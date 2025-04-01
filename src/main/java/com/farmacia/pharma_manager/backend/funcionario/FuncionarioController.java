package com.farmacia.pharma_manager.backend.funcionario;

import com.farmacia.pharma_manager.backend.farmaceutico.Farmaceutico;
import com.farmacia.pharma_manager.backend.gerente.Gerente;
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
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
      if (funcionario instanceof Gerente) {
        Gerente gerenteAtualizado = gerenteService.atualizarGerente(id, (Gerente) funcionario);
        return ResponseEntity.ok(gerenteAtualizado);
      } else if (funcionario instanceof Farmaceutico) {
        Farmaceutico farmaceuticoAtualizado = farmaceuticoService.atualizarFarmaceutico(id, (Farmaceutico) funcionario);
        return ResponseEntity.ok(farmaceuticoAtualizado);
      }

      // Se o tipo não for Gerente nem Farmaceutico, retornar erro 400
      return ResponseEntity.badRequest().build();
    }


  // Endpoint para deletar um funcionário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable("id") Integer id) {
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para associar um supervisor a um funcionário
    @PutMapping("/{funcionarioId}/supervisor/{supervisorId}")
    public ResponseEntity<Funcionario> setSupervisor(@PathVariable("funcionarioId") Integer funcionarioId, @PathVariable("supervisorId") Integer supervisorId) {
        Funcionario updatedFuncionario = funcionarioService.setSupervisor(funcionarioId, supervisorId);
        return updatedFuncionario != null ? ResponseEntity.ok(updatedFuncionario) : ResponseEntity.notFound().build();
    }
}
