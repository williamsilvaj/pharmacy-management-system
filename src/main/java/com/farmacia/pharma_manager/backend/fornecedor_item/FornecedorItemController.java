package com.farmacia.pharma_manager.backend.fornecedor_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedor-items")
public class FornecedorItemController {

    @Autowired
    private FornecedorItemService fornecedorItemService;

    // Criar ou atualizar um fornecedor_item
    @PostMapping
    public FornecedorItem createFornecedorItem(@RequestBody FornecedorItem fornecedorItem) {
        return fornecedorItemService.saveFornecedorItem(fornecedorItem);
    }

    // Obter todos os fornecedor_item
    @GetMapping
    public List<FornecedorItem> getAllFornecedorItems() {
        return fornecedorItemService.getAllFornecedorItems();
    }

    // Obter fornecedor_item por ID
    @GetMapping("/{id}")
    public Optional<FornecedorItem> getFornecedorItemById(@PathVariable Integer id) {
        return fornecedorItemService.getFornecedorItemById(id);
    }

    // Deletar fornecedor_item por ID
    @DeleteMapping("/{id}")
    public void deleteFornecedorItem(@PathVariable Integer id) {
        fornecedorItemService.deleteFornecedorItem(id);
    }
}
