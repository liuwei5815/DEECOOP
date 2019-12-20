package com.xy.vmes.exception;

public class ServiceDisconnectException extends Exception {
    private String errorCode;

    public ServiceDisconnectException(String message) {
        super(message);
    }

    public ServiceDisconnectException(String errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
    }


    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
