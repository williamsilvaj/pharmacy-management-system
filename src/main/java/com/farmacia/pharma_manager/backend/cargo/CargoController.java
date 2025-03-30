package com.farmacia.pharma_manager.backend.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cargos")
public class CargoController {

  @Autowired
  private CargoService cargoService;

  // Endpoint para criar um cargo
  @PostMapping
  public ResponseEntity<Cargo> criarCargo(@RequestBody Cargo cargo) {
    Cargo novoCargo = cargoService.criarCargo(cargo.getTitulo(), cargo.getDataContratacao(), cargo.getSalario());
    return new ResponseEntity<>(novoCargo, HttpStatus.CREATED);
  }

  // Endpoint para obter todos os cargos
  @GetMapping
  public ResponseEntity<List<Cargo>> obterTodosCargos() {
    List<Cargo> cargos = cargoService.obterTodosCargos();
    return new ResponseEntity<>(cargos, HttpStatus.OK);
  }

  // Endpoint para obter um cargo pelo ID
  @GetMapping("/{id}")
  public ResponseEntity<Cargo> obterCargoPorId(@PathVariable Integer id) {
    Optional<Cargo> cargo = cargoService.obterCargoPorId(id);
    return cargo.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Endpoint para atualizar um cargo
  @PutMapping("/{id}")
  public ResponseEntity<Cargo> atualizarCargo(@PathVariable Integer id, @RequestBody Cargo novoCargo) {
    try {
      Cargo cargoAtualizado = cargoService.atualizarCargo(id, novoCargo);
      return new ResponseEntity<>(cargoAtualizado, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Endpoint para deletar um cargo
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCargo(@PathVariable Integer id) {
    cargoService.deletarCargo(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
