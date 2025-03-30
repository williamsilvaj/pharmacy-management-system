package com.farmacia.pharma_manager.backend.despesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
    List<Despesa> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
