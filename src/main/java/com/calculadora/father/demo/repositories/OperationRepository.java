package com.calculadora.father.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calculadora.father.demo.models.*;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}