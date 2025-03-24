package com.farmacia.pharma_manager.backend.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")  // URL base para as operações de Endereco
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Endpoint para listar todos os endereços
    @GetMapping
    public List<Endereco> listarTodos() {
        return enderecoService.listarTodos();
    }

    // Endpoint para buscar um endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Integer id) {
        Optional<Endereco> endereco = enderecoService.buscarPorId(id);
        return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvar(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    // Endpoint para atualizar um endereço existente
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {
        if (enderecoService.buscarPorId(id).isPresent()) {
            endereco.setIdEndereco(id);
            Endereco enderecoAtualizado = enderecoService.atualizar(endereco);
            return ResponseEntity.ok(enderecoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para deletar um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        if (enderecoService.buscarPorId(id).isPresent()) {
            enderecoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
