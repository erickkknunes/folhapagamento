package com.calculadora.father.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.calculadora.father.demo.models.*;
import com.calculadora.father.demo.repositories.*;

import java.util.Optional;

@RestController
@RequestMapping("/remuneracao")
public class RemuneracaoController {

    @Autowired
    private FuncionarioRepo funcionarioRepo;

    @Autowired
    private BeneficiosRepo beneficiosRepo;

    @Autowired
    private DescontosRepository descontosRepository;

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<CalculoRemuneracaoDTO> consultarRemuneracao(@PathVariable Long funcionarioId) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepo.findById(funcionarioId);

        if (!funcionarioOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Funcionario funcionario = funcionarioOptional.get();
        Optional<Beneficios> beneficiosOptional = beneficiosRepo.findByFuncionarioId(funcionarioId);
        Optional<Descontos> descontosOptional = descontosRepository.findByFuncionarioId(funcionarioId);

        if (!beneficiosOptional.isPresent() || !descontosOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Beneficios beneficios = beneficiosOptional.get();
        Descontos descontos = descontosOptional.get();

        /* double salarioTotal = calcularSalarioTotal(beneficios, descontos); */

        CalculoRemuneracaoDTO remuneracaoDTO = new CalculoRemuneracaoDTO();
        remuneracaoDTO.setFuncionario(funcionario);
        remuneracaoDTO.setBeneficios(beneficios);
        remuneracaoDTO.setDescontos(descontos);
        /* remuneracaoDTO.setSalarioTotal(salarioTotal); */

        return ResponseEntity.ok(remuneracaoDTO);
    }
}
