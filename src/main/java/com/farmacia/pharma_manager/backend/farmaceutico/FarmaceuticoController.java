package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controlador de endpoints para operações relacionadas aos farmacêuticos.
 * Oferece endpoints para criar, obter, atualizar e deletar farmacêuticos.
 */
@RestController
@RequestMapping("/farmaceuticos")
public class FarmaceuticoController {

    @Autowired
    private FarmaceuticoService farmaceuticoService;

    /**
     * Endpoint para criar um novo farmacêutico.
     *
     * @param farmaceutico o farmacêutico a ser criado
     * @return a resposta com o farmacêutico criado
     */
    @PostMapping
    public ResponseEntity<Farmaceutico> criarFarmaceutico(@RequestBody Farmaceutico farmaceutico) {
        Farmaceutico novoFarmaceutico = farmaceuticoService.criarFarmaceutico(farmaceutico);
        return new ResponseEntity<>(novoFarmaceutico, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obter um farmacêutico pelo seu ID.
     *
     * @param id o ID do farmacêutico a ser obtido
     * @return o farmacêutico encontrado ou uma resposta 404 caso não exista
     */
    @GetMapping("/{id}")
    public ResponseEntity<Farmaceutico> obterFarmaceuticoPorId(@PathVariable Integer id) {
        Optional<Farmaceutico> farmaceutico = farmaceuticoService.obterFarmaceuticoPorId(id);
        return farmaceutico.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint para atualizar um farmacêutico existente.
     *
     * @param id o ID do farmacêutico a ser atualizado
     * @param farmaceutico os novos dados para o farmacêutico
     * @return a resposta com o farmacêutico atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Farmaceutico> atualizarFarmaceutico(@PathVariable Integer id, @RequestBody Farmaceutico farmaceutico) {
        Farmaceutico farmaceuticoAtualizado = farmaceuticoService.atualizarFarmaceutico(id, farmaceutico);
        if (farmaceuticoAtualizado != null) {
            return new ResponseEntity<>(farmaceuticoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para deletar um farmacêutico pelo seu ID.
     *
     * @param id o ID do farmacêutico a ser deletado
     * @return uma resposta indicando o sucesso ou falha da operação
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFarmaceutico(@PathVariable Integer id) {
        farmaceuticoService.deletarFarmaceutico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
