package com.farmacia.pharma_manager.backend.funcionario;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    // Atributos da classe

    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "cargo")
    private String cargo;

    // Construtor para inicializar os atributos
    public Funcionario(String nome, String telefone, String cpf, String cargo) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    // Métodos getters e setters para cada atributo

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

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }



    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Funcionario [Nome=" + nome + ", Telefone=" + telefone + ", CPF=" + cpf + "]";
    }

}


