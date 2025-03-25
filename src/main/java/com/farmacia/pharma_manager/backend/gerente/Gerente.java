package com.farmacia.pharma_manager.backend.gerente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerente") // Nome da tabela no banco de dados
public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
    private Integer idGerente;

    private String nivel;
    private String funcionariosSupervisionados;

    // Getters and Setters
    public Integer getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Integer idGerente) {
        this.idGerente = idGerente;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getFuncionariosSupervisionados() {
        return funcionariosSupervisionados;
    }

    public void setFuncionariosSupervisionados(String funcionariosSupervisionados) {
        this.funcionariosSupervisionados = funcionariosSupervisionados;
    }
}
