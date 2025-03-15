package stakeholders;
import cadastros.*;


public class TestesStakeholders {
    
    public static void main(String[] args) {
        // Criando um objeto Funcionário
        ControleFornecedor controle = new ControleFornecedor();
        ControleFuncionario funcionario = new ControleFuncionario();

        // Cadastrar fornecedores
        controle.cadastrarFornecedor("Fornecedor A", "12.345.678/0001-90", "contato@fornecedorA.com", true);
        controle.cadastrarFornecedor("Fornecedor B", "98.765.432/0001-10", "contato@fornecedorB.com", false);
        
    }
    
}
