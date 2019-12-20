package com.xy.vmes.exception;

public class TableVersionException extends Exception {
    private String errorCode;

    public TableVersionException(String message) {
        super(message);
    }

    public TableVersionException(String errorCode, String message) {
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
