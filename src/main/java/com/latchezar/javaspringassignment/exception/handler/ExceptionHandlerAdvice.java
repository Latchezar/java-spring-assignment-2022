package com.latchezar.javaspringassignment.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.latchezar.javaspringassignment.exception.ServiceException;
import com.latchezar.javaspringassignment.exception.model.ErrorCode;
import com.latchezar.javaspringassignment.exception.model.ErrorResponse;
import com.latchezar.javaspringassignment.exception.model.FieldErrorResponse;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(errorCode.getCode())
                .errorMessage(errorCode.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<FieldErrorResponse>> handleException(BindException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<FieldErrorResponse> errorDetails = new ArrayList<>();
        for (FieldError fieldError : errors) {
            FieldErrorResponse fieldErrorResponse = FieldErrorResponse.builder()
                    .fieldName(fieldError.getField())
                    .errorMessage(fieldError.getDefaultMessage())
                    .build();
            errorDetails.add(fieldErrorResponse);
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
