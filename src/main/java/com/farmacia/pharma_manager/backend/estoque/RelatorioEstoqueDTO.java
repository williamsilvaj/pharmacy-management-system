package com.farmacia.pharma_manager.backend.estoque;

import java.time.LocalDate;
import java.util.Date;

public class RelatorioEstoqueDTO {
    private String nomeProduto;
    private Integer quantidade;
    private Double valor;
    private LocalDate dataVencimento;
    private LocalDate dataEntradaEstoque;

    public RelatorioEstoqueDTO(String nomeProduto, Integer quantidade, Double valor, LocalDate dataVencimento, LocalDate dataEntradaEstoque) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataEntradaEstoque = dataEntradaEstoque;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

    public LocalDate getDataEntradaEstoque() {
        return dataEntradaEstoque;
    }

    public void setDataEntradaEstoque(LocalDate dataEntradaEstoque) {
        this.dataEntradaEstoque = dataEntradaEstoque;
    }
}
