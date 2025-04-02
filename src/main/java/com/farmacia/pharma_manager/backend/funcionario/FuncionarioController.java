package com.farmacia.pharma_manager.backend.funcionario;

import com.farmacia.pharma_manager.backend.farmaceutico.Farmaceutico;
import com.farmacia.pharma_manager.backend.gerente.Gerente;
import com.farmacia.pharma_manager.backend.gerente.GerenteRepository;
import com.farmacia.pharma_manager.backend.gerente.GerenteService;
import com.farmacia.pharma_manager.backend.farmaceutico.FarmaceuticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private GerenteService gerenteService;  // Injeção do serviço de Gerente

    @Autowired
    private FarmaceuticoService farmaceuticoService;  // Caso tenha o serviço do Farmaceutico

    @GetMapping("/pagina")
    public String redirecionarParaFuncionarioPage() {
      return "funcionario/funcionario";
    }


    // Endpoint para listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.getAllFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    // Endpoint para buscar um funcionário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable("id") Integer id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para salvar um novo funcionário
    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario savedFuncionario = funcionarioService.saveFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFuncionario);
    }

    // Endpoint para atualizar um funcionário existente
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(
      @PathVariable Integer id,
      @RequestBody Funcionario funcionarioAtualizado) {

      Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
      if (funcionarioOptional.isEmpty()) {
        return ResponseEntity.notFound().build();
      }

      Funcionario funcionarioExistente = funcionarioOptional.get();

      // Atualiza os dados do funcionário
      funcionarioExistente.setNome(funcionarioAtualizado.getNome());
      funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
      funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
      funcionarioExistente.setEndereco(funcionarioAtualizado.getEndereco());
      funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());

      // Atualiza os dados específicos de farmacêutico, se for o caso
      if (funcionarioExistente instanceof Farmaceutico) {
        ((Farmaceutico) funcionarioExistente).setTurno(((Farmaceutico) funcionarioAtualizado).getTurno());
        ((Farmaceutico) funcionarioExistente).setCrf(((Farmaceutico) funcionarioAtualizado).getCrf());
        ((Farmaceutico) funcionarioExistente).setCargaHoraria(((Farmaceutico) funcionarioAtualizado).getCargaHoraria());
      }

      // Atualiza o supervisor
      if (funcionarioAtualizado.getIdSupervisor() == null) {
        funcionarioExistente.setSupervisor(null);
      } else {
        Optional<Gerente> supervisorOptional = gerenteRepository.findById(funcionarioAtualizado.getIdSupervisor());
        funcionarioExistente.setSupervisor(supervisorOptional.orElse(null));
      }

      Funcionario funcionarioAtualizadoFinal = funcionarioRepository.save(funcionarioExistente);
      return ResponseEntity.ok(funcionarioAtualizadoFinal);
    }
//    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
//      if (funcionario instanceof Gerente) {
//        Gerente gerenteAtualizado = gerenteService.atualizarGerente(id, (Gerente) funcionario);
//        return ResponseEntity.ok(gerenteAtualizado);
//      } else if (funcionario instanceof Farmaceutico) {
//        Farmaceutico farmaceuticoAtualizado = farmaceuticoService.atualizarFarmaceutico(id, (Farmaceutico) funcionario);
//        return ResponseEntity.ok(farmaceuticoAtualizado);
//      }
//
//      // Se o tipo não for Gerente nem Farmaceutico, retornar erro 400
//      return ResponseEntity.badRequest().build();
//    }


  // Endpoint para deletar um funcionário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable("id") Integer id) {
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para associar um supervisor a um funcionário
    @PostMapping("/gerente/{id}/subordinados")
    public ResponseEntity<?> adicionarSubordinados(@PathVariable int id, @RequestBody List<Integer> subordinadosIds) {
      Optional<Gerente> gerenteOpt = funcionarioRepository.findById(id).map(f -> (Gerente) f);

      if (gerenteOpt.isPresent()) {
        Gerente gerente = gerenteOpt.get();
        List<Funcionario> subordinados = funcionarioRepository.findAllById(subordinadosIds);

        for (Funcionario sub : subordinados) {
          sub.setSupervisor(gerente); // Define o gerente como supervisor
          funcionarioRepository.save(sub); // Salva a atualização no banco
        }

        return ResponseEntity.ok("Subordinados adicionados com sucesso.");
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gerente não encontrado.");
      }
    }
}
