package com.farmacia.pharma_manager.backend.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ControleFornecedor {

    private final FornecedorService fornecedorService;

    @Autowired
    public ControleFornecedor(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    // Cadastrar um fornecedor
    public void cadastrarFornecedor(String nome, String cnpj, String email, boolean status) {
        Fornecedor fornecedor = new Fornecedor(nome, cnpj, email, status);
        fornecedorService.cadastrarFornecedor(fornecedor);
        System.out.println("Fornecedor cadastrado com sucesso: " + fornecedor);
    }

    // Alterar os dados de um fornecedor
    public void alterarFornecedor(String cnpj, String novoNome, String novoEmail, boolean novoStatus) {
        Optional<Fornecedor> fornecedorOpt = fornecedorService.consultarFornecedorPorCnpj(cnpj);
        if (fornecedorOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorOpt.get();
            fornecedor.setNome(novoNome);
            fornecedor.setEmail(novoEmail);
            fornecedor.setStatus(novoStatus);
            fornecedorService.cadastrarFornecedor(fornecedor); // Atualiza o fornecedor no banco
            System.out.println("Fornecedor alterado com sucesso: " + fornecedor);
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    // Remover um fornecedor
    public void removerFornecedor(String cnpj) {
        Optional<Fornecedor> fornecedorOpt = fornecedorService.consultarFornecedorPorCnpj(cnpj);
        if (fornecedorOpt.isPresent()) {
            fornecedorService.removerFornecedor(fornecedorOpt.get().getId());
            System.out.println("Fornecedor removido com sucesso.");
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    // Consultar um fornecedor pelo CNPJ
    public void consultarFornecedor(String cnpj) {
        Optional<Fornecedor> fornecedorOpt = fornecedorService.consultarFornecedorPorCnpj(cnpj);
        if (fornecedorOpt.isPresent()) {
            System.out.println("Fornecedor encontrado: " + fornecedorOpt.get());
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
    }

    // Listar todos os fornecedores
    public void listarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.listarFornecedores();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            System.out.println("Lista de Fornecedores:");
            fornecedores.forEach(f -> System.out.println(f));
        }
    }
}
