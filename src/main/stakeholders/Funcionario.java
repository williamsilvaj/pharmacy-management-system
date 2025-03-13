public class Funcionario {

    // Atributos da classe
    private String nome;
    private String telefone;
    private String cpf;

    // Construtor para inicializar os atributos
    public Funcionario(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
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

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Funcionario [Nome=" + nome + ", Telefone=" + telefone + ", CPF=" + cpf + "]";
    }
    
}


