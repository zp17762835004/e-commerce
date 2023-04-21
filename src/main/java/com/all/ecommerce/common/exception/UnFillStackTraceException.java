package com.all.ecommerce.common.exception;

public class UnFillStackTraceException extends RuntimeException {

    private static final long serialVersionUID = -3181827538683088424L;

    public UnFillStackTraceException() {

        this(null, null);

    }

    public UnFillStackTraceException(String message) {

        this(message, null);

    }

    public UnFillStackTraceException(Throwable cause) {

        this(null, cause);

    }

    public UnFillStackTraceException(String message, Throwable cause) {

        super(message, cause, false, false);

    }

}
