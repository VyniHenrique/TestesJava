package com.vynicius.Locadora.controller;

import com.vynicius.Locadora.entity.CarroEntity;
import com.vynicius.Locadora.exeptions.CarroNaoEncontradoException;
import com.vynicius.Locadora.service.CarroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{id}")
    public ResponseEntity<CarroEntity> obterDetalhes(@PathVariable Long id){

        try {
            CarroEntity carro = service.buscarPorId(id);
            return ResponseEntity.ok().body(carro);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CarroEntity>> listarTodos(){
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody CarroEntity carro){
        try {
            service.atualizar(id, carro);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try {
            service.deletar(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
