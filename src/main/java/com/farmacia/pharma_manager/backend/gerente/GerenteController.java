package com.farmacia.pharma_manager.backend.gerente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @PostMapping
    public ResponseEntity<Gerente> criarGerente(@RequestBody Gerente gerente) {
        Gerente novoGerente = gerenteService.criarGerente(gerente);
        return new ResponseEntity<>(novoGerente, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Gerente> obterTodosGerentes() {
        return gerenteService.obterTodosGerentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> obterGerentePorId(@PathVariable Integer id) {
        Optional<Gerente> gerente = gerenteService.obterGerentePorId(id);
        return gerente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> atualizarGerente(@PathVariable Integer id, @RequestBody Gerente gerente) {
        try {
            Gerente gerenteAtualizado = gerenteService.atualizarGerente(id, gerente);
            return new ResponseEntity<>(gerenteAtualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGerente(@PathVariable Integer id) {
        gerenteService.deletarGerente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
