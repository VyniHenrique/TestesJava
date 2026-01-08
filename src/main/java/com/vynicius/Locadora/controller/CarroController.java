package com.vynicius.Locadora.controller;

import com.vynicius.Locadora.entity.CarroEntity;
import com.vynicius.Locadora.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carros")
public class CarroController {

    private final CarroService service;

    public CarroController(CarroService service){
        this.service = service;
    }


    @PostMapping()
    public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro){
        try {
            CarroEntity carroSalvo = service.salvar(carro);
            return ResponseEntity.status(HttpStatus.OK).body(carroSalvo);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }
}
