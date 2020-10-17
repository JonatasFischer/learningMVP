package com.learning.ella.data;

import com.learning.ella.data.model.LoggedInUser;
import com.learning.ella.data.payload.response.MessageResponse;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void initRepository(LoginDataSource dataSource) {
        if (instance == null) {

        }
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void login(String username, String password, LoggedInUser.LoggedUserListener listener, MessageResponse.MessageResponseListener errorListener) {
        dataSource.login(username, password, result -> {
                    LoggedInUser newResult = new LoggedInUser(result.getId().toString(), result.getUsername());
                    setLoggedInUser(newResult);
                    listener.onResponse(newResult);
                }, errorListener);
    }
}