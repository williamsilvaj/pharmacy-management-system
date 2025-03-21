<<<<<<< HEAD
package com.farmacia.pharma_manager.backend.farmaceutico;
//import java.util.List;

public class Farmaceutico {
    private String turno;
    private String crf;
    private float cargaHoraria;

    // Construtor
    public Farmaceutico(String turno, String crf, float cargaHoraria) {
        this.turno = turno;
        this.crf = crf;
        this.cargaHoraria = cargaHoraria;
    }

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

    public float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    // Método para exibir as informações do Farmacêutico
    @Override
    public String toString() {
        return "Farmacêutico{" +
                "turno='" + turno + '\'' +
                ", crf='" + crf + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
=======
package com.farmacia.pharma_manager.backend.farmaceutico;
import com.farmacia.pharma_manager.backend.funcionario.Funcionario;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "farmaceutico")
public class Farmaceutico extends Funcionario  {
    @Column(name = "turno")
    private String turno;

    @Column(name = "crf")
    private String crf;

    @Column(name = "cargaHoraria")
    private float cargaHoraria;

    // Construtor
    public Farmaceutico (String nome, String telefone, String cpf, String cargo, String turno, String crf, float cargaHoraria) {
        super(nome, telefone, cpf, cargo);
        this.turno = turno;
        this.crf = crf;
        this.cargaHoraria = cargaHoraria;
    }

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

    public float getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(float cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    // Método para exibir as informações do Farmacêutico
    @Override
    public String toString() {
        return "Farmacêutico{" +
                "turno='" + turno + '\'' +
                ", crf='" + crf + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
>>>>>>> cadastrar
