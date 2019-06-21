package com.guo.javaconcurrencyinpractice.testexception;

public class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String message) {
        super(message);
    }
}
