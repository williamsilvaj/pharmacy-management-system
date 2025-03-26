package com.farmacia.pharma_manager.backend.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Criar novo produto
    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Optional<Produto> obterProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    // Atualizar produto
    public Produto atualizarProduto(Integer id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setIdProduto(id);  // Garantir que o ID correto seja usado
            return produtoRepository.save(produto);
        }
        return null;
    }

    // Deletar produto
    public boolean deletarProduto(Integer id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
