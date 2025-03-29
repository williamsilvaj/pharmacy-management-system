package com.farmacia.pharma_manager.backend.despesa;

import com.farmacia.pharma_manager.backend.gerente.GerenteService;
import com.farmacia.pharma_manager.backend.gerente.Gerente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/despesas")
public class DespesaController {

  private final DespesaService despesaService;
  private final GerenteService gerenteService;

  public DespesaController(DespesaService despesaService, GerenteService gerenteService) {
    this.despesaService = despesaService;
    this.gerenteService = gerenteService;
  }

  @GetMapping("/pagina")
  public String redirecionarParaDespesaPage() {
    return "despesa/despesa";
  }


  @GetMapping
  public ResponseEntity<List<Despesa>> listarDespesas() {
    try {
      List<Despesa> despesas = despesaService.listarTodas();
      return ResponseEntity.ok(despesas);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Despesa> buscarPorId(@PathVariable Integer id) {
    Optional<Despesa> despesa = despesaService.buscarPorId(id);
    return despesa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<?> adicionarDespesa(@RequestBody Despesa despesa) {
    // Recupera o gerente pelo ID
    Gerente gerente = gerenteService.obterGerentePorId(despesa.getGerente().getId())
      .orElse(null);

    if (gerente == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gerente não encontrado");
    }

    // Salvar a despesa com o gerente
    despesa.setGerente(gerente);
    despesaService.salvar(despesa);

    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Despesa> atualizar(@PathVariable Integer id, @RequestBody Despesa despesa) {
    try {
      return ResponseEntity.ok(despesaService.atualizar(id, despesa));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Integer id) {
    despesaService.deletar(id);
    return ResponseEntity.noContent().build();
  }
}
