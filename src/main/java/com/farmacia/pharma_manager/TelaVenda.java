package com.farmacia.pharma_manager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaVenda extends JPanel {
  
  private final TelaPrincipal telaPrincipal;
  private JTextField txtCliente;
  private JTextField txtPrecoTotal;
  private List<String> produtosSelecionados;
  private double precoTotal = 0.0;

  public TelaVenda(TelaPrincipal telaPrincipal) {
    this.telaPrincipal = telaPrincipal;
    this.produtosSelecionados = new ArrayList<>();
    setLayout(new BorderLayout());
    setBackground(new Color(240, 248, 255));

    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    formPanel.setBackground(new Color(240, 248, 255));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.gridx = 0;
    gbc.gridy = 0;

    // Campo Cliente
    formPanel.add(new JLabel("Cliente:"), gbc);
    gbc.gridx = 1;
    txtCliente = new JTextField(20);
    txtCliente.setEditable(false);
    formPanel.add(txtCliente, gbc);
    gbc.gridx = 2;
    JButton btnBuscarCliente = telaPrincipal.criarBotao("Buscar Cliente");
    btnBuscarCliente.addActionListener(e -> buscarCliente());
    formPanel.add(btnBuscarCliente, gbc);
    
    // Campo Produtos
    gbc.gridx = 0;
    gbc.gridy++;
    formPanel.add(new JLabel("Produtos:"), gbc);
    gbc.gridx = 1;
    JTextArea txtProdutos = new JTextArea(5, 20);
    txtProdutos.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(txtProdutos);
    formPanel.add(scrollPane, gbc);
    gbc.gridx = 2;
    JButton btnAdicionarProduto = telaPrincipal.criarBotao("Adicionar Produto");
    btnAdicionarProduto.addActionListener(e -> adicionarProduto(txtProdutos));
    formPanel.add(btnAdicionarProduto, gbc);
    
    // Campo Preço Total
    gbc.gridx = 0;
    gbc.gridy++;
    formPanel.add(new JLabel("Preço Total:"), gbc);
    gbc.gridx = 1;
    txtPrecoTotal = new JTextField(10);
    txtPrecoTotal.setEditable(false);
    formPanel.add(txtPrecoTotal, gbc);
    
    // Botão Confirmar Compra
    gbc.gridx = 0;
    gbc.gridy++;
    gbc.gridwidth = 3;
    JButton btnConfirmar = telaPrincipal.criarBotao("Confirmar Compra");
    btnConfirmar.addActionListener(e -> confirmarCompra());
    formPanel.add(btnConfirmar, gbc);
    
    add(formPanel, BorderLayout.CENTER);

    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    botoesPanel.setBackground(new Color(240, 248, 255));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    
    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(telaPrincipal.exibirTelaFarmaceutico()));
    botoesPanel.add(btnVoltar);
    
    add(botoesPanel, BorderLayout.SOUTH);
  }

  private void buscarCliente() {
    JOptionPane.showMessageDialog(this, "Abrir janela de busca de cliente.");
  }

  private void adicionarProduto(JTextArea txtProdutos) {
    String produto = JOptionPane.showInputDialog(this, "Digite o nome do produto:");
    if (produto != null && !produto.trim().isEmpty()) {
      String quantidadeStr = JOptionPane.showInputDialog(this, "Digite a quantidade:");
      try {
        int quantidade = Integer.parseInt(quantidadeStr);
        double precoProduto = quantidade * 10.0; // Simulação do preço unitário = 10
        precoTotal += precoProduto;
        produtosSelecionados.add(produto + " - Qtd: " + quantidade + " - R$" + precoProduto);
        txtProdutos.setText(String.join("\n", produtosSelecionados));
        txtPrecoTotal.setText("R$ " + precoTotal);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void confirmarCompra() {
    if (txtCliente.getText().isEmpty() || produtosSelecionados.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Selecione um cliente e adicione pelo menos um produto!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }
    JOptionPane.showMessageDialog(this, "Compra Confirmada:\nCliente: " + txtCliente.getText() + "\nProdutos:\n" + String.join("\n", produtosSelecionados) + "\nPreço Total: " + txtPrecoTotal.getText());
    telaPrincipal.trocarTela(new TelaVenda(telaPrincipal));
  }
}
