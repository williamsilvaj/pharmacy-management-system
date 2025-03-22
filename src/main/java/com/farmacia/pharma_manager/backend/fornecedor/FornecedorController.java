package com.farmacia.pharma_manager.backend.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    // Endpoint para cadastrar um novo fornecedor
    @PostMapping
    public Fornecedor cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.cadastrarFornecedor(fornecedor);
    }

    // Endpoint para consultar um fornecedor por CNPJ
    @GetMapping("/cnpj/{cnpj}")
    public Optional<Fornecedor> consultarFornecedorPorCnpj(@PathVariable String cnpj) {
        return fornecedorService.consultarFornecedorPorCnpj(cnpj);
    }

    // Endpoint para consultar um fornecedor por nome
    @GetMapping("/nome/{nome}")
    public Optional<Fornecedor> consultarFornecedorPorNome(@PathVariable String nome) {
        return fornecedorService.consultarFornecedorPorNome(nome);
    }

    // Endpoint para listar todos os fornecedores
    @GetMapping
    public List<Fornecedor> listarFornecedores() {
        return fornecedorService.listarFornecedores();
    }

    // Endpoint para alterar dados de um fornecedor
    @PutMapping("/{id}")
    public Fornecedor alterarFornecedor(
            @PathVariable Integer id,
            @RequestBody Fornecedor fornecedor) {
        return fornecedorService.alterarFornecedor(id, fornecedor);
    }

    // Endpoint para remover um fornecedor
    @DeleteMapping("/{id}")
    public String removerFornecedor(@PathVariable Integer id) {
        boolean isRemoved = fornecedorService.removerFornecedor(id);
        return isRemoved ? "Fornecedor removido com sucesso" : "Fornecedor não encontrado";
    }
}
