package com.farmacia.pharma_manager.backend.fornecedor_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorItemService {

    @Autowired
    private FornecedorItemRepository fornecedorItemRepository;

    // Criar ou atualizar um fornecedor_item
    public FornecedorItem saveFornecedorItem(FornecedorItem fornecedorItem) {
        return fornecedorItemRepository.save(fornecedorItem);
    }

    // Obter todos os fornecedor_item
    public List<FornecedorItem> getAllFornecedorItems() {
        return fornecedorItemRepository.findAll();
    }

    // Obter um fornecedor_item por ID
    public Optional<FornecedorItem> getFornecedorItemById(Integer id) {
        return fornecedorItemRepository.findById(id);
    }

    // Deletar um fornecedor_item
    public void deleteFornecedorItem(Integer id) {
        fornecedorItemRepository.deleteById(id);
    }
}
