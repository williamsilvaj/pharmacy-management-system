package com.farmacia.pharma_manager.backend.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControleFuncionario {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public ControleFuncionario(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    // Cadastrar um novo funcionário
    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Consultar um funcionário pelo CPF
    public Funcionario consultarFuncionario(String cpf) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByCpf(cpf);
        return funcionario.orElse(null); // Retorna o funcionário ou null caso não encontrado
    }

    // Alterar um funcionário
    public Funcionario alterarFuncionario(String novoNome, String novoTelefone, String cpf, String novoCargo) {
        Funcionario funcionario = consultarFuncionario(cpf);
        if (funcionario != null) {
            funcionario.setNome(novoNome);
            funcionario.setCargo(novoCargo);
            funcionario.setTelefone(novoTelefone);
            return funcionarioRepository.save(funcionario); // Atualiza e retorna o funcionário alterado
        }
        return null; // Retorna null se não encontrar o funcionário
    }

    // Remover um funcionário pelo nome
    public boolean removerFuncionario(String nome) {
        Funcionario funcionario = funcionarioRepository.findByNome(nome);
        if (funcionario != null) {
            funcionarioRepository.delete(funcionario);
            return true; // Retorna true se o funcionário foi removido
        }
        return false; // Retorna false se não encontrar o funcionário
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll(); // Retorna todos os funcionários do banco
    }

    // Atribuir um cargo a um funcionário
    public boolean atribuirCargo(String nome, String novoCargo) {
        Funcionario funcionario = funcionarioRepository.findByNome(nome);
        if (funcionario != null) {
            funcionario.setCargo(novoCargo);
            funcionarioRepository.save(funcionario);
            return true; // Retorna true se o cargo foi atribuído
        }
        return false; // Retorna false se não encontrar o funcionário
    }
}
