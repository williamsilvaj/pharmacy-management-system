package com.farmacia.pharma_manager.backend.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByTelefone(String telefone);
	List<Cliente> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
}
