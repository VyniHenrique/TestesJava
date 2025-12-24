package com.vynicius.Locadora.model;

import com.vynicius.Locadora.exeptions.ReservaInvalidaException;
import lombok.Data;

@Data
public class Reserva {

    private Cliente cliente;
    private Carro carro;

    public Reserva(Cliente cliente, Carro carro) {
        this.cliente = cliente;
        this.carro = carro;
    }

    public double calcularTotalReserva(int quantidadeDeDias){

        double valorAluguel = carro.calcularValorAluguel(quantidadeDeDias);
        System.out.println(cliente.getNome());

        if (quantidadeDeDias < 0){
            throw new ReservaInvalidaException("A reserva é inválida");
        }
        return valorAluguel;
    }
}
