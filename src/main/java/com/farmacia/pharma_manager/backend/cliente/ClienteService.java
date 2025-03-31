package com.farmacia.pharma_manager.backend.cliente;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Optional<Cliente> buscarPorTelefone(String telefone) {
        return clienteRepository.findByTelefone(telefone);
    }

	public List<Cliente> buscarPorNome(String nome) {
		return clienteRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
	}

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Integer id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEndereco(clienteAtualizado.getEndereco());
          if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
            }
          if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Este e-mail já está registrado.");
            }
          return clienteRepository.save(cliente);

        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void deletar(Integer id) {
        clienteRepository.deleteById(id);
    }
}
