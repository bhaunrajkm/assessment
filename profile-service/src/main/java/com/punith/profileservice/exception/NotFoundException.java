package com.punith.profileservice.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {
    private String message;

    public NotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
