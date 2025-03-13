package stakeholders;

import java.util.ArrayList;
import java.util.List;

import cadastros.ControleFornecedor;
import cadastros.ControleFuncionario;

public class TestesStakeholders {
    
    public static void main(String[] args) {
        // Criando um objeto Funcionário
        ControleFornecedor controle = new ControleFornecedor();

        // Cadastrar fornecedores
        controle.cadastrarFornecedor("Fornecedor A", "12.345.678/0001-90", "contato@fornecedorA.com", true);
        controle.cadastrarFornecedor("Fornecedor B", "98.765.432/0001-10", "contato@fornecedorB.com", false);

        // Listar fornecedores
        controle.listarFornecedores();

        // Consultar um fornecedor
        controle.consultarFornecedor("12.345.678/0001-90");

        // Alterar dados de um fornecedor
        controle.alterarFornecedor("12.345.678/0001-90", "Fornecedor A Alterado", "novo_email@fornecedorA.com", false);

        // Remover um fornecedor
        controle.removerFornecedor("98.765.432/0001-10");

        // Listar novamente os fornecedores
        controle.listarFornecedores();
        
    }
    
}
