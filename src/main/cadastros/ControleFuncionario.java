import java.util.List;
import java.util.ArrayList;
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
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
    }

    public void alterarFuncionario(String cpf, String novoNome, String novoCargo) {
        Funcionario funcionario = consultarFuncionario(cpf);
        if (funcionario != null) {
            funcionario.setNome(novoNome);
            funcionario.setCargo(novoCargo);
            System.out.println("Funcionário alterado com sucesso: " + novoNome);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
    
}
