package com.calculadora.father.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empregador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;
    private String nomeEmpresa;
    private String endereco;
    private String contatoEmail;
    private String contatoTelefone;

    // Construtor vazio necessário para JPA
    public Empregador() {
    }

    // Construtor com todos os campos
    public Empregador(String cnpj, String nomeEmpresa, String endereco, String contatoEmail, String contatoTelefone) {
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.endereco = endereco;
        this.contatoEmail = contatoEmail;
        this.contatoTelefone = contatoTelefone;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContatoEmail() {
        return contatoEmail;
    }

    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
    }

    public String getContatoTelefone() {
        return contatoTelefone;
    }

    public void setContatoTelefone(String contatoTelefone) {
        this.contatoTelefone = contatoTelefone;
    }

    public static Empregador fromCnpjString(String cnpj) {
        return new Empregador(cnpj, null, null, null, null);
    }
}
