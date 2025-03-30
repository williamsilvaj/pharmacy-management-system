package com.farmacia.pharma_manager.backend.funcionario;

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
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable("id") Integer id, @RequestBody Funcionario funcionario) {
        Funcionario updatedFuncionario = funcionarioService.updateFuncionario(id, funcionario);
        return ResponseEntity.ok(updatedFuncionario);
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
