package com.farmacia.pharma_manager.backend.despesa;

import com.farmacia.pharma_manager.backend.despesa.Despesa;
import com.farmacia.pharma_manager.backend.despesa.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

  private final DespesaRepository despesaRepository;

  public DespesaService(DespesaRepository despesaRepository) {
    this.despesaRepository = despesaRepository;
  }

  public Despesa saveOrUpdateDespesa(Despesa despesa) {
    return despesaRepository.save(despesa);
  }

  public List<Despesa> getAllDespesas() {
    return despesaRepository.findAll();
  }

  public Optional<Despesa> getDespesaById(Integer id) {
    return despesaRepository.findById(id);
  }

  public void deleteDespesa(Integer id) {
    despesaRepository.deleteById(id);
  }
}
