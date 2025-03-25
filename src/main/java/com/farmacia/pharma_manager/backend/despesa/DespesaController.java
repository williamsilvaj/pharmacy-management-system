package com.farmacia.pharma_manager.backend.despesa;

import com.farmacia.pharma_manager.backend.despesa.Despesa;
import com.farmacia.pharma_manager.backend.despesa.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

  private final DespesaService despesaService;

  public DespesaController(DespesaService despesaService) {
    this.despesaService = despesaService;
  }

  @PostMapping
  public ResponseEntity<Despesa> createOrUpdateDespesa(@RequestBody Despesa despesa) {
    Despesa savedDespesa = despesaService.saveOrUpdateDespesa(despesa);
    return new ResponseEntity<>(savedDespesa, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Despesa>> getAllDespesas() {
    List<Despesa> despesas = despesaService.getAllDespesas();
    return new ResponseEntity<>(despesas, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Despesa> getDespesaById(@PathVariable Integer id) {
    Optional<Despesa> despesa = despesaService.getDespesaById(id);
    return despesa.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDespesa(@PathVariable Integer id) {
    despesaService.deleteDespesa(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
