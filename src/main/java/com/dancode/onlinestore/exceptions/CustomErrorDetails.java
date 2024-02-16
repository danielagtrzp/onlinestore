package com.dancode.onlinestore.exceptions;

public class CustomErrorDetails {

    private String uri;
    private String message;
    private StackTraceElement[] stackTrace;

    public CustomErrorDetails() {
    }

    public CustomErrorDetails(String uri, String message, StackTraceElement[] stackTrace) {
        this.uri = uri;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }
    
}
