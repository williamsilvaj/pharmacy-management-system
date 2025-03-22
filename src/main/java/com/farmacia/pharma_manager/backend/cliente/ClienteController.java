package com.farmacia.pharma_manager.backend.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Endpoint para cadastrar um cliente
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    // Endpoint para consultar um cliente por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable String cpf) {
        Optional<Cliente> cliente = clienteService.consultarClientePorCpf(cpf);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para alterar um cliente
    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> alterarCliente(@PathVariable String cpf, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteService.consultarClientePorCpf(cpf);
        if (clienteExistente.isPresent()) {
            cliente.setCpf(cpf); // Mantém o CPF existente
            Cliente clienteAlterado = clienteService.alterarCliente(cliente);
            return ResponseEntity.ok(clienteAlterado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint para remover um cliente
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> removerCliente(@PathVariable String cpf) {
        if (clienteService.removerCliente(cpf)) {
            return ResponseEntity.noContent().build(); // Retorna status 204 (sem conteúdo)
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna status 404 (não encontrado)
    }

    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna status 204 (sem conteúdo)
        }
        return ResponseEntity.ok(clientes);
    }
}
