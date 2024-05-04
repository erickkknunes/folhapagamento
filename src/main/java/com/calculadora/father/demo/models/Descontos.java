package com.calculadora.father.demo.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Descontos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double fgts;
    private double valeTransporte;
    private double valeAlimentacao;
    private double contribuicaoSindical;
    private double faltasAtrasos;
    private double irrf;
    private double inss;
    private LocalDate dataFolhaPagamento;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    // Getters
    public Long getId() {
        return id;
    }

    public double getFgts() {
        return fgts;
    }

    public double getValeTransporte() {
        return valeTransporte;
    }

    public double getValeAlimentacao() {
        return valeAlimentacao;
    }

    public double getContribuicaoSindical() {
        return contribuicaoSindical;
    }

    public double getFaltasAtrasos() {
        return faltasAtrasos;
    }

    public double getIrrf() {
        return irrf;
    }

    public double getInss() {
        return inss;
    }

    public LocalDate getDataFolhaPagamento() {
        return dataFolhaPagamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFgts(double fgts) {
        this.fgts = fgts;
    }

    public void setValeTransporte(double valeTransporte) {
        this.valeTransporte = valeTransporte;
    }

    public void setValeAlimentacao(double valeAlimentacao) {
        this.valeAlimentacao = valeAlimentacao;
    }

    public void setContribuicaoSindical(double contribuicaoSindical) {
        this.contribuicaoSindical = contribuicaoSindical;
    }

    public void setFaltasAtrasos(double faltasAtrasos) {
        this.faltasAtrasos = faltasAtrasos;
    }

    public void setIrrf(double irrf) {
        this.irrf = irrf;
    }

    public void setInss(double inss) {
        this.inss = inss;
    }

    public void setDataFolhaPagamento(LocalDate dataFolhaPagamento) {
        this.dataFolhaPagamento = dataFolhaPagamento;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}