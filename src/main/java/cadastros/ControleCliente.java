package cadastros;
import java.util.ArrayList;
import java.util.List;

import stakeholders.*;

public class ControleCliente {


    private List<Cliente> clientes;

    // Construtor
    public ControleCliente() {
        this.clientes = new ArrayList<>();
    }

    // Cadastrar cliente
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso: " + cliente);
    }

    // Alterar dados de um cliente
    public void alterarCliente(String cpf, Cliente clienteAtualizado) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                cliente.setNome(clienteAtualizado.getNome());
                cliente.setEmail(clienteAtualizado.getEmail());
                cliente.setTelefone(clienteAtualizado.getTelefone());
                cliente.setEndereco(clienteAtualizado.getEndereco());
                System.out.println("Cliente alterado com sucesso: " + cliente);
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    // Remover cliente
    public void removerCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                clientes.remove(cliente);
                System.out.println("Cliente removido com sucesso.");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    // Consultar cliente
    public void consultarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println("Cliente encontrado: " + cliente);
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    // Listar todos os clientes
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }
}