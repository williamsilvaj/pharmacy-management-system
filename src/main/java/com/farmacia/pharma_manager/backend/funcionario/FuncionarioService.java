package com.farmacia.pharma_manager.backend.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService{
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService( FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

      // salvar ou atualizar um Gerente
  public Funcionario saveOrUpdateFuncionario(Funcionario funcionario) {
    return funcionarioRepository.save(funcionario);
  }

    //listar todos os Gerentes
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

      //  buscar um Gerente pelo ID
  public Optional<Funcionario> getFuncionarioById(Integer id) {
    return funcionarioRepository.findById(id);
  }

  // deletar um Gerente pelo ID
  public void deletarFuncionario(Integer id) {
    funcionarioRepository.deleteById(id);
  }
}
