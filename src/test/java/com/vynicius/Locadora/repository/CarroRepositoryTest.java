package com.vynicius.Locadora.repository;

import com.vynicius.Locadora.entity.CarroEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    @Test
    @DisplayName("Deve salvar um Carro")
    void salvarCarro(){

        // 1 - Cenário
        CarroEntity carro = new CarroEntity("Civic", BigDecimal.valueOf(500.00));

        // 2 - Execução
        repository.save(carro);


        // 3 - Verificação
        assertNotNull(carro.getId());
    }
}