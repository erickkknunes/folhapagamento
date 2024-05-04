package com.calculadora.father.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calculadora.father.demo.models.Descontos;

import java.util.Optional;

public interface DescontosRepository extends JpaRepository<Descontos, Long> {
    Optional<Descontos> findByFuncionarioId(Long funcionarioId);
}
