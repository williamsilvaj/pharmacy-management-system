package cadastros;
import java.util.List;
import java.util.ArrayList;
import stakeholders.Funcionario;

public class ControleFuncionario {

    private List<Funcionario> listaFuncionarios;

      // Construtor
      public ControleFuncionario() {
        listaFuncionarios = new ArrayList<>();
    }

    public void cadastrarFuncionario (Funcionario funcionario){
        listaFuncionarios.add(funcionario);
        System.out.println("Funcionário cadastrado com sucesso: " + funcionario.getNome());
    }

    public Funcionario consultarFuncionario(String cpf) {
        for (Funcionario f : listaFuncionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    public void alterarFuncionario(String novoNome, String novoTelefone, String cpf, String novoCargo) {
        Funcionario funcionario = consultarFuncionario(cpf);
        if (funcionario != null) {
            funcionario.setNome(novoNome);
            funcionario.setCargo(novoCargo);
            funcionario.setTelefone(novoTelefone);
            System.out.println("Funcionário alterado com sucesso: " + novoNome);
        } 
        else {
            System.out.println("Funcionário não encontrado.");
        }
    }
    
}
