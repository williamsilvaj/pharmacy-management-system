package com.farmacia.pharma_manager.backend.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ControleCliente {

    private final ClienteService clienteService;

    @Autowired
    public ControleCliente(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Cadastrar cliente
    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    // Alterar dados de um cliente
    public Cliente alterarCliente(String cpf, Cliente clienteAtualizado) {
        Optional<Cliente> clienteOpt = clienteService.consultarClientePorCpf(cpf);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            return clienteService.cadastrarCliente(cliente); // Atualiza o cliente
        }
        return null; // Retorna null caso o cliente não seja encontrado
    }

    // Remover cliente
    public boolean removerCliente(String cpf) {
        Optional<Cliente> clienteOpt = clienteService.consultarClientePorCpf(cpf);
        if (clienteOpt.isPresent()) {
            clienteService.removerCliente(clienteOpt.get().getCpf());
            return true; // Retorna true se o cliente foi removido
        }
        return false; // Retorna false caso o cliente não seja encontrado
    }

    // Consultar cliente por CPF
    public Cliente consultarCliente(String cpf) {
        Optional<Cliente> clienteOpt = clienteService.consultarClientePorCpf(cpf);
        return clienteOpt.orElse(null); // Retorna o cliente ou null se não encontrado
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes(); // Retorna todos os clientes cadastrados
    }
}
