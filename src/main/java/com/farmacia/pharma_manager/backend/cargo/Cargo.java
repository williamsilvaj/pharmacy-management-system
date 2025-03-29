package com.farmacia.pharma_manager.backend.cargo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCargo")
    private Integer idCargo;

    @Column(name = "titulo", nullable = false, length = 45)
    private String titulo;

    @Column(name = "dataContratacao")
    @Temporal(TemporalType.DATE)
    private Date dataContratacao;

    @Column(name = "salario")
    private Double salario;

    // Construtor
    public Cargo(String titulo, Date dataContratacao, Double salario) {

        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título do cargo não pode ser vazio.");
        }
        this.titulo = titulo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }

    public Cargo() {}

    // Getters e Setters
    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    // Método toString() para exibição
    @Override
    public String toString() {
        return "Cargo{" +
                "idCargo=" + idCargo +
                ", titulo='" + titulo + '\'' +
                ", dataContratacao=" + dataContratacao +
                ", salario=" + salario +
                '}';
    }
}
