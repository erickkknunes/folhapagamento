package com.calculadora.father.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.calculadora.father.demo.models.*;
import com.calculadora.father.demo.repositories.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/descontos")
public class DescontosController {

    @Autowired
    private DescontosRepository descontosRepository;

    @Autowired
    private FuncionarioRepo funcionarioRepo;

    @PostMapping("/calcdesc")
    public ResponseEntity<Descontos> calcularDescontos(@RequestBody Map<String, String> params) {
        try {
            String nomeFuncionario = params.get("funcionarioNome");
            List<Funcionario> funcionarios = funcionarioRepo.findByNomeCompleto(nomeFuncionario);

            if (funcionarios.isEmpty()) {
                throw new IllegalArgumentException("Funcionário não encontrado.");
            }

            Funcionario funcionario = funcionarios.get(0); // Obtém o primeiro funcionário da lista

            // Verifica se já existe um registro de descontos para o funcionário
            Optional<Descontos> descontosOptional = descontosRepository.findByFuncionarioId(funcionario.getId());
            Descontos descontos;

            if (descontosOptional.isPresent()) {
                // Se já existir, atualize o registro existente com os novos valores de
                // descontos calculados
                descontos = descontosOptional.get();
            } else {
                // Se não existir, crie um novo registro de descontos
                descontos = new Descontos();
                descontos.setFuncionario(funcionario);
            }

            // Realize os cálculos dos descontos conforme antes
            double salarioBase = funcionario.getSalarioBase();
            boolean optouVT = Boolean.parseBoolean(params.get("optouVT"));
            boolean optouCS = Boolean.parseBoolean(params.get("optouCS"));
            int diasFaltados = Integer.parseInt(params.get("diasFaltados"));
            LocalDate dataFolhaPagamento = LocalDate.parse(params.get("dataFolhaPagamento"));

            double fgts = salarioBase * 0.08;
            double valeTransporte = optouVT ? salarioBase * 0.06 : 0;
            double valeAlimentacao = salarioBase * 0.10;
            double contribuicaoSindical = optouCS ? salarioBase * 0.02 : 0;
            double faltasAtrasos = (salarioBase / 30) * diasFaltados;
            double irrf = calcularIRRF(salarioBase);
            double inss = calcularINSS(salarioBase);

            // Atualize os valores dos descontos no objeto Descontos
            descontos.setFgts(fgts);
            descontos.setValeTransporte(valeTransporte);
            descontos.setValeAlimentacao(valeAlimentacao);
            descontos.setContribuicaoSindical(contribuicaoSindical);
            descontos.setFaltasAtrasos(faltasAtrasos);
            descontos.setIrrf(irrf);
            descontos.setInss(inss);
            descontos.setDataFolhaPagamento(dataFolhaPagamento);

            // Salve ou atualize o objeto Descontos no banco de dados
            Descontos savedDescontos = descontosRepository.save(descontos);

            return ResponseEntity.ok(savedDescontos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private double calcularIRRF(double salarioBase) {
        double irrf;
        if (salarioBase <= 1903.98) {
            irrf = 0; // Isento
        } else if (salarioBase <= 2826.65) {
            irrf = (salarioBase - 1903.98) * 0.075;
        } else if (salarioBase <= 3751.05) {
            irrf = (salarioBase - 2826.65) * 0.15 + 69.36;
        } else if (salarioBase <= 4664.68) {
            irrf = (salarioBase - 3751.05) * 0.225 + 172.44;
        } else {
            irrf = (salarioBase - 4664.68) * 0.275 + 330.55;
        }
        return irrf;
    }

    private double calcularINSS(double salarioBase) {
        double inss;
        if (salarioBase <= 1751.81) {
            inss = salarioBase * 0.08;
        } else if (salarioBase <= 2919.72) {
            inss = salarioBase * 0.09;
        } else if (salarioBase <= 5839.45) {
            inss = salarioBase * 0.11;
        } else {
            inss = 642.34; // Fixo acima de R$ 5.839,45
        }
        return inss;
    }
}
