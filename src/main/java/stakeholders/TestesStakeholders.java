import java.util.ArrayList;
import java.util.List;  
import stakeholders.Funcionario;
import stakeholders.Gerente;
import stakeholders.Farmaceutico; 

import cadastros.ControleFuncionario;

public class TestesStakeholders {

    
    
    public static void main(String[] args) {
        // Criando um objeto Funcionário
        ControleFuncionario controleDeFuncionarios = new ControleFuncionario();
        Funcionario funcionario01 = new Funcionario("Juam Silva", "1234-5678", "123.456.789-00", " atendente");
        Funcionario funcionario02 = new Funcionario("Marcos Lima", "8895-5678", "168.456.789-00", " atendente");
        Farmaceutico farmaceutico01 = new Farmaceutico("noturno","839234",12.0f);
        List<Funcionario> funcionariosSupervisionados = new ArrayList<>();
        
        funcionariosSupervisionados.add(funcionario01);
        funcionariosSupervisionados.add(funcionario02);
        
        Gerente gerente = new Gerente(" nivel 01", funcionariosSupervisionados);
        
        controleDeFuncionarios.cadastrarFuncionario(funcionario01);
        
        // Alterando dados usando os setters
        funcionario01.setNome("Maria Oliveira");
        funcionario01.setTelefone("9876-5432");
        funcionario01.setCpf("987.654.321-00");

        // Exibindo os dados atualizados do funcionário
        //System.out.println(funcionario01);
        System.out.println(farmaceutico01);
        
    }
    
}
