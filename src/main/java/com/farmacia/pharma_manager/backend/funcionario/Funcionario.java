package com.farmacia.pharma_manager.backend.funcionario;

import com.farmacia.pharma_manager.backend.cargo.Cargo;
import com.farmacia.pharma_manager.backend.gerente.Gerente;
import com.farmacia.pharma_manager.backend.farmaceutico.Farmaceutico;
import com.farmacia.pharma_manager.backend.endereco.Endereco;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuncionario;

    private String nome;
    private String telefone;
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "idCargo", nullable = false)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "idGerente")
    private Gerente gerente;

    @ManyToOne
    @JoinColumn(name = "idFarmaceutico")
    private Farmaceutico farmaceutico;

    @ManyToOne
    @JoinColumn(name = "idEndereco", nullable = false)
    private Endereco endereco;

    // Getters e Setters

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Farmaceutico getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(Farmaceutico farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
