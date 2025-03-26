package com.farmacia.pharma_manager.backend.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor atualizar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deletar(Integer id) {
        fornecedorRepository.deleteById(id);
    }
}
