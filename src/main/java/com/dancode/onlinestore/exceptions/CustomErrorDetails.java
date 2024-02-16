package com.dancode.onlinestore.exceptions;

public class CustomErrorDetails {
    private String message;

    public CustomErrorDetails() {
    }
    
    public CustomErrorDetails(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
