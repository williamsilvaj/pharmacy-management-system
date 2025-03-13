package stakeholders;

public class Fornecedor {
    private String nome;
    private String cnpj;
    private String email;
    private boolean status; // true = Ativo, false = Inativo

    public Fornecedor(String nome, String cnpj, String email, boolean status) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CNPJ: " + cnpj + ", Email: " + email + ", Status: " + (status ? "Ativo" : "Inativo");
    }
}