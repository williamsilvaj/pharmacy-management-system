package com.farmacia.pharma_manager.backend.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Cadastrar um novo cliente
    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente); // Salva o cliente no banco
    }

    // Consultar um cliente por CPF
    public Optional<Cliente> consultarClientePorCpf(Long cpf) {
        return clienteRepository.findByCpf(cpf); // Busca um cliente pelo CPF
    }

    // Alterar um cliente
    public Cliente alterarCliente(Cliente cliente) {
        return clienteRepository.save(cliente); // Atualiza o cliente no banco
    }

    // Remover um cliente
    public boolean removerCliente(Long cpf) {
        Optional<Cliente> cliente = consultarClientePorCpf(cpf);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get()); // Deleta o cliente do banco
            return true;
        }
        return false; // Retorna false se o cliente não for encontrado
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll(); // Retorna todos os clientes
    }
}
