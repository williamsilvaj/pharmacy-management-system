package com.farmacia.pharma_manager.backend.funcionario;

import com.farmacia.pharma_manager.backend.cargo.Cargo;
import com.farmacia.pharma_manager.backend.endereco.Endereco;
import com.farmacia.pharma_manager.backend.farmaceutico.Farmaceutico;
import com.farmacia.pharma_manager.backend.gerente.Gerente;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
  @JsonSubTypes.Type(value = Gerente.class, name = "gerente"),
  @JsonSubTypes.Type(value = Farmaceutico.class, name = "farmaceutico")
})
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCargo", nullable = false)
    private Cargo cargo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEndereco", nullable = false)
    private Endereco endereco;

    // Supervisor desse funcionario
    @ManyToOne
    @JoinColumn(name = "idSupervisor", nullable = true)
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

  public Integer getIdSupervisor() {
    return (supervisor != null) ? supervisor.getId() : null;
  }

  public void setIdSupervisor(Integer idSupervisor) {
    if (idSupervisor == null || idSupervisor == 0) {
      this.supervisor = null; // Remove o supervisor
    } else {
      this.supervisor = new Gerente();
      this.supervisor.setId(idSupervisor);
    }
  }
}
