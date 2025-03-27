package com.farmacia.pharma_manager.backend.endereco;

import jakarta.persistence.*;

@Entity
@Table(name="endereco")
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idEndereco")
  private int idEndereco;

  @Column(name = "rua", nullable = false)
  private String rua;

  @Column(name = "numero", nullable = false)
  private String numero;

  @Column(name = "bairro", nullable = false)
  private String bairro;

  @Column(name = "cidade", nullable = false)
  private String cidade;

  @Column(name = "estado", nullable = false)
  private String estado;

  @Column(name = "cep", nullable = false)
  private String cep;


  // Construtor
  public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
    this.rua = rua;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.cep = cep;
  }

  public Endereco() {}

  public Endereco(int idEndereco, String rua, String numero, String bairro, String cidade, String estado, String cep) {
    this.idEndereco = idEndereco;
    this.rua = rua;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.cep = cep;
  }

  public int getIdEndereco() {
    return idEndereco;
  }

  public void setIdEndereco(int idEndereco) {
    this.idEndereco = idEndereco;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  @Override
  public String toString() {
    return rua + ", " + numero + " - " + bairro + ", " + cidade + ", " + estado + " - CEP: " + cep;
  }
}
