package com.vynicius.Locadora.repository;

import com.vynicius.Locadora.entity.CarroEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    CarroEntity carro;

    @BeforeEach
    void setUp(){
        carro = new CarroEntity("Supra", BigDecimal.valueOf(700.00), 1990);
    }

    @Test
    @DisplayName("Deve salvar um Carro")
    void salvarCarro(){

        // 2 - Execução
        repository.save(carro);


        // 3 - Verificação
        assertNotNull(carro.getId());
    }

    @Test
    @DisplayName("Deve buscar carro por modelo")
    @Sql("/sql/popular-carros.sql")
    void buscarCarroModelo(){

        // 2 - Execução
        List<CarroEntity> lista = repository.findByModelo("Civic");
        CarroEntity carro = lista.stream().findFirst().get();

        // 3 - Verificação(JUnit)
        assertEquals("Civic",carro.getModelo());

        // 3 - Verificação(AssertJ)
        assertThat(carro.getModelo()).isEqualTo("Civic");
    }

    @Test
    @DisplayName("Deve bucar carro por id")
    @Sql("/sql/popular-carros.sql")
    void bucarCarroId(){

        // 2 - Execução
        Optional<CarroEntity> carroEncontrado = repository.findById(1L);

        // 3 - Verificação(JUnit)
        assertEquals(1L, carroEncontrado.get().getId());

        // 3 - Verificação(AssertJ)
        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Deve atualizar um carro")
    void atualizarCarro(){

        // 2 - Execução
        CarroEntity carroSalvo = repository.save(carro);
        carroSalvo.setAno(2000);
        CarroEntity carroAtualizado = repository.save(carroSalvo);

        // 3 - Verificação(JUnit)
        assertEquals(2000, carroAtualizado.getAno());

        // 3 - Verificação(AssertJ)
        assertThat(carroAtualizado.getAno()).isEqualTo(2000);
    }

    @Test
    @DisplayName("Deve deletar um carro")
    void deletarCarro(){

        // 2 - Execução
        CarroEntity carroSalvo = repository.save(carro);
        repository.deleteById(carroSalvo.getId());
        Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

        // 3 - Verificação(JUnit)
        assertTrue(carroEncontrado.isEmpty());

        // 3 - Verificação(AssertJ)
        assertThat(carroEncontrado).isEmpty();

    }
}