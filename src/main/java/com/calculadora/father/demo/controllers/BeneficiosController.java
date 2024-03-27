package com.calculadora.father.demo.controllers;

public class BeneficiosController {

    // Método para calcular hora extra
    public double calcularHoraExtra(double salarioBase, int horasExtras, boolean feriadoOuDomingo) {
        if (feriadoOuDomingo) {
            return (salarioBase / 30 / 8) * horasExtras * 2;
        } else {
            return (salarioBase / 30 / 8) * horasExtras * 1.5;
        }
    }

}
