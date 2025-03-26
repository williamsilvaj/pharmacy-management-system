package com.farmacia.pharma_manager.backend.cargo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for interacting with the Cargo table.
 */
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
