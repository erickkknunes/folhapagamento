package com.calculadora.father.demo.models;

import jakarta.persistence.Entity;
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
    private LocalDate dataFolhaPagamento; // Adiciona o campo de data da folha de pagamento

    public Descontos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFgts() {
        return fgts;
    }

    public void setFgts(double fgts) {
        this.fgts = fgts;
    }

    public double getValeTransporte() {
        return valeTransporte;
    }

    public void setValeTransporte(double valeTransporte) {
        this.valeTransporte = valeTransporte;
    }

    public double getValeAlimentacao() {
        return valeAlimentacao;
    }

    public void setValeAlimentacao(double valeAlimentacao) {
        this.valeAlimentacao = valeAlimentacao;
    }

    public double getContribuicaoSindical() {
        return contribuicaoSindical;
    }

    public void setContribuicaoSindical(double contribuicaoSindical) {
        this.contribuicaoSindical = contribuicaoSindical;
    }

    public double getFaltasAtrasos() {
        return faltasAtrasos;
    }

    public void setFaltasAtrasos(double faltasAtrasos) {
        this.faltasAtrasos = faltasAtrasos;
    }

    public double getIrrf() {
        return irrf;
    }

    public void setIrrf(double irrf) {
        this.irrf = irrf;
    }

    public LocalDate getDataFolhaPagamento() {
        return dataFolhaPagamento;
    }

    public void setDataFolhaPagamento(LocalDate dataFolhaPagamento) {
        this.dataFolhaPagamento = dataFolhaPagamento;
    }
}
