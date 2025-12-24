package com.vynicius.Locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void deveCriarClienteComNome(){
        // 1 - Cenário
        Cliente cliente = new Cliente("Maria");

        // 2 - Execução
        String nome = cliente.getNome();

        // 3 - Verificação
        Assertions.assertEquals("Maria", nome);
    }
}
