package com.server.musalasoft.drone_management.handler;

public class UnavailableException extends RuntimeException {
    public UnavailableException(String message) {
        super(message);
    }
}
