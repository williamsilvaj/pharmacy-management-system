public class TestesStakeholders {
    
    public static void main(String[] args) {
        // Criando um objeto Funcionário
        Funcionario funcionario = new Funcionario("Juam Silva", "1234-5678", "123.456.789-00");

        // Exibindo os dados do funcionário
        System.out.println(funcionario);

        // Alterando dados usando os setters
        funcionario.setNome("Maria Oliveira");
        funcionario.setTelefone("9876-5432");
        funcionario.setCpf("987.654.321-00");

        // Exibindo os dados atualizados do funcionário
        System.out.println(funcionario);
    }
    
}
