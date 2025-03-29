package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FarmaceuticoService {

  @Autowired
  private FarmaceuticoRepository farmaceuticoRepository;

  public Farmaceutico criarFarmaceutico(Farmaceutico farmaceutico) {
    return farmaceuticoRepository.save(farmaceutico);
  }

  public List<Farmaceutico> obterTodosFarmaceuticos() {
    return farmaceuticoRepository.findAll();
  }

  public Optional<Farmaceutico> obterFarmaceuticoPorId(Integer id) {
    return farmaceuticoRepository.findById(id);
  }

  public Farmaceutico atualizarFarmaceutico(Integer id, Farmaceutico farmaceuticoAtualizado) {
    Optional<Farmaceutico> farmaceuticoExistente = farmaceuticoRepository.findById(id);
    if (farmaceuticoExistente.isPresent()) {
      Farmaceutico farmaceutico = farmaceuticoExistente.get();
      farmaceutico.setNome(farmaceuticoAtualizado.getNome());
      farmaceutico.setTelefone(farmaceuticoAtualizado.getTelefone());
      farmaceutico.setCpf(farmaceuticoAtualizado.getCpf());
      farmaceutico.setCargo(farmaceuticoAtualizado.getCargo());
      farmaceutico.setEndereco(farmaceuticoAtualizado.getEndereco());
      farmaceutico.setTurno(farmaceuticoAtualizado.getTurno());
      farmaceutico.setCrf(farmaceuticoAtualizado.getCrf());
      farmaceutico.setCargaHoraria(farmaceuticoAtualizado.getCargaHoraria());
      return farmaceuticoRepository.save(farmaceutico);
    } else {
      throw new IllegalArgumentException("Farmaceutico não encontrado para o id: " + id);
    }
  }

  public void deletarFarmaceutico(Integer id) {
    farmaceuticoRepository.deleteById(id);
  }
  
  public List<Farmaceutico> buscarPorNomeOuCrf(String termo) {
	return farmaceuticoRepository.findByNomeOrCrfContaining(termo);
  }

}
