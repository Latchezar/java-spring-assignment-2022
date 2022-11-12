package com.latchezar.javaspringassignment.exception.model;

import lombok.Getter;

@Getter
public enum ErrorCode {

    SOMETHING_WENT_WRONG(10000, "Something went wrong! Please try again."),

    WORD_RELATION_ALREADY_EXISTS(11001, "Relation with the given words already exist!"),
    NO_PATH_AVAILABLE(11002, "No Path Available!");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;
}
