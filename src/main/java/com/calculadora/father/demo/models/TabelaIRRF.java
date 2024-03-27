package com.calculadora.father.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TabelaIRRF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double faixaInicial;
    private double faixaFinal;
    private double aliquota;

    // Getters
    public Long getId() {
        return id;
    }

    public double getFaixaInicial() {
        return faixaInicial;
    }

    public double getFaixaFinal() {
        return faixaFinal;
    }

    public double getAliquota() {
        return aliquota;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFaixaInicial(double faixaInicial) {
        this.faixaInicial = faixaInicial;
    }

    public void setFaixaFinal(double faixaFinal) {
        this.faixaFinal = faixaFinal;
    }

    public void setAliquota(double aliquota) {
        this.aliquota = aliquota;
    }
}
