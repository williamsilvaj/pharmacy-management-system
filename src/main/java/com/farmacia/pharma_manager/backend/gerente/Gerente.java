package com.farmacia.pharma_manager.backend.gerente;

import com.farmacia.pharma_manager.backend.cargo.Cargo;
import com.farmacia.pharma_manager.backend.endereco.Endereco;
import com.farmacia.pharma_manager.backend.funcionario.Funcionario;

import jakarta.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "idFuncionario")
@Table(name = "gerente")
public class Gerente extends Funcionario {

    public Gerente(String nome, String telefone, String cpf, Cargo cargo, Endereco endereco) {
        super(nome, telefone, cpf, cargo, endereco);
    }

    public Gerente() {}

}
