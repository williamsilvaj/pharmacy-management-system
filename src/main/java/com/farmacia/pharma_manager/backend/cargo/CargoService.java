package com.farmacia.pharma_manager.backend.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

  @Autowired
  private CargoRepository cargoRepository;

  public Cargo criarCargo(String titulo, LocalDate dataContratacao, Double salario) {
    Cargo cargo = new Cargo(titulo, dataContratacao, salario);
    return cargoRepository.save(cargo);
  }

  public List<Cargo> obterTodosCargos() {
    return cargoRepository.findAll();
  }

  public Optional<Cargo> obterCargoPorId(Integer id) {
    return cargoRepository.findById(id);
  }

  public Cargo atualizarCargo(Integer id, Cargo cargoAtualizado) {
    Optional<Cargo> cargoExistente = cargoRepository.findById(id);

    if (cargoExistente.isPresent()) {
      Cargo cargo = cargoExistente.get();

      if (cargoAtualizado.getTitulo() != null && !cargoAtualizado.getTitulo().isEmpty()) {
        cargo.setTitulo(cargoAtualizado.getTitulo());
      }
      if (cargoAtualizado.getDataContratacao() != null) {
        cargo.setDataContratacao(cargoAtualizado.getDataContratacao());
      }
      if (cargoAtualizado.getSalario() != null) {
        cargo.setSalario(cargoAtualizado.getSalario());
      }

      return cargoRepository.save(cargo);
    } else {
      throw new IllegalArgumentException("Cargo não encontrado para o ID: " + id);
    }
  }

  public void deletarCargo(Integer id) {
    Optional<Cargo> cargo = cargoRepository.findById(id);
    cargo.ifPresent(value -> cargoRepository.delete(value));
  }

}
