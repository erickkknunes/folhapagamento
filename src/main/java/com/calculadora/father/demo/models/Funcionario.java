package com.calculadora.father.demo.models;

import jakarta.persistence.*;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String dataNascimento;
    private String dataAdmissao;
    private String cargo;
    private double salarioBase;
    private int numDependentes;
    private int numDependentesSix;
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "empregador_id")
    private Empregador empregador;

    // Getters
    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public int getNumDependentes() {
        return numDependentes;
    }

    public int getNumDependentesSix() {
        return numDependentesSix;
    }

    public String getSexo() {
        return sexo;
    }

    public Empregador getEmpregador() {
        return empregador;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNacimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setNumDependentes(int numDependentes) {
        this.numDependentes = numDependentes;
    }

    public void setNumDependentesSix(int numDependentesSix) {
        this.numDependentesSix = numDependentesSix;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEmpregador(Empregador empregador) {
        this.empregador = empregador;
    }
}
