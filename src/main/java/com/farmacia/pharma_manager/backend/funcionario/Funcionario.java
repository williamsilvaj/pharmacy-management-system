package com.farmacia.pharma_manager.backend.funcionario;

import com.farmacia.pharma_manager.backend.cargo.Cargo;
import com.farmacia.pharma_manager.backend.endereco.Endereco;
import com.farmacia.pharma_manager.backend.gerente.Gerente;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFuncionario")
    private Integer idFuncionario;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "idCargo", nullable = false)
    private Cargo cargo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEndereco", nullable = false)
    private Endereco endereco;

    // Supervisor desse funcionario
    @ManyToOne
    @JoinColumn(name = "idGerente", nullable = true)
    private Gerente supervisor;


    public Funcionario(String nome, String telefone, String cpf, Cargo cargo, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cargo = cargo;
        this.endereco = endereco;
    }

    public Funcionario() {}

    // Getters e Setters

    public Integer getId() {
        return idFuncionario;
    }

    public void setId(Integer id) {
        this.idFuncionario = id;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Gerente getSupervisor() { return supervisor; }

    public void setSupervisor(Gerente supervisor) { this.supervisor = supervisor; }
}
