package com.calculadora.father.demo.repositories;

import com.calculadora.father.demo.models.Beneficios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiosRepo extends JpaRepository<Beneficios, Long> {
    Optional<Beneficios> findByFuncionarioId(Long funcionarioId);
}
