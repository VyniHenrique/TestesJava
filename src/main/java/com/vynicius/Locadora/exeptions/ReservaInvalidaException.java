package com.vynicius.Locadora.exeptions;

public class ReservaInvalidaException extends RuntimeException {
    public ReservaInvalidaException(String message) {
        super(message);
    }
}
