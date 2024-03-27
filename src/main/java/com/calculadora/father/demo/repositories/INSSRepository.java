package com.calculadora.father.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calculadora.father.demo.models.*;

public interface INSSRepository extends JpaRepository<Inss, Long> {
}