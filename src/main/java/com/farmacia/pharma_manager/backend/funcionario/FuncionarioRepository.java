package com.farmacia.pharma_manager.backend.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
@Query("SELECT f FROM Funcionario f WHERE f.idFuncionario = :id")
Funcionario buscarPorId(@Param("id") Integer id);
}
