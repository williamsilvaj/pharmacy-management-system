package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaceuticoRepository extends JpaRepository <Farmaceutico, Integer> {
}
