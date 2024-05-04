package com.calculadora.father.demo.controllers;

import com.calculadora.father.demo.models.Beneficios;
import com.calculadora.father.demo.models.Funcionario;
import com.calculadora.father.demo.repositories.BeneficiosRepo;
import com.calculadora.father.demo.repositories.FuncionarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.Period;

@RestController
public class BeneficiosController {

    @Autowired
    private BeneficiosRepo beneficiosRepo;

    @Autowired
    private FuncionarioRepo funcionarioRepo;

    @PostMapping("/beneficios/calcular")
    @Transactional
    public ResponseEntity<?> calcularBeneficios(@RequestBody Map<String, String> params) {
        try {
            String funcionarioNome = params.get("funcionarioNome");
            int horasExtras = Integer.parseInt(params.get("horasExtras"));
            int horasExtrasFeriadoDomingo = Integer.parseInt(params.get("horasExtrasFeriadoDomingo"));
            int horasExtrasNoturnas = Integer.parseInt(params.get("horasExtrasNoturnas"));
            boolean valeAlimentacao = Boolean.parseBoolean(params.get("valeAlimentacao"));
            boolean optouAP = Boolean.parseBoolean(params.get("optouAP"));
            String adInsal = params.get("adInsal");

            int countMulheresComDependentes = funcionarioRepo.countMulheresComMaisDe16AnosComDependentes();

            List<Funcionario> funcionarios = funcionarioRepo.findByNomeCompleto(funcionarioNome);
            if (funcionarios.isEmpty()) {
                throw new IllegalArgumentException("Funcionário não encontrado.");
            }

            // Utiliza o primeiro funcionário retornado (assumindo que não há funcionários
            // duplicados com o mesmo nome)
            Funcionario funcionario = funcionarios.get(0);

            // Calcula o valor das horas extras
            double valorHorasExtras = calcularHoraExtra(funcionario.getSalarioBase(), horasExtras, false, false);

            // Calcula o valor das horas extras em feriados/domingos
            double valorHorasExtrasFeriadoDomingo = calcularHoraExtra(funcionario.getSalarioBase(),
                    horasExtrasFeriadoDomingo, true, false);

            // Calcula o valor das horas extras noturnas
            double valorHorasExtrasNoturnas = calcularHoraExtra(funcionario.getSalarioBase(),
                    horasExtrasNoturnas, false, true);

            double adicionalPericulosidade = 0.0;
            // Adiciona adicional de periculosidade se optado pelo funcionário
            if (optouAP) {
                adicionalPericulosidade = funcionario.getSalarioBase() * 0.3; // 30% do salário base
            }

            // Adiciona adicional de insalubridade de acordo com o nível escolhido
            double adicionalInsalubridade = 0.0;
            switch (adInsal) {
                case "Mínimo":
                    adicionalInsalubridade = funcionario.getSalarioBase() * 0.1; // 10% do salário base
                    break;
                case "Médio":
                    adicionalInsalubridade = funcionario.getSalarioBase() * 0.2; // 20% do salário base
                    break;
                case "Máximo":
                    adicionalInsalubridade = funcionario.getSalarioBase() * 0.4; // 40% do salário base
                    break;
                default:
                    throw new IllegalArgumentException("Selecione um nível de insalubridade válido.");
            }

            double salarioFamilia = 0.0;
            if (funcionario.getNumDependentes() > 0) {
                salarioFamilia = 1412 * 0.05 * funcionario.getNumDependentes();
            }

            double auxilioBaba = 0.0;
            if (countMulheresComDependentes > 30) {
                // Calcula o auxílio babá
                auxilioBaba = calcularAuxilioBaba(funcionario);
            }

            // Cria um novo objeto Beneficios com as informações calculadas
            Beneficios beneficios = new Beneficios();
            beneficios.setFuncionario(funcionario);
            beneficios.setValeAlimentacao(valeAlimentacao);
            beneficios.setHorasExtras(valorHorasExtras);
            beneficios.setHorasExtrasFeriadoDomingo(valorHorasExtrasFeriadoDomingo);
            beneficios.setHorasExtrasNoturnas(valorHorasExtrasNoturnas);
            beneficios.setAdicionalInsalubridade(adicionalInsalubridade);
            beneficios.setAdicionalPericulosidade(adicionalPericulosidade);
            beneficios.setSalarioFamilia(salarioFamilia);
            beneficios.setAuxilioBaba(auxilioBaba);

            // Salva o objeto Beneficios no banco de dados
            Beneficios savedBeneficios = beneficiosRepo.save(beneficios);

            return ResponseEntity.ok(savedBeneficios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Método para calcular hora extra
    private double calcularHoraExtra(double salarioBase, int horasExtras, boolean feriadoOuDomingo, boolean noturna) {
        if (noturna) {
            return (salarioBase / 30 / 8) * horasExtras * 1.2;
        } else if (feriadoOuDomingo) {
            return (salarioBase / 30 / 8) * horasExtras * 2;
        } else {
            return (salarioBase / 30 / 8) * horasExtras * 1.5;
        }
    }

    public double calcularAuxilioBaba(Funcionario funcionario) {
        LocalDate dataNascimento = LocalDate.parse(funcionario.getDataNascimento());

        // Calcula a idade do funcionário usando a data atual
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        int idade = periodo.getYears();

        // Verifica se o funcionário é do sexo feminino e tem mais de 16 anos
        if (funcionario.getSexo().equalsIgnoreCase("feminino") && idade > 16) {
            // Verifica se o número de dependentes é maior que zero
            if (funcionario.getNumDependentesSix() > 0) {
                // Calcula o valor do auxílio babá
                return 0.05 * 1412 * funcionario.getNumDependentesSix();
            }
        }
        return 0; // Retorna zero se não aplicável
    }
}