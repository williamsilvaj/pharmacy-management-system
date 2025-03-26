package com.farmacia.pharma_manager.backend.gerente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    /**
     * Endpoint to create a new Gerente.
     *
     * @param gerente the Gerente object to create
     * @return the created Gerente object
     */
    @PostMapping
    public ResponseEntity<Gerente> criarGerente(@RequestBody Gerente gerente) {
        Gerente novoGerente = gerenteService.criarGerente(gerente);
        return new ResponseEntity<>(novoGerente, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing Gerente by its ID.
     *
     * @param id      the ID of the Gerente to update
     * @param gerente the Gerente object with updated values
     * @return the updated Gerente object, or an error response if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Gerente> atualizarGerente(@PathVariable Integer id, @RequestBody Gerente gerente) {
        Gerente gerenteAtualizado = gerenteService.atualizarGerente(id, gerente);
        if (gerenteAtualizado != null) {
            return new ResponseEntity<>(gerenteAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to get a Gerente by its ID.
     *
     * @param id the ID of the Gerente to retrieve
     * @return the Gerente object if found, or a 404 not found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Gerente> obterGerentePorId(@PathVariable Integer id) {
        Optional<Gerente> gerente = gerenteService.obterGerentePorId(id);
        return gerente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to delete a Gerente by its ID.
     *
     * @param id the ID of the Gerente to delete
     * @return a response indicating if the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGerente(@PathVariable Integer id) {
        gerenteService.deletarGerente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
