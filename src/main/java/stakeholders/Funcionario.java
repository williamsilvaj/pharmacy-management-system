package stakeholders;
public class Funcionario {

    // Atributos da classe
    private String nome;
    private String telefone;
    private String cpf;
    private String cargo;

    // Construtor para inicializar os atributos
    public Funcionario(String nome, String telefone, String cpf, String cargo) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    // Métodos getters e setters para cada atributo

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }



    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Funcionario [Nome=" + nome + ", Telefone=" + telefone + ", CPF=" + cpf + "]";
    }
    
}


