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

    /**
     * Metodo para redirecionar para a página de produtos.
     */
    @GetMapping("/pagina")
    public String redirecionarParaProdutoPage() {
        return "produto/produto";  // Nome do arquivo produto.html na pasta resources/templates
    }

    /**
     * Endpoint para criar um novo produto.
     *
     * @param produto Produto a ser criado.
     * @return O produto criado.
     */
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    /**
     * Endpoint para listar todos os produtos.
     *
     * @return Lista de produtos.
     */
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    /**
     * Endpoint para obter um produto pelo ID.
     *
     * @param id ID do produto.
     * @return O produto encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.obterProdutoPorId(id);
        return produto.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint para atualizar um produto existente.
     *
     * @param id      ID do produto a ser atualizado.
     * @param produto Produto com as novas informações.
     * @return O produto atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        if (produtoAtualizado != null) {
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint para deletar um produto pelo ID.
     *
     * @param id ID do produto a ser deletado.
     * @return Status de sucesso ou erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        if (produtoService.deletarProduto(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
