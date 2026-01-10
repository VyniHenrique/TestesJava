package com.vynicius.Locadora.controller;

import com.vynicius.Locadora.entity.CarroEntity;
import com.vynicius.Locadora.service.CarroService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CarroService service;

    @Test
    @DisplayName("Deve salvar um carro")
    void salvarCarro() throws Exception {

        // Cenário
        CarroEntity carro = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2026);
        carro.setId(1L);

        Mockito.when(service.salvar(Mockito.any())).thenReturn(carro);

        String json = """
                {
                   "modelo": "Civic",
                   "valorDiaria": 200.00,
                   "ano": 2026
                }
                """;

        // Execução
        ResultActions resultado = mvc.perform(
                MockMvcRequestBuilders
                        .post("/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );


        // Verificação
        resultado
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Civic"));
    }


    @Test
    @DisplayName("Deve obter os detalhes de um carro")
    void obterDetalhes() throws Exception {
        CarroEntity carro = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 2026);
        carro.setId(1L);

        Mockito.when(service.buscarPorId(Mockito.any())).thenReturn(carro);

        mvc.perform(
                MockMvcRequestBuilders.get("/carros/1")

        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
         .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Civic"))
         .andExpect(MockMvcResultMatchers.jsonPath("$.valorDiaria").value(200.00));

    }

    @Test
    @DisplayName("Deve apontar para um erro quando um carro não existir")
    void apontarErroCarro() throws Exception {
        Mockito.when(service.buscarPorId(Mockito.any())).thenThrow(EntityNotFoundException.class);

        mvc.perform(
                    MockMvcRequestBuilders.get("/carros/1")

                ).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @DisplayName("Deve listar todos os carros")
    void listarCarros() throws Exception {

        List<CarroEntity> lista = List.of(
                new CarroEntity("Civic", BigDecimal.valueOf(200.00), 1998),
                new CarroEntity("Eclipse", BigDecimal.valueOf(150.00), 2003)
        );

        Mockito.when(service.listarTodos()).thenReturn(lista);

        mvc.perform(
                MockMvcRequestBuilders.get("/carros")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve atualizar um carro com sucesso")
    void atualizarCarro() throws Exception {

        CarroEntity carro = new CarroEntity("Civic", BigDecimal.valueOf(200.00), 1998);
        Mockito.when(service.atualizar(Mockito.any(), Mockito.any())).thenReturn(carro);

        String json = """
                {
                   "modelo": "Civic",
                   "valorDiaria": 200.00,
                   "ano": 1998
                }
                """;

        mvc.perform(MockMvcRequestBuilders
                        .put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("Deve apontar para um erro quando tentar atualizar um carro inexistente")
    void atualizarCarroErro() throws Exception{

        Mockito.when(service.atualizar(Mockito.any(), Mockito.any())).thenThrow(EntityNotFoundException.class);

        String json = """
                {
                   "modelo": "Civic",
                   "valorDiaria": 200.00,
                   "ano": 1998
                }
                """;

        mvc.perform(
                MockMvcRequestBuilders
                        .put("/carrros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Deve deletar um carro com sucesso")
    void deletarCarro() throws Exception{
        Mockito.doNothing().when(service).deletar(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Deve apontar para um erro quando tentar deletar um carro inexistente")
    void deletarCarroErro() throws Exception{

            Mockito.doThrow(EntityNotFoundException.class).when(service).deletar(Mockito.any());

        mvc.perform(MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
