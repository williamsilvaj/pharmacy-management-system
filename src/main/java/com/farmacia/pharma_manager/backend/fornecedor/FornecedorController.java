package com.farmacia.pharma_manager.backend.fornecedor;


//import com.farmacia.pharma_manager.backend.endereco.Endereco;
//import com.farmacia.pharma_manager.backend.endereco.EnderecoService;
//import com.farmacia.pharma_manager.backend.fornecedor.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;
  @Autowired
  private FornecedorRepository fornecedorRepository;

//    @Autowired
//    private EnderecoService enderecoService;

  @GetMapping("/pagina")
  public String redirecionarParaFornecedorPage() {
    return "fornecedor/fornecedor";
  }

  @PostMapping
  public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor) {
    Fornecedor savedFornecedor = fornecedorService.salvar(fornecedor);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedFornecedor);
  }


  // Criar um novo Fornecedor
//    @PostMapping
//    public ResponseEntity<Fornecedor> criarFornecedor(@RequestBody Fornecedor fornecedor) {
//        Optional<Endereco> enderecoOptional = enderecoService.buscarPorId(fornecedor.getEndereco().getIdEndereco());
//
//        if (!enderecoOptional.isPresent()) {
//            return ResponseEntity.notFound().build(); // Retorna 404 se o Endereco não existir
//        }
//
//        fornecedor.setEndereco(enderecoOptional.get());
//        Fornecedor fornecedorSalvo = fornecedorService.salvar(fornecedor);
//        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
//    }

//    // Obter todos os Fornecedores
//    @GetMapping
//    public List<Fornecedor> listarTodos() {
//        return fornecedorService.listarTodos();
//    }


  @GetMapping
  public ResponseEntity<List<Fornecedor>> listarTodos() {
    try {
      List<Fornecedor> fornecedores = fornecedorService.listarTodos();
      return ResponseEntity.ok(fornecedores); // Retorna a lista de fornecedores como resposta
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna um erro 500
    }
  }


  // Obter um Fornecedor por ID
  @GetMapping("/{id}")
  public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Integer id) {
    Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
    return fornecedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

//    if (fornecedor.isPresent()) {
//      return ResponseEntity.ok(fornecedor.get());
//    } else {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
  }


  // Atualizar um Fornecedor existente
//    @PutMapping("/{id}")
//    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Integer id, @RequestBody Fornecedor fornecedor) {
//        Optional<Fornecedor> fornecedorExistente = fornecedorService.buscarPorId(id);
//        if (!fornecedorExistente.isPresent()) {
//            return ResponseEntity.notFound().build(); // Retorna 404 se o Fornecedor não for encontrado
//        }
//      Fornecedor fornecedorAtualizado = fornecedorService.atualizar(fornecedor);
//        return ResponseEntity.ok(fornecedorAtualizado);
//    }
  @PutMapping("/{id}")
  public ResponseEntity<Fornecedor> updateFornecedor(
    @PathVariable Integer id,
    @RequestBody Fornecedor fornecedorAtualizado) {

    Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
    if (fornecedorOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Fornecedor fornecedorExistente = fornecedorOptional.get();

    // Atualiza os dados do funcionário
    fornecedorExistente.setNome(fornecedorAtualizado.getNome());
    fornecedorExistente.setCnpj(fornecedorAtualizado.getCnpj());
    fornecedorExistente.setEmail(fornecedorAtualizado.getEmail());
    fornecedorExistente.setEndereco(fornecedorAtualizado.getEndereco());
    // fornecedorExistente.setCargo(fornecedorAtualizado.getCargo());


    Fornecedor fornecedorAtualizadoFinal = fornecedorRepository.save(fornecedorExistente);
    return ResponseEntity.ok(fornecedorAtualizadoFinal);
  }

    // Deletar um Fornecedor
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFornecedor(@PathVariable("id") Integer id) {
    fornecedorService.deletar(id);
    return ResponseEntity.noContent().build();
  }
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> deletarFornecedor(@PathVariable Integer id) {
//      Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
//
//      if (!fornecedor.isPresent()) {
//          return ResponseEntity.notFound().build(); // Retorna 404 se o Fornecedor não for encontrado
//      }
//
//      fornecedorService.deletar(id);
//      return ResponseEntity.noContent().build();
//  }
}
