package com.axeman.auth.exceptions;

public abstract class BaseException extends RuntimeException {
    public BaseException(String exceptionCode) {
        super(exceptionCode);
    }
}
