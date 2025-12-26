package com.vynicius.Locadora.model;

import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import com.vynicius.Locadora.exeptions.ReservaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservaTest {

    Carro carro;
    Cliente cliente;

    @BeforeEach
    void setUp(){
        cliente = new Cliente("Mario");
        carro = new Carro("Civic", 100.00);
    }

    @Test
    void deveCriarUmaReserva(){
        // 1 - Cenário
        int dias = 5;
        // 2 - Execução
        var reserva = new Reserva(cliente, carro, dias);
        // 3 - Verificação
        Assertions.assertThat(reserva).isNotNull();
    }

    @Test
    void deveCalcularValorReservaCorretamente(){
        // 1 - Cenário
        Reserva reserva = new Reserva(cliente, carro, 5);

        // 2 - Execução
        double total = reserva.calcularTotalReserva();

        // 3 - Verificação
        Assertions.assertThat(total).isEqualTo(450.0);
    }

    @Test
    @DisplayName("Deve lançar um erro quando o valor de dias for menor que 1")
    void testeReservaInvalidaException(){

        // 1 - Cenário
        int dias = 0;

        // 2 - Execução

        // JUnit
        assertThrows(ReservaInvalidaException.class, () -> new Reserva(cliente, carro, dias));

        // AssertJ
        var erro = Assertions.catchThrowable(() -> new Reserva(cliente, carro, dias));

        // 3 - Verificação
        Assertions.assertThat(erro).isInstanceOf(ReservaInvalidaException.class).hasMessage("A reserva é inválida");
    }
}
