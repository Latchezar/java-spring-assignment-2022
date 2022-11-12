package com.latchezar.javaspringassignment.exception.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FieldErrorResponse {

    private String fieldName;
    private String errorMessage;

}
