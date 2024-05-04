package com.calculadora.father.demo.models;

public class CalculoRemuneracaoDTO {
    private Funcionario funcionario;
    private Beneficios beneficios;
    private Descontos descontos;
    private double salarioTotal;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Beneficios getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(Beneficios beneficios) {
        this.beneficios = beneficios;
    }

    public Descontos getDescontos() {
        return descontos;
    }

    public void setDescontos(Descontos descontos) {
        this.descontos = descontos;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }
}
