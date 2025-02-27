package com.farmacia.pharma_manager;

import javax.swing.*;
import java.awt.*;

public class TelaRelatorios extends JPanel {
  
  private final TelaPrincipal telaPrincipal;

  public TelaRelatorios(TelaPrincipal telaPrincipal) {
    this.telaPrincipal = telaPrincipal;
    setLayout(new BorderLayout());
    setBackground(new Color(240, 248, 255));

    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(5, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));

    JButton btnGerarRelatorioVendas = telaPrincipal.criarBotao("Gerar relatório de vendas");
    //btnGerarRelatorioVendas.addActionListener(e -> exibir());
    botoesPanel.add(btnGerarRelatorioVendas);
    
    JButton btnGerarRelatorioEstoque = telaPrincipal.criarBotao("Gerar relatório de estoque");
    //btnGerarRelatorioEstoque.addActionListener(e -> exibir());
    botoesPanel.add(btnGerarRelatorioEstoque);
    
    JButton btnGerarRelatorioDespesas = telaPrincipal.criarBotao("Gerar relatório de despesas");
    //btnGerarRelatorioDespesas.addActionListener(e -> exibir());
    botoesPanel.add(btnGerarRelatorioDespesas);
    
    JButton btnGerarRelatorioReceitas = telaPrincipal.criarBotao("Gerar relatório de receitas");
    //btnGerarRelatorioReceitas.addActionListener(e -> exibir());
    botoesPanel.add(btnGerarRelatorioReceitas);
    
    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(telaPrincipal.exibirTelaGerente()));
    botoesPanel.add(btnVoltar);

    add(botoesPanel, BorderLayout.SOUTH);
  }

}
