package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmaceuticos")
public class FarmaceuticoController {

    private final FarmaceuticoService farmaceuticoService;

    @Autowired
    public FarmaceuticoController(FarmaceuticoService farmaceuticoService) {
        this.farmaceuticoService = farmaceuticoService;
    }

    // criar ou atualizar um Farmaceutico
    @PostMapping
    public ResponseEntity<Farmaceutico> saveOrUpdateFarmaceutico(@RequestBody Farmaceutico farmaceutico) {
        Farmaceutico savedFarmaceutico = farmaceuticoService.saveOrUpdateFarmaceutico(farmaceutico);
        return new ResponseEntity<>(savedFarmaceutico, HttpStatus.CREATED);
    }

    //  listar todos os Farmaceuticos
    @GetMapping
    public ResponseEntity<List<Farmaceutico>> getAllFarmaceuticos() {
        List<Farmaceutico> farmaceuticos = farmaceuticoService.getAllFarmaceuticos();
        return new ResponseEntity<>(farmaceuticos, HttpStatus.OK);
    }

    // buscar um Farmaceutico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Farmaceutico> getFarmaceuticoById(@PathVariable Integer id) {
        Optional<Farmaceutico> farmaceutico = farmaceuticoService.getFarmaceuticoById(id);
        return farmaceutico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // deletar um Farmaceutico pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmaceutico(@PathVariable Integer id) {
        Optional<Farmaceutico> farmaceutico = farmaceuticoService.getFarmaceuticoById(id);
        if (farmaceutico.isPresent()) {
            farmaceuticoService.deleteFarmaceutico(id);
            return ResponseEntity.noContent().build();  // encontrado
        } else {
            return ResponseEntity.notFound().build();  //não encontrado
        }
    }
}
