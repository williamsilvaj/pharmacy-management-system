package com.farmacia.pharma_manager.backend.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public Funcionario salvarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.salvarFuncionario(funcionario);
    }

    @PutMapping
    public Funcionario atualizarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.atualizarFuncionario(funcionario);
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }

    @GetMapping("/cpf/{cpf}")
    public Optional<Funcionario> buscarFuncionarioPorCpf(@PathVariable String cpf) {
        return funcionarioService.buscarFuncionarioPorCpf(cpf);
    }

    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }

    @DeleteMapping("/{id}")
    public void deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
    }
}
