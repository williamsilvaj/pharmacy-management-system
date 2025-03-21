package com.farmacia.pharma_manager.backend.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    // Método para salvar um novo funcionário
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Método para atualizar um funcionário existente
    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        if (funcionario.getId() == null) {
            throw new IllegalArgumentException("Funcionario não pode ser atualizado sem um ID válido.");
        }
        return funcionarioRepository.save(funcionario);
    }

    // Método para buscar um funcionário pelo ID
    public Optional<Funcionario> buscarFuncionarioPorId(Integer id) {
        return funcionarioRepository.findById(id);
    }

    // Método para buscar um funcionário pelo CPF
    public Optional<Funcionario> buscarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    // Método para listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Método para deletar um funcionário por ID
      // deletar um Gerente pelo ID
    public void deletarFuncionario(Integer id) {
    if (funcionarioRepository.existsById(id)) {
        funcionarioRepository.deleteById(id);
    } else {
        throw new IllegalArgumentException("Funcionario com ID " + id + " não encontrado.");
        }
    }

}