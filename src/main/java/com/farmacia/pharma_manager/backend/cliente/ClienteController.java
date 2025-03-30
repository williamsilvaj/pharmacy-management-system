package com.farmacia.pharma_manager.backend.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
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
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
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
    public Cliente criar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.atualizar(id, cliente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
