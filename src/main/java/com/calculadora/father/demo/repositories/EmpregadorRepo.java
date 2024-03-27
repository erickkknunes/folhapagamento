package com.calculadora.father.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calculadora.father.demo.models.*;

public interface EmpregadorRepo extends JpaRepository<Empregador, Long> {
    Empregador findByCnpj(String cnpj);
}