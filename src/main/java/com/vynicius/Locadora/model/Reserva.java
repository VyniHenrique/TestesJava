package com.vynicius.Locadora.model;

import com.vynicius.Locadora.exeptions.ReservaInvalidaException;
import lombok.Data;

@Data
public class Reserva {

    private Cliente cliente;
    private Carro carro;
    private int quantidadeDeDias;

    public Reserva(Cliente cliente, Carro carro, int quantidadeDeDias) {
        if (quantidadeDeDias < 1){
            throw new ReservaInvalidaException("A reserva é inválida");
        }
        this.cliente = cliente;
        this.carro = carro;
        this.quantidadeDeDias = quantidadeDeDias;
    }

    public double calcularTotalReserva(){

        double valorAluguel = carro.calcularValorAluguel(quantidadeDeDias);
        System.out.println(cliente.getNome());

        return valorAluguel;
    }
}
