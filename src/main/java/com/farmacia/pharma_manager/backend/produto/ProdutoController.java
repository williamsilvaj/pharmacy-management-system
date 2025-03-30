package com.farmacia.pharma_manager.backend.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/pagina")
    public String redirecionarParaProdutoPage() {
        return "produto/produto";
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.obterProdutoPorId(id);
        return produto.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if (produtoAtualizado != null) {
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        if (produtoService.deletarProduto(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/busca/{termo}")
    public ResponseEntity<List<Produto>> buscarPorNome(@PathVariable String termo) {
        List<Produto> produtos = produtoService.buscarPorNome(termo);
        return ResponseEntity.ok(produtos);
    }
}
