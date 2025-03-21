package com.farmacia.pharma_manager.backend.gerente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gerentes")
public class GerenteController {

    private final GerenteService gerenteService;

    @Autowired
    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    // criar ou atualizar um Gerente
    @PostMapping
    public ResponseEntity<Gerente> saveOrUpdateGerente(@RequestBody Gerente gerente) {
        Gerente savedGerente = gerenteService.saveOrUpdateGerente(gerente);
        return new ResponseEntity<>(savedGerente, HttpStatus.CREATED);
    }

    // listar todos os Gerentes
    @GetMapping
    public ResponseEntity<List<Gerente>> getAllGerentes() {
        List<Gerente> gerentes = gerenteService.getAllGerentes();
        return new ResponseEntity<>(gerentes, HttpStatus.OK);
    }

    // buscar um Gerente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Gerente> getGerenteById(@PathVariable Long id) {
        Optional<Gerente> gerente = gerenteService.getGerenteById(id);
        return gerente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // deletar um Gerente pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGerente(@PathVariable Long id) {
        Optional<Gerente> gerente = gerenteService.getGerenteById(id);
        if (gerente.isPresent()) {
            gerenteService.deleteGerente(id);
            return ResponseEntity.noContent().build();  // sucessoo
        } else {
            return ResponseEntity.notFound().build();  // não encontrado
        }
    }
}
