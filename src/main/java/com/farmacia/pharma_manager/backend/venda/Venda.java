package com.farmacia.pharma_manager.backend.venda;

import com.farmacia.pharma_manager.backend.cliente.Cliente;
import com.farmacia.pharma_manager.backend.farmaceutico.Farmaceutico;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.farmacia.pharma_manager.backend.item.Item;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenda;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "idFarmaceutico", referencedColumnName = "idFuncionario")
    private Farmaceutico farmaceutico;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    // Getters e Setters

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Farmaceutico getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(Farmaceutico farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
