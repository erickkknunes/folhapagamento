package com.calculadora.father.demo.controllers;

import com.calculadora.father.demo.models.Funcionario;
import com.calculadora.father.demo.repositories.FuncionarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioRepo funcionarioRepo;

    @Autowired
    public FuncionarioController(FuncionarioRepo funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    // Endpoint para cadastrar um novo funcionário
    @PostMapping("/cadastrar")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioRepo.save(funcionario);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    // Endpoint para obter um funcionário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> obterFuncionario(@PathVariable Long id) {
        return funcionarioRepo.findById(id)
                .map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
