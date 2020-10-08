package com.pelgray.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Integer clientId) {
        super("Could not find client with id=" + clientId);
    }
}
