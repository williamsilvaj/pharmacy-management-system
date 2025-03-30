package com.farmacia.pharma_manager.backend.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // JpaRepository fornece implementações padrão de CRUD para Produto
	List<Produto> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
}
