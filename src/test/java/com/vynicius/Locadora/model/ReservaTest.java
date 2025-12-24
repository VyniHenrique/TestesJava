package com.vynicius.Locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReservaTest {

    @Test
    void deveCalcularValorReservaCorretamente(){
        // 1 - Cenário
        Cliente cliente = new Cliente("Mario");
        Carro carro = new Carro("Civic", 100.00);
        Reserva reserva = new Reserva(cliente, carro);

        // 2 - Execução
        double total = reserva.calcularTotalReserva(5);

        // 3 - Verificação
        Assertions.assertEquals(450.0, total);
    }
}
