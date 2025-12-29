package com.vynicius.Locadora.repository;

import com.vynicius.Locadora.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
}
