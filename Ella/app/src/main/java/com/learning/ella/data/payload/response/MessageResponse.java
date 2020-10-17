package com.learning.ella.data.payload.response;

import com.android.volley.Response;

public class MessageResponse {
    private String message;

    public interface MessageResponseListener extends Response.Listener<MessageResponse> {
        @Override
        void onResponse(MessageResponse response);
    }

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}