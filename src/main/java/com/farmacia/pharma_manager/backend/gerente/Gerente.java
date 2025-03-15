package com.farmacia.pharma_manager.backend.gerente;
import com.farmacia.pharma_manager.backend.funcionario.Funcionario;

import java.util.List;

public class Gerente {
    private String nivel;
    private List<Funcionario> funcionariosSupervisionados;

    // Construtor
    public Gerente(String nivel, List<Funcionario> funcionariosSupervisionados) {
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
