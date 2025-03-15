package stakeholders;
import java.util.List;

public class Gerente extends Funcionario {
    
    private String nivel;
    private List<Funcionario> funcionariosSupervisionados;

    // Construtor
    public Gerente (String nome, String telefone, String cpf, String cargo, String nivel, List<Funcionario> funcionariosSupervisionados ) {
        super(nome, telefone, cpf, cargo);
        this.nivel = nivel;
        this.funcionariosSupervisionados = funcionariosSupervisionados;

    }

    // Getters e Setters
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<Funcionario> getFuncionariosSupervisionados() {
        return funcionariosSupervisionados;
    }

    public void setFuncionariosSupervisionados(List<Funcionario> funcionariosSupervisionados) {
        this.funcionariosSupervisionados = funcionariosSupervisionados;
    }

    // Método para exibir as informações do Gerente
    @Override
    public String toString() {
        return "Gerente{" +
                "nivel='" + nivel + '\'' +
                ", funcionariosSupervisionados=" + funcionariosSupervisionados +
                '}';
    }
}
