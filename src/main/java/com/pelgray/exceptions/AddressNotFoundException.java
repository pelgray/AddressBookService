package com.pelgray.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer addressId) {
        super("Could not find address with id=" + addressId);
    }
}
