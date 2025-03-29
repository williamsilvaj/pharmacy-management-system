package com.farmacia.pharma_manager.backend.fornecedor;

import com.farmacia.pharma_manager.backend.endereco.Endereco;
import com.farmacia.pharma_manager.backend.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private EnderecoService enderecoService;

    // Criar um novo Fornecedor
    @PostMapping
    public ResponseEntity<Fornecedor> criarFornecedor(@RequestBody Fornecedor fornecedor) {
        Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(fornecedor.getEndereco().getIdEndereco());

        if (!enderecoOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o Endereco não existir
        }

        fornecedor.setEndereco(enderecoOptional.get());
        Fornecedor fornecedorSalvo = fornecedorService.salvar(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
    }

    // Obter todos os Fornecedores
    @GetMapping
    public List<Fornecedor> listarTodos() {
        return fornecedorService.listarTodos();
    }

    // Obter um Fornecedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Integer id) {
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
        return fornecedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um Fornecedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Integer id, @RequestBody Fornecedor fornecedor) {
        Optional<Fornecedor> fornecedorExistente = fornecedorService.buscarPorId(id);

        if (!fornecedorExistente.isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o Fornecedor não for encontrado
        }

        Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(fornecedor.getEndereco().getIdEndereco());
        if (!enderecoOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o Endereco não existir
        }

        fornecedor.setIdFornecedor(id);
        fornecedor.setEndereco(enderecoOptional.get());
        Fornecedor fornecedorAtualizado = fornecedorService.atualizar(fornecedor);

        return ResponseEntity.ok(fornecedorAtualizado);
    }

    // Deletar um Fornecedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Integer id) {
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);

        if (!fornecedor.isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o Fornecedor não for encontrado
        }

        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
