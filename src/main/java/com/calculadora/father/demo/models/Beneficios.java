package com.calculadora.father.demo.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Beneficios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    private boolean valeAlimentacao;
    private double horasExtras;
    private double horasExtrasFeriadoDomingo;
    private double horasExtrasNoturnas;
    private double adicionalInsalubridade;
    private double adicionalPericulosidade;
    private double salarioFamilia;
    private double auxilioBaba;

    // Getters
    public Long getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public boolean isValeAlimentacao() {
        return valeAlimentacao;
    }

    public double getHorasExtras() {
        return horasExtras;
    }

    public double getHorasExtrasFeriadoDomingo() {
        return horasExtrasFeriadoDomingo;
    }

    public double getHorasExtrasNoturnas() {
        return horasExtrasNoturnas;
    }

    public double getAdicionalInsalubridade() {
        return adicionalInsalubridade;
    }

    public double getAdicionalPericulosidade() {
        return adicionalPericulosidade;
    }

    public double getSalarioFamilia() {
        return salarioFamilia;
    }

    public double getAuxilioBaba() {
        return auxilioBaba;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setValeAlimentacao(boolean valeAlimentacao) {
        this.valeAlimentacao = valeAlimentacao;
    }

    public void setHorasExtras(double horasExtras) {
        this.horasExtras = horasExtras;
    }

    public void setHorasExtrasFeriadoDomingo(double horasExtrasFeriadoDomingo) {
        this.horasExtrasFeriadoDomingo = horasExtrasFeriadoDomingo;
    }

    public void setHorasExtrasNoturnas(double horasExtrasNoturnas) {
        this.horasExtrasNoturnas = horasExtrasNoturnas;
    }

    public void setAdicionalInsalubridade(double adicionalInsalubridade) {
        this.adicionalInsalubridade = adicionalInsalubridade;
    }

    public void setAdicionalPericulosidade(double adicionalPericulosidade) {
        this.adicionalPericulosidade = adicionalPericulosidade;
    }

    public void setSalarioFamilia(double salarioFamilia) {
        this.salarioFamilia = salarioFamilia;
    }

    public void setAuxilioBaba(double auxilioBaba) {
        this.auxilioBaba = auxilioBaba;
    }
}
