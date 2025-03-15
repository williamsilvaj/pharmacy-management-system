package com.farmacia.pharma_manager.backend.fornecedor;
import java.util.ArrayList;
import java.util.List;

public class ControleFornecedor {

    private List<Fornecedor> listaFornecedores;

    //construtor

    public ControleFornecedor() {
        this.listaFornecedores = new ArrayList();

    }

    public void cadastrarFornecedor(String nome, String cnpj, String email, boolean status){
        Fornecedor fornecedor = new Fornecedor(nome, cnpj, email, status);
        listaFornecedores.add(fornecedor);
        System.out.println("Fornecedor cadastrado com sucesso: " + fornecedor);
    }

    public void alterarFornecedor(String cnpj, String novoNome, String novoEmail, boolean novoStatus) {
        for (Fornecedor f : listaFornecedores) {
            if (f.getCnpj().equals(cnpj)) {
                f.setNome(novoNome);
                f.setEmail(novoEmail);
                f.setStatus(novoStatus);
                System.out.println("Fornecedor alterado com sucesso: " + f);
                return;
            }
        }
        System.out.println("Fornecedor não encontrado.");
    }

    // Remover um fornecedor
    public void removerFornecedor(String cnpj) {
        for (Fornecedor f : listaFornecedores) {
            if (f.getCnpj().equals(cnpj)) {
                listaFornecedores.remove(f);
                System.out.println("Fornecedor removido com sucesso.");
                return;
            }
        }
        System.out.println("Fornecedor não encontrado.");
    }

    public void consultarFornecedor(String cnpj) {
        for (Fornecedor f : listaFornecedores) {
            if (f.getCnpj().equals(cnpj)) {
                System.out.println("Fornecedor encontrado: " + f);
                return;
            }
        }
        System.out.println("Fornecedor não encontrado.");
    }

    //lista todos os Fornecedores
    public void listarFornecedores() {
        if (listaFornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            System.out.println("Lista de Fornecedores:");
            for (Fornecedor f : listaFornecedores) {
                System.out.println(f);
            }
        }
    }


}
