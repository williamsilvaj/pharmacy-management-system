package com.farmacia.pharma_manager.backend.gerente;

import com.farmacia.pharma_manager.backend.cargo.Cargo;
import com.farmacia.pharma_manager.backend.endereco.Endereco;
import com.farmacia.pharma_manager.backend.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnore;



import jakarta.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idFuncionario")
@Table(name = "gerente")
public class Gerente extends Funcionario {

    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Funcionario> funcionariosSupervisionados;



    public Gerente(String nome, String telefone, String cpf, Cargo cargo, Endereco endereco, List<Funcionario> funcionariosSupervisionados) {
        super(nome, telefone, cpf, cargo, endereco);
        this.funcionariosSupervisionados = funcionariosSupervisionados;
    }

    public Gerente() {}

    // Getters and Setters
    public List<Funcionario> getFuncionariosSupervisionados() {
        return funcionariosSupervisionados;
    }

    public void setFuncionariosSupervisionados(List<Funcionario> funcionariosSupervisionados) {
        this.funcionariosSupervisionados = funcionariosSupervisionados;
    }
}
