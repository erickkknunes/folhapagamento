package com.calculadora.father.demo.controllers;

import com.calculadora.father.demo.models.Funcionario;
import com.calculadora.father.demo.repositories.EmpregadorRepo;
import com.calculadora.father.demo.repositories.FuncionarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selecao")
public class SelecaoController {

    private final EmpregadorRepo empregadorRepo;
    private final FuncionarioRepo funcionarioRepo;

    @Autowired
    public SelecaoController(EmpregadorRepo empregadorRepo, FuncionarioRepo funcionarioRepo) {
        this.empregadorRepo = empregadorRepo;
        this.funcionarioRepo = funcionarioRepo;
    }

    // Endpoint para obter funcionários de um empregador específico
    @GetMapping("/funcionariosPorEmpregador/{empregadorId}")
    public ResponseEntity<List<Funcionario>> obterFuncionariosPorEmpregador(@PathVariable Long empregadorId) {
        List<Funcionario> funcionarios = funcionarioRepo.findByEmpregadorId(empregadorId);
        if (funcionarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }
}
