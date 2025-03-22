package com.farmacia.pharma_manager.backend.cliente;

import com.farmacia.pharma_manager.backend.endereco.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes") // Nome da tabela no banco de dados
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerar automaticamente o ID para cada instância
    @Column(name = "id")
    private Integer id; // Chave primária

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "endereco_id") // Definindo o relacionamento com a entidade Endereco
    private Endereco endereco;

    // Construtor
    public Cliente(String nome, String cpf, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente [Nome=" + nome + ", CPF=" + cpf + ", Email=" + email + ", Telefone=" + telefone + ", Endereço=" + endereco + "]";
    }
}
