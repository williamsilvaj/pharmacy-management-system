package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farmaceuticos")
public class FarmaceuticoController {

  @Autowired
  private FarmaceuticoService farmaceuticoService;

  @PostMapping
  public ResponseEntity<Farmaceutico> criarFarmaceutico(@RequestBody Farmaceutico farmaceutico) {
    Farmaceutico novoFarmaceutico = farmaceuticoService.criarFarmaceutico(farmaceutico);
    return new ResponseEntity<>(novoFarmaceutico, HttpStatus.CREATED);
  }

  @GetMapping
  public List<Farmaceutico> obterTodosFarmaceuticos() {
    return farmaceuticoService.obterTodosFarmaceuticos();
  }
  
  @GetMapping("/busca/{termo}")
    public ResponseEntity<List<Farmaceutico>> buscarPorNomeOuCrf(@PathVariable String termo) {
		List<Farmaceutico> farmaceuticos = farmaceuticoService.buscarPorNomeOuCrf(termo);
        return ResponseEntity.ok(farmaceuticos);
    }

  @GetMapping("/{id}")
  public ResponseEntity<Farmaceutico> obterFarmaceuticoPorId(@PathVariable Integer id) {
    Optional<Farmaceutico> farmaceutico = farmaceuticoService.obterFarmaceuticoPorId(id);
    return farmaceutico.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Farmaceutico> atualizarFarmaceutico(@PathVariable Integer id, @RequestBody Farmaceutico farmaceutico) {
    try {
      Farmaceutico farmaceuticoAtualizado = farmaceuticoService.atualizarFarmaceutico(id, farmaceutico);
      return new ResponseEntity<>(farmaceuticoAtualizado, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarFarmaceutico(@PathVariable Integer id) {
    farmaceuticoService.deletarFarmaceutico(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
