package com.farmacia.pharma_manager.backend.funcionario;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Define que as classes derivadas (como Gerente) serão armazenadas na mesma tabela
@DiscriminatorColumn(name = "tipo_funcionario", discriminatorType = DiscriminatorType.STRING)  // Usado para distinguir as subclasses
public class Funcionario {

    // Atributos da classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerar automaticamente o ID para cada instância
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    // Construtor para inicializar os atributos
    public Funcionario(String nome, String telefone, String cpf, String cargo) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    // Métodos getters e setters para cada atributo
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Funcionario [Nome=" + nome + ", Telefone=" + telefone + ", CPF=" + cpf + ", Cargo=" + cargo + "]";
    }
}
