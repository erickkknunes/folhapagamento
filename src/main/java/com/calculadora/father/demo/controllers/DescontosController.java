package com.calculadora.father.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.calculadora.father.demo.models.*;
import com.calculadora.father.demo.repositories.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/descontos")
public class DescontosController {

    @Autowired
    private DescontosRepository descontosRepository;

    @Autowired
    private TabelaIRRFRepository tabelaIRRFRepository;

    @PostMapping("/calcdesc")
    public ResponseEntity<Descontos> calcularDescontos(@RequestBody Map<String, String> params) {
        try {
            double salarioBase = Double.parseDouble(params.get("salarioBase"));
            boolean optouVT = Boolean.parseBoolean(params.get("optouVT"));
            boolean optouCS = Boolean.parseBoolean(params.get("optouCS"));
            int diasFaltados = Integer.parseInt(params.get("diasFaltados"));
            LocalDate dataFolhaPagamento = LocalDate.parse(params.get("dataFolhaPagamento"));

            double fgts = salarioBase * 0.08;
            double valeTransporte = optouVT ? salarioBase * 0.06 : 0;
            double valeAlimentacao = salarioBase * 0.10;
            double contribuicaoSindical = optouCS ? salarioBase * 0.02 : 0;
            double faltasAtrasos = (salarioBase / 30) * diasFaltados;

            // Calculando o IRRF
            double irrf = calcularIRRF(salarioBase);

            Descontos response = new Descontos();
            response.setFgts(fgts);
            response.setValeTransporte(valeTransporte);
            response.setValeAlimentacao(valeAlimentacao);
            response.setContribuicaoSindical(contribuicaoSindical);
            response.setFaltasAtrasos(faltasAtrasos);
            response.setIrrf(irrf);
            response.setDataFolhaPagamento(dataFolhaPagamento);

            descontosRepository.save(response);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Retorna 400 Bad Request em caso de erro nos parâmetros
        }
    }

    // Método para calcular o IRRF com base no salário
    private double calcularIRRF(double salarioBase) {
        Iterable<TabelaIRRF> tabela = tabelaIRRFRepository.findAll();
        for (TabelaIRRF faixa : tabela) {
            double faixaInicial = faixa.getFaixaInicial();
            double faixaFinal = faixa.getFaixaFinal();
            double aliquota = faixa.getAliquota();

            if (salarioBase >= faixaInicial && salarioBase <= faixaFinal) {
                return salarioBase * aliquota;
            }
        }
        return 0; // Se não estiver em nenhuma faixa, retorna 0
    }
}
