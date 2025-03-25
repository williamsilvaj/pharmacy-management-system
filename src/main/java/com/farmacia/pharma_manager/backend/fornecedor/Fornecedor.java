package com.farmacia.pharma_manager.backend.fornecedor;

import jakarta.persistence.*;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFornecedor")
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status")
    private boolean status; 

    // Construtores
    public Fornecedor() {}

    public Fornecedor(String nome, String cnpj, String email, boolean status) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.status = status;
    }


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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fornecedor [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", email=" + email + ", status=" + (status ? "Ativo" : "Inativo") + "]";
    }
}
