package com.calculadora.father.demo.repositories;

import com.calculadora.father.demo.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepo extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByEmpregadorId(Long empregadorId);
}
