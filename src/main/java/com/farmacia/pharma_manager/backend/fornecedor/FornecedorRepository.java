package com.farmacia.pharma_manager.backend.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByCnpj(String cnpj); // Buscar fornecedor pelo CNPJ
    Optional<Fornecedor> findByNome(String nome); // Buscar fornecedor pelo nome
}
