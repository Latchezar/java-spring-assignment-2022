package com.latchezar.javaspringassignment.exception;

import com.latchezar.javaspringassignment.exception.model.ErrorCode;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
