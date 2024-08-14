package com.osharif.spring_lms.Exceptions;

public class CantProcessException extends RuntimeException{
    public CantProcessException(String message) {
        super(message);
    }
}
