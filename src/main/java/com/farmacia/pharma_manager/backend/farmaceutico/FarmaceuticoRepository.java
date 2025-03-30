package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FarmaceuticoRepository extends JpaRepository<Farmaceutico, Integer> {
    
    @Query("SELECT f FROM Farmaceutico f WHERE " +
           "LOWER(f.funcionario.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(f.crf) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Farmaceutico> findByNomeOrCrfContaining(@Param("termo") String termo);
}