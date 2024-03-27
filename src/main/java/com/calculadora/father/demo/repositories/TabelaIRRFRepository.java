package com.calculadora.father.demo.repositories;

import com.calculadora.father.demo.models.TabelaIRRF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaIRRFRepository extends JpaRepository<TabelaIRRF, Long> {
    // Se necessário, adicione métodos personalizados de consulta aqui
}
