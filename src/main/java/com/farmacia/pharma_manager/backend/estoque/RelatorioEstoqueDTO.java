package com.farmacia.pharma_manager.backend.estoque;

import java.time.LocalDate;
import java.util.Date;

public class RelatorioEstoqueDTO {
    private String nomeProduto;
    private Integer quantidade;
    private Double valor;
    private Date dataVencimento;
    private Date dataEntradaEstoque;

    public RelatorioEstoqueDTO(String nomeProduto, Integer quantidade, Double valor, Date dataVencimento, Date dataEntradaEstoque) {
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

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataEntradaEstoque() {
        return dataEntradaEstoque;
    }

    public void setDataEntradaEstoque(Date dataEntradaEstoque) {
        this.dataEntradaEstoque = dataEntradaEstoque;
    }
}
