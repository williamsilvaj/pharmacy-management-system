package com.farmacia.pharma_manager.backend.fornecedor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private static final Logger logger = LoggerFactory.getLogger(FornecedorService.class);

    public Fornecedor salvar(Fornecedor fornecedor) {
      try {
        logger.info("Salvando fornecedor: {}", fornecedor);
        return fornecedorRepository.save(fornecedor);
      } catch (Exception e) {
        logger.error("Erro ao salvar fornecedor: {}", fornecedor, e);
        throw e; // Re-throw exception para ser capturada pelo controlador
      }
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

  public Fornecedor atualizar(Fornecedor fornecedor) {
    try {
      logger.info("Atualizando fornecedor: {}", fornecedor);
      return fornecedorRepository.save(fornecedor);
    } catch (Exception e) {
      logger.error("Erro ao atualizar fornecedor: {}", fornecedor, e);
      throw e; // Re-throw exception
    }
  }

  public void deletar(Integer id) {
    try {
      logger.info("Deletando fornecedor com ID: {}", id);
      fornecedorRepository.deleteById(id);
    } catch (Exception e) {
      logger.error("Erro ao deletar fornecedor com ID: {}", id, e);
      throw e; // Re-throw exception
    }
  }
}
