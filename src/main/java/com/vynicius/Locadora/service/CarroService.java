package com.vynicius.Locadora.service;


import com.vynicius.Locadora.entity.CarroEntity;
import com.vynicius.Locadora.exeptions.CarroNaoEncontradoException;
import com.vynicius.Locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository){
        this.repository = repository;
    }

    public CarroEntity salvar(CarroEntity carro){
        if (carro.getValorDiaria() == null || carro.getValorDiaria().compareTo(BigDecimal.ONE) < 1){
            throw new IllegalArgumentException("O valor deve ser instanciado e deve ser maior que zero");
        }
        return repository.save(carro);
    }

    public CarroEntity atualizar(Long id, CarroEntity carroAtualizado){
        if (carroAtualizado.getValorDiaria() == null || carroAtualizado.getValorDiaria().compareTo(BigDecimal.ONE) < 1){
            throw new IllegalArgumentException("O valor deve ser instanciado e deve ser maior que zero");
        }

        var carro = repository.findById(id)
                .orElseThrow(() -> new CarroNaoEncontradoException("Carro não encontrado"));

        carro.setAno(carroAtualizado.getAno());
        carro.setModelo(carroAtualizado.getModelo());
        carro.setValorDiaria(carroAtualizado.getValorDiaria());

        return repository.save(carro);
    }

    public void deletar(Long id){
        var carro = repository.findById(id)
                .orElseThrow(() -> new CarroNaoEncontradoException("Carro não encontrado"));

        repository.deleteById(carro.getId());
    }

    public CarroEntity buscarPorId(Long id){
        Optional<CarroEntity> carro = repository.findById(id);
        return carro.orElseThrow(() -> new CarroNaoEncontradoException("Carro não encontrado"));
    }

    public List<CarroEntity> listarTodos(){
        return repository.findAll();
    }
}
