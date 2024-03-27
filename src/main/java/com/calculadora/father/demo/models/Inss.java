package com.calculadora.father.demo.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal faixaSalarial;
    private BigDecimal aliquota;
    private BigDecimal valorFixo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFaixaSalarial() {
        return faixaSalarial;
    }

    public void setFaixaSalarial(BigDecimal faixaSalarial) {
        this.faixaSalarial = faixaSalarial;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getValorFixo() {
        return valorFixo;
    }

    public void setValorFixo(BigDecimal valorFixo) {
        this.valorFixo = valorFixo;
    }
}
