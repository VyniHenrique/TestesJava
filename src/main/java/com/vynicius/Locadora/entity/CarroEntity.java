package com.vynicius.Locadora.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "carro")
@Data
public class CarroEntity {

    public CarroEntity(){
    }

    public CarroEntity(String modelo, BigDecimal valorDiaria, int ano){
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private BigDecimal valorDiaria;

    private int ano;
}
