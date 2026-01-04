package com.vynicius.Locadora.service;

import com.vynicius.Locadora.entity.CarroEntity;
import com.vynicius.Locadora.repository.CarroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService service;

    @Mock
    CarroRepository repository;

    @Test
    @DisplayName("Deve salvar um carro")
    void salvarCarro(){
        Mockito
                .when(repository.findById(1L))
                .thenReturn(Optional.of(new CarroEntity("Sedan", BigDecimal.valueOf(270.00), 2020)));

        System.out.println(repository.findById(1L));
    }
}