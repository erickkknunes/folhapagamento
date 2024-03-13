package com.calculadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calculadora.entities.*;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}