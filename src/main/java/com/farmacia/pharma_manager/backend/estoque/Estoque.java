package com.farmacia.pharma_manager.backend.estoque;

import com.farmacia.pharma_manager.backend.item.Item;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstoque;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataEntrada;

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();


    // Getters and Setters
    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}
