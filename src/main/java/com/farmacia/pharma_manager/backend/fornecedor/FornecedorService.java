package com.farmacia.pharma_manager.backend.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    // Método para cadastrar um novo fornecedor
    public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    // Método para consultar fornecedor por CNPJ
    public Optional<Fornecedor> consultarFornecedorPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj);
    }

    // Método para consultar fornecedor por nome
    public Optional<Fornecedor> consultarFornecedorPorNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }

    // Método para listar todos os fornecedores
    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.findAll();
    }

    // Método para alterar os dados de um fornecedor
    public Fornecedor alterarFornecedor(Long id, Fornecedor fornecedorAlterado) {
        Optional<Fornecedor> fornecedorExistente = fornecedorRepository.findById(id);
        if (fornecedorExistente.isPresent()) {
            Fornecedor fornecedor = fornecedorExistente.get();
            fornecedor.setNome(fornecedorAlterado.getNome());
            fornecedor.setCnpj(fornecedorAlterado.getCnpj());
            fornecedor.setEmail(fornecedorAlterado.getEmail());
            fornecedor.setStatus(fornecedorAlterado.getStatus());
            return fornecedorRepository.save(fornecedor);
        }
        return null; // Caso o fornecedor não seja encontrado
    }

    // Método para remover um fornecedor
    public boolean removerFornecedor(Long id) {
        if (fornecedorRepository.existsById(id)) {
            fornecedorRepository.deleteById(id);
            return true;
        }
        return false; // Retorna falso caso não encontre o fornecedor
    }
}
