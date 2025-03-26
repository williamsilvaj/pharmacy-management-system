package com.farmacia.pharma_manager.backend.farmaceutico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade representando um farmacêutico.
 * Esta classe é mapeada para a tabela "farmaceutico" no banco de dados.
 */
@Entity
@Table(name = "farmaceutico")
public class Farmaceutico {

    /**
     * ID único do farmacêutico.
     * Este campo é gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFarmaceutico;

    /**
     * Turno de trabalho do farmacêutico (Exemplo: manhã, tarde, noite).
     */
    private String turno;

    /**
     * CRF (Conselho Regional de Farmácia) do farmacêutico.
     */
    private String crf;

    /**
     * Carga horária semanal do farmacêutico.
     */
    private Float cargaHoraria;

    // Getters e Setters

    public Integer getIdFarmaceutico() {
        return idFarmaceutico;
    }

    public void setIdFarmaceutico(Integer idFarmaceutico) {
        this.idFarmaceutico = idFarmaceutico;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCrf() {
        return crf;
    }

    public void setCrf(String crf) {
        this.crf = crf;
    }

    public Float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
