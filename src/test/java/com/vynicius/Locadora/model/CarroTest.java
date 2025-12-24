package com.vynicius.Locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel")
    void deveCalcularValorAluguel(){

        // 1 - Cenário
        Carro carro = new Carro("Sedan", 100.00);

        // 2 - Execução
        double total = carro.calcularValorAluguel(3);

        //3 - Verificação
        Assertions.assertEquals(300.0, total);
    }


    @Test
    @DisplayName("Deve calcular o valor correto do aluguel com desconto")
    void deveCalcularValorAluguelComDesconto(){

        // 1 - Cenário
        Carro carro = new Carro("Sedan", 100.00);
        int quantidadeDias = 5;

        // 2 - Execução
        double total = carro.calcularValorAluguel(quantidadeDias);

        //3 - Verificação
        Assertions.assertEquals(450.0, total);
    }
}
