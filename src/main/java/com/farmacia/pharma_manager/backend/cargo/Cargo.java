package com.farmacia.pharma_manager.backend.cargo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataContratacao;

    @Column(name = "salario")
    private Double salario;

    // Construtor
    public Cargo(String titulo, LocalDate dataContratacao, Double salario) {

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

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
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
