package com.farmacia.pharma_manager;

import javax.swing.*;
import java.awt.*;

public class TelaGestaoProduto extends JPanel {
  
  private final TelaPrincipal telaPrincipal;

  public TelaGestaoProduto(TelaPrincipal telaPrincipal) {
    this.telaPrincipal = telaPrincipal;
    setLayout(new BorderLayout());
    setBackground(new Color(240, 248, 255));

    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(5, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));
    
    JButton btnCadastrarProduto = telaPrincipal.criarBotao("Cadastrar produto");
    btnCadastrarProduto.addActionListener(e -> exibirFormularioCadastroProduto());
    botoesPanel.add(btnCadastrarProduto);
    
    JButton btnListarProduto = telaPrincipal.criarBotao("Listar produtos");
    //btnListarProduto.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnListarProduto);
    
    JButton btnAlterarProduto = telaPrincipal.criarBotao("Alterar dados do produto");
    //btnAlterarProduto.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnAlterarProduto);
    
    JButton btnRemoverProduto = telaPrincipal.criarBotao("Remover produto");
    //btnRemoverProduto.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnRemoverProduto);

    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(telaPrincipal.exibirTelaGerente()));
    botoesPanel.add(btnVoltar);

    add(botoesPanel, BorderLayout.SOUTH);
  }

  // 🔹 Método que exibe o formulário de cadastro de produto
  private void exibirFormularioCadastroProduto() {
    removeAll();
    setLayout(new GridLayout(5, 2, 10, 10));

    add(telaPrincipal.criarLabel("Nome do Produto:"));
    JTextField txtNome = telaPrincipal.criarCampoTexto();
    add(txtNome);
    
    add(telaPrincipal.criarLabel("Fabricante:"));
    JTextField txtFabricante = telaPrincipal.criarCampoTexto();
    add(txtFabricante);

    add(telaPrincipal.criarLabel("Concentração:"));
    JTextField txtConcentracao = telaPrincipal.criarCampoTexto();
    add(txtConcentracao);

    JButton btnSalvar = telaPrincipal.criarBotao("Salvar");
    btnSalvar.addActionListener(e -> {
      String nome = txtNome.getText();
      String concentracao = txtConcentracao.getText();
      String fabricante = txtFabricante.getText();

      JOptionPane.showMessageDialog(this, "Produto Cadastrado:\n" +
                    "Nome: " + nome + "\nFabricante: " + fabricante + "\nConcentração: " + concentracao);
            
      telaPrincipal.trocarTela(new TelaGestaoProduto(telaPrincipal));
    });

    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(new TelaGestaoProduto(telaPrincipal)));

    add(btnSalvar);
    add(btnVoltar);

    revalidate();
    repaint();
  }
}
