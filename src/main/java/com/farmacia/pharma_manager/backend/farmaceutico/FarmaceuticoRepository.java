package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para realizar operações de banco de dados na tabela "farmaceutico".
 * Estende JpaRepository para fornecer métodos básicos como salvar, excluir e buscar.
 */
public interface FarmaceuticoRepository extends JpaRepository<Farmaceutico, Integer> {
    // Métodos adicionais de consulta podem ser adicionados aqui, caso necessário
}
