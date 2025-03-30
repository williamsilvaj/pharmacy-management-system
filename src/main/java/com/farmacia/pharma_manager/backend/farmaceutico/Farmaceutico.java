package com.farmacia.pharma_manager.backend.farmaceutico;

import com.farmacia.pharma_manager.backend.cargo.Cargo;
import com.farmacia.pharma_manager.backend.endereco.Endereco;
import com.farmacia.pharma_manager.backend.funcionario.Funcionario;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "idFuncionario")
@Table(name = "farmaceutico")
public class Farmaceutico extends Funcionario {

	  @Column(name = "idFuncionario", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "turno", nullable = false, length = 45)
    private String turno;

    @Column(name = "crf", nullable = false, length = 45)
    private String crf;

    @Column(name = "cargaHoraria", nullable = false)
    private Float cargaHoraria;

    @OneToOne
    @JoinColumn(name = "idFuncionario", referencedColumnName = "idFuncionario", insertable = false, updatable = false)
    private Funcionario funcionario;


    public Farmaceutico(String nome, String telefone, String cpf, Cargo cargo, Endereco endereco, String turno, String crf, Float cargaHoraria) {
        super(nome, telefone, cpf, cargo, endereco);
        this.turno = turno;
        this.crf = crf;
        this.cargaHoraria = cargaHoraria;
    }

    public Farmaceutico() {}

    // Getters e Setters
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
