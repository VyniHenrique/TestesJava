package com.vynicius.Locadora.model;

import lombok.Data;

@Data
public class Carro {

    public Carro(String modelo, double valorDiaria) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    private String modelo;
    private double valorDiaria;


    public double calcularValorAluguel(int dias){
        if(dias > 4){
            return (dias * valorDiaria) - 50;
        }
        return dias * valorDiaria;
    }
}
