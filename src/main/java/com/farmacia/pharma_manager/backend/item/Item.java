package com.farmacia.pharma_manager.backend.item;

import com.farmacia.pharma_manager.backend.produto.Produto;
import com.farmacia.pharma_manager.backend.venda.Venda;
import com.farmacia.pharma_manager.backend.estoque.Estoque;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = true)
    private Integer quantidade;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataVencimento;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idVenda", nullable = true)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "idEstoque", nullable = true)
    private Estoque estoque;

    // Getters and Setters
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Integer getIdItem() {
        return idItem;
    }
    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    public Estoque getEstoque() {
        return estoque;
    }
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
