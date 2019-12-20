package com.xy.vmes.exception;

public class ApplicationException extends Exception {
    private String errorCode;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String errorCode, String message) {
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
