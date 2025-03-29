package com.farmacia.pharma_manager.backend.fornecedor_item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor_item")
public class FornecedorItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto incremento para o idFornecedorItem
    private Integer idFornecedorItem;

    private Integer idFornecedor;
    private Integer idItem;

    // Getters e Setters

    public Integer getIdFornecedorItem() {
        return idFornecedorItem;
    }

    public void setIdFornecedorItem(Integer idFornecedorItem) {
        this.idFornecedorItem = idFornecedorItem;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
}
