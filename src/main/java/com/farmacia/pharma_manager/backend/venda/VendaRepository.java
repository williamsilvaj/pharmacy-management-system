package com.farmacia.pharma_manager.backend.venda;

import com.farmacia.pharma_manager.backend.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}