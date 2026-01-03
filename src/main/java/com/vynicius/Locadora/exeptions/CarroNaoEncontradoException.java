package com.vynicius.Locadora.exeptions;

public class CarroNaoEncontradoException extends RuntimeException{
    public CarroNaoEncontradoException(String message){
        super(message);
    }
}
