package com.calculadora.father.demo.controllers;

import com.calculadora.father.demo.models.Empregador;
import com.calculadora.father.demo.repositories.EmpregadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/juridica")
public class EmpregadorController {

    @Autowired
    private EmpregadorRepo empregadorRepo;

    @GetMapping("/empregador")
    public ResponseEntity<List<Empregador>> getAllEmpregadores() {
        List<Empregador> empregadores = empregadorRepo.findAll();
        if (empregadores.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se não houver empregadores cadastrados
        }
        return ResponseEntity.ok(empregadores);
    }

    @PostMapping("/empregador")
    public ResponseEntity<Empregador> createEmpregador(@RequestBody Empregador empregador) {
        try {
            Empregador savedEmpregador = empregadorRepo.save(empregador);
            return ResponseEntity.ok(savedEmpregador);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build(); // Retorna 400 Bad Request em caso de erro ao salvar o
                                                        // empregador
        }
    }

    @GetMapping("/empregador/{id}")
    public ResponseEntity<Empregador> getEmpregadorById(@PathVariable Long id) {
        Empregador empregador = empregadorRepo.findById(id).orElse(null);
        if (empregador == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o empregador não for encontrado
        }
        return ResponseEntity.ok(empregador);
    }

    @PutMapping("/empregador/{id}")
    public ResponseEntity<Empregador> updateEmpregador(@PathVariable Long id,
            @RequestBody Empregador empregadorAtualizado) {
        Empregador empregador = empregadorRepo.findById(id).orElse(null);
        if (empregador == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o empregador não for encontrado
        }
        empregador.setNomeEmpresa(empregadorAtualizado.getNomeEmpresa());
        empregador.setCnpj(empregadorAtualizado.getCnpj());
        empregador.setEndereco(empregadorAtualizado.getEndereco());
        empregador.setContatoTelefone(empregadorAtualizado.getContatoTelefone());
        empregador.setContatoEmail(empregadorAtualizado.getContatoEmail());
        Empregador updatedEmpregador = empregadorRepo.save(empregador);
        return ResponseEntity.ok(updatedEmpregador);
    }

    @DeleteMapping("/empregador/{id}")
    public ResponseEntity<Void> deleteEmpregador(@PathVariable Long id) {
        empregadorRepo.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content após a exclusão do empregador
    }
}
