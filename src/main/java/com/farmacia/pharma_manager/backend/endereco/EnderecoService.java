package com.farmacia.pharma_manager.backend.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Método para listar todos os endereços
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    // Método para buscar um endereço por ID
    public Optional<Endereco> buscarPorId(Integer idEndereco) {
        return enderecoRepository.findById(idEndereco);
    }

    // Método para salvar um novo endereço
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Método para atualizar um endereço existente
    public Endereco atualizar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Método para deletar um endereço
    public void deletar(Integer idEndereco) {
        enderecoRepository.deleteById(idEndereco);
    }
}
