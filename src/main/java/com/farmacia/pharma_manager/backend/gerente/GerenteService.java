package com.farmacia.pharma_manager.backend.gerente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

  private final GerenteRepository gerenteRepository;

  @Autowired
  public GerenteService(GerenteRepository gerenteRepository) {
    this.gerenteRepository = gerenteRepository;
  }

  // salvar ou atualizar um Gerente
  public Gerente saveOrUpdateGerente(Gerente gerente) {
    return gerenteRepository.save(gerente);
  }

  //listar todos os Gerentes
  public List<Gerente> getAllGerentes() {
    return gerenteRepository.findAll();
  }

  //  buscar um Gerente pelo ID
  public Optional<Gerente> getGerenteById(Integer id) {
    return gerenteRepository.findById(id);
  }

  // deletar um Gerente pelo ID
  public void deleteGerente(Integer id) {
    gerenteRepository.deleteById(id);
  }
}
