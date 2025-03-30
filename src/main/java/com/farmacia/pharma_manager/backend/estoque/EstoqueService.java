  package com.farmacia.pharma_manager.backend.estoque;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Service;

  import java.util.List;
  import java.util.Optional;

  @Service
  public class EstoqueService {

      @Autowired
      private EstoqueRepository estoqueRepository;

      public Estoque salvar(Estoque estoque) {
          return estoqueRepository.save(estoque);
      }

      public Optional<Estoque> buscarPorId(Integer id) {
          return estoqueRepository.findById(id);
      }

      public List<Estoque> listarTodos() {
          return estoqueRepository.findAll();
      }

      public Estoque atualizar(Estoque estoque) {
          return estoqueRepository.save(estoque);
      }

      public void deletar(Integer id) {
          estoqueRepository.deleteById(id);
      }
  }
