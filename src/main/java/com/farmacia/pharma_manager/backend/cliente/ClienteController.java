package com.farmacia.pharma_manager.backend.cliente;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteService clienteService;

  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @GetMapping("/pagina")
  public String redirecionarParaClientePage() {
    return "cliente/cliente";
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> listarTodos() {
    List<Cliente> clientes = clienteService.listarTodos();
    return ResponseEntity.ok(clientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
    Optional<Cliente> cliente = clienteService.buscarPorId(id);
    return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/cpf/{cpf}")
  public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
    Optional<Cliente> cliente = clienteService.buscarPorCpf(cpf);
    return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/nome/{nome}")
  public ResponseEntity<List<Cliente>> buscarPorNome(@PathVariable String nome) {
    List<Cliente> clientes = clienteService.buscarPorNome(nome);
    return ResponseEntity.ok(clientes);
  }

  @PostMapping
  public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
    Cliente savedCliente = clienteService.salvar(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
  }


  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
    Optional<Cliente> clienteExistente = clienteService.buscarPorId(id);
    if (clienteExistente.isPresent()) {
      cliente.setIdCliente(id);
      clienteService.salvar(cliente);
      return ResponseEntity.ok(cliente);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Integer id) {
    clienteService.deletar(id);
    return ResponseEntity.noContent().build();
  }
}
