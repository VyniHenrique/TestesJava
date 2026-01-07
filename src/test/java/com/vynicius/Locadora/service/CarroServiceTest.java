package com.vynicius.Locadora.service;

import com.vynicius.Locadora.entity.CarroEntity;
import com.vynicius.Locadora.exeptions.CarroNaoEncontradoException;
import com.vynicius.Locadora.repository.CarroRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
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
        CarroEntity carro = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2025);

        Mockito.when( repository.save(Mockito.any()) ).thenReturn(carro);

        var carroSalvo = service.salvar(carro);

        assertNotNull(carroSalvo);
        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    @DisplayName("Deve apontar um erro ao tentar salvar um carro com valor de diária negativa")
    void erroSalvar(){

        // 1 - Cenário
        CarroEntity carro = new CarroEntity("Civic", BigDecimal.valueOf(-200.00), 2025);

        // 2 - Execução
        Throwable throwable = Assertions.catchThrowable(() -> service.salvar(carro));

        // 3 - Verificação
        Assertions.assertThat(throwable).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @DisplayName("Deve atualizar um carro")
    @Test
    void atualizarCarro(){
        CarroEntity carroExistente = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2025);
        CarroEntity carroAtualizado = new CarroEntity("Sedan", BigDecimal.valueOf(300.00), 2015);
        carroAtualizado.setId(1L);

        Mockito.when( repository.findById(1L)).thenReturn(Optional.of(carroExistente));

        Mockito.when( repository.save(Mockito.any()) ).thenReturn(carroAtualizado);

        CarroEntity resultado = service.atualizar(1L, carroAtualizado);

        assertEquals("Sedan", resultado.getModelo());

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Deve apontar para um erro quando tentar atualizar um carro inexistente")
    void erroAtualizar(){
        CarroEntity carroExistente = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2025);

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var error = Assertions.catchThrowable( () -> service.atualizar(1L, carroExistente));

        Assertions.assertThat(error).isInstanceOf(CarroNaoEncontradoException.class);

        Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("Deve deletar um carro")
    void deletarCarro(){
        CarroEntity carroExistente = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2026);
        carroExistente.setId(1L);

        Mockito.when( repository.findById(1L)).thenReturn( Optional.of(carroExistente));

        service.deletar(1L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }


    @Test
    @DisplayName("Deve apontar para um erro quando tentar deletar um carro inexistente")
    void erroDeletar(){

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var error = Assertions.catchThrowable( () -> service.deletar(1L));

        Assertions.assertThat(error).isInstanceOf(CarroNaoEncontradoException.class);

        Mockito.verify(repository, Mockito.times(0)).deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Deve buscar um carro por id")
    void buscarCarro(){
        CarroEntity carroExistente = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2026);
        carroExistente.setId(1L);

        Mockito.when( repository.findById(1L)).thenReturn( Optional.of(carroExistente));

        CarroEntity carroEncontrado = service.buscarPorId(1L);

        Assertions.assertThat(carroEncontrado.getModelo()).isEqualTo("Civic");

        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve apontar para um erro quando tentar buscar um carro inexistente")
    void erroBuscarCarro(){

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var error = Assertions.catchThrowable( () -> service.deletar(1L));

        Assertions.assertThat(error).isInstanceOf(CarroNaoEncontradoException.class);

        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    @DisplayName("Deve listar todos os carros")
    void listarTodos(){
        CarroEntity carro1 = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2026);
        carro1.setId(1L);
        CarroEntity carro2 = new CarroEntity("Eclipse", BigDecimal.valueOf(150.00), 2003);
        carro2.setId(2L);

        List<CarroEntity> lista = List.of(carro1, carro2);

        Mockito.when(repository.findAll()).thenReturn(lista);

        List<CarroEntity> carroEntities = service.listarTodos();

        Assertions.assertThat(carroEntities).hasSize(2);

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }
}