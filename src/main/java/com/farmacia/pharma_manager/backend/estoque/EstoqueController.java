package com.farmacia.pharma_manager.backend.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    // Endpoint para listar todos os estoques
    @GetMapping
    public List<Estoque> listarTodos() {
        return estoqueService.listarTodos();
    }

    // Endpoint para buscar um estoque por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Integer id) {
        Optional<Estoque> estoque = estoqueService.buscarPorId(id);
        return estoque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo estoque
    @PostMapping
    public ResponseEntity<Estoque> criarEstoque(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.salvar(estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstoque);
    }

    // Endpoint para atualizar um estoque existente
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Integer id, @RequestBody Estoque estoque) {
        if (estoqueService.buscarPorId(id).isPresent()) {
            estoque.setIdEstoque(id);
            Estoque estoqueAtualizado = estoqueService.atualizar(estoque);
            return ResponseEntity.ok(estoqueAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para deletar um estoque
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Integer id) {
        if (estoqueService.buscarPorId(id).isPresent()) {
            estoqueService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
