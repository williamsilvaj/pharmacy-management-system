package com.farmacia.pharma_manager.backend.cliente;

import com.farmacia.pharma_manager.backend.endereco.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente", uniqueConstraints = {
    @UniqueConstraint(columnNames = "cpf"),
    @UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "telefone")
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = false, length = 45, unique = true)
    private String email;

    @Column(nullable = false, length = 45, unique = true)
    private String telefone;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEndereco", nullable = false)
    private Endereco endereco;

    // Getters e Setters
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

}
