package com.calculadora.father.demo.repositories;

import com.calculadora.father.demo.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface FuncionarioRepo extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByEmpregadorId(Long empregadorId);

    List<Funcionario> findByNomeCompleto(String nome);

    @Query(value = "SELECT COUNT(*) FROM Funcionario f WHERE f.sexo = 'feminino' AND f.num_dependentes_six > 0 " +
            "AND TIMESTAMPDIFF(YEAR, f.data_nascimento, CURDATE()) > 16", nativeQuery = true)
    int countMulheresComMaisDe16AnosComDependentes();
}
