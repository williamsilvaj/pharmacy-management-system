package com.farmacia.pharma_manager.backend.gerente;
import com.farmacia.pharma_manager.backend.funcionario.Funcionario;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "gerente")
public class Gerente extends Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerar automaticamente o ID para cada instância
    @Column(name = "id")
    private Long id;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "Funcionarios Supervisionados")
    private List<Funcionario> funcionariosSupervisionados;

    // Construtor
    public Gerente(String nome, String telefone, String cpf, String cargo, String nivel, List<Funcionario> funcionariosSupervisionados) {
        super(nome, telefone, cpf, cargo);
        this.nivel = nivel;
        this.funcionariosSupervisionados = funcionariosSupervisionados;
    }

    // Getters e Setters
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<Funcionario> getFuncionariosSupervisionados() {
        return funcionariosSupervisionados;
    }

    public void setFuncionariosSupervisionados(List<Funcionario> funcionariosSupervisionados) {
        this.funcionariosSupervisionados = funcionariosSupervisionados;
    }

    // Método para exibir as informações do Gerente
    @Override
    public String toString() {
        return "Gerente{" +
                "nivel='" + nivel + '\'' +
                ", funcionariosSupervisionados=" + funcionariosSupervisionados +
                '}';
    }
}
