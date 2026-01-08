package com.vynicius.Locadora.controller;

import com.vynicius.Locadora.entity.CarroEntity;
import com.vynicius.Locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CarroService service;

    @Test
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
}
