package com.farmacia.pharma_manager.backend.despesa;

import jakarta.persistence.*;
import java.util.Date;

import com.farmacia.pharma_manager.backend.gerente.Gerente;

@Entity
@Table(name = "despesa")
public class Despesa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idDespesa")
  private Integer idDespesa;

  @Column(name = "descricao", nullable = false)
  private String descricao;

  @Column(name = "data", nullable = false)
  private Date data;

  @Column(name = "valor", nullable = false)
  private Double valor;

  @ManyToOne
  @JoinColumn(name = "idGerente", nullable = false)
  private Gerente gerente;


  public Despesa() {

  }

  public Despesa(String descricao, Date data, Double valor, Gerente Gerente) {
    this.descricao = descricao;
    this.data = data;
    this.valor = valor;
    this.gerente = Gerente;
  }

  // Getters e Setters
  public Integer getIdDespesa() {
    return idDespesa;
  }

  public void setIdDespesa(Integer idDespesa) {
    this.idDespesa = idDespesa;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Gerente getGerente() {
    return gerente;
  }

  public void setGerente(Gerente gerente) {
    this.gerente = gerente;
  }
}
