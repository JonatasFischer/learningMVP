package com.learning.ella.data.model;

import com.android.volley.Response;
import com.android.volley.Response.Listener;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;

    public interface LoggedUserListener extends Listener<LoggedInUser>{
        @Override
        void onResponse(LoggedInUser response);
    }

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}