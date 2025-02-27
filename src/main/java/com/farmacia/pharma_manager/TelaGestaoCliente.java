package com.farmacia.pharma_manager;

import javax.swing.*;
import java.awt.*;

public class TelaGestaoCliente extends JPanel {
  
  private final TelaPrincipal telaPrincipal;

  public TelaGestaoCliente(TelaPrincipal telaPrincipal) {
    this.telaPrincipal = telaPrincipal;
    setLayout(new BorderLayout());
    setBackground(new Color(240, 248, 255));

    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(5, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));

    JButton btnCadastrarCliente = telaPrincipal.criarBotao("Cadastrar cliente");
    btnCadastrarCliente.addActionListener(e -> exibirFormularioCadastroCliente());
    botoesPanel.add(btnCadastrarCliente);
    
    JButton btnConsultarCliente = telaPrincipal.criarBotao("Consultar cliente");
    //btnConsultarCliente.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnConsultarCliente);
    
    JButton btnAlterarCliente = telaPrincipal.criarBotao("Alterar dados do cliente");
    //btnAlterarCliente.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnAlterarCliente);
    
    JButton btnRemoverCliente = telaPrincipal.criarBotao("Remover cliente");
    //btnRemoverCliente.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnRemoverCliente);
    
    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(telaPrincipal.exibirTelaGerente()));
    botoesPanel.add(btnVoltar);

    add(botoesPanel, BorderLayout.SOUTH);
  }

  // 🔹 Método que exibe o formulário de cadastro de cliente
  public void exibirFormularioCadastroCliente() {
    removeAll();

    revalidate();
    repaint();
  }
}
