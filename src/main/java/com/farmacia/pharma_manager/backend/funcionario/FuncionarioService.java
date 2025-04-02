package com.farmacia.pharma_manager.backend.funcionario;

import com.farmacia.pharma_manager.backend.gerente.Gerente;
import com.farmacia.pharma_manager.backend.gerente.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

  @Autowired
  private FuncionarioRepository funcionarioRepository;

  @Autowired
  private GerenteRepository gerenteRepository;


  // Salvar um novo Funcionario
//  public Funcionario saveFuncionario(Funcionario funcionario) {
//    return funcionarioRepository.save(funcionario);
//  }
  public Funcionario saveFuncionario(Funcionario funcionario) {
    if (funcionario.getSupervisor() != null) {
      Optional<Gerente> supervisorOpt = gerenteRepository.findById(funcionario.getIdSupervisor());
      supervisorOpt.ifPresent(funcionario::setSupervisor);
    }

    return funcionarioRepository.save(funcionario);
  }

  // Buscar todos os Funcionarios
  public List<Funcionario> getAllFuncionarios() {
    return funcionarioRepository.findAll();
  }

  // Buscar Funcionario por ID
  public Optional<Funcionario> getFuncionarioById(Integer id) {
    return funcionarioRepository.findById(id);
  }

  // Atualizar Funcionario
  public Funcionario updateFuncionario(Integer id, Funcionario novoFuncionario) {
    return funcionarioRepository.findById(id).map(funcionario -> {
      funcionario.setNome(novoFuncionario.getNome());
      funcionario.setTelefone(novoFuncionario.getTelefone());
      funcionario.setCpf(novoFuncionario.getCpf());
      funcionario.setEndereco(novoFuncionario.getEndereco());
      funcionario.setCargo(novoFuncionario.getCargo());

      // Permitir remoção do supervisor
      if (novoFuncionario.getIdSupervisor() == null || novoFuncionario.getIdSupervisor() == 0) {
        funcionario.setSupervisor(null);
      } else {
        Optional<Gerente> supervisorOpt = gerenteRepository.findById(novoFuncionario.getIdSupervisor());
        funcionario.setSupervisor(supervisorOpt.orElse(null));
      }

      return funcionarioRepository.save(funcionario);
    }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
  }
//  public Funcionario updateFuncionario(Integer id, Funcionario funcionario) {
//    funcionario.setId(id);
//    return funcionarioRepository.save(funcionario);
//  }

  // Deletar Funcionario
  public void deleteFuncionario(Integer id) {
    funcionarioRepository.deleteById(id);
  }

  // Associar supervisor (Gerente)
  public Funcionario setSupervisor(Integer funcionarioId, Integer supervisorId) {
    Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(funcionarioId);
    Optional<Gerente> gerenteOptional = gerenteRepository.findById(supervisorId);

    if (funcionarioOptional.isPresent() && gerenteOptional.isPresent()) {
      Funcionario funcionario = funcionarioOptional.get();
      Gerente gerente = gerenteOptional.get();
      funcionario.setSupervisor(gerente);
      return funcionarioRepository.save(funcionario);
    }

    return null;  // Caso algum dos dados nao seja encontrado
  }
}
