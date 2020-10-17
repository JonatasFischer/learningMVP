package com.learning.ella.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.learning.ella.data.model.LoggedInUser;
import com.learning.ella.data.payload.response.JwtResponse;
import com.learning.ella.data.payload.response.MessageResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    String SIGN_IN_URL = "http://192.168.0.132:8081/api/auth/signin";
    private RequestQueue queue;
    public LoginDataSource(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void login(String username, String password, JwtResponse.JwtResponseListener listener, MessageResponse.MessageResponseListener errorListener) {
        Gson gson = new Gson();
        try {
            JSONObject requestBody= (new JSONObject()).put("username", username).put("password", password);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SIGN_IN_URL, requestBody,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (response != null) {
                        boolean isJwtResponse = response.has("accessToken");
                        boolean isMessageResponse = response.has("message");
                        if (isJwtResponse) {

                            JwtResponse jwt = gson.fromJson(response.toString(), JwtResponse.class);
                            if (jwt != null) {
                                listener.onResponse(jwt);
                            }
                        } else if(isMessageResponse) {
                            MessageResponse messageResponse = gson.fromJson(response.toString(), MessageResponse.class);
                            if(messageResponse != null)
                                errorListener.onResponse(messageResponse);
                        } else {
                            errorListener.onResponse(new MessageResponse("Malford answer: "+ response.toString()));
                        }
                    }
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorListener.onResponse(new MessageResponse(error.getMessage()));
                }
            });
            queue.add(jsonObjectRequest);





        } catch (Exception e) {
            errorListener.onResponse(new MessageResponse(e.getMessage()));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}