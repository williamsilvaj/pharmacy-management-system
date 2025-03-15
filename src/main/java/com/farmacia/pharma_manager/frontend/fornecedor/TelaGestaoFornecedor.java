package com.farmacia.pharma_manager.frontend.fornecedor;

import com.farmacia.pharma_manager.frontend.TelaPrincipal;

import javax.swing.*;
import java.awt.*;

public class TelaGestaoFornecedor extends JPanel {

  private final TelaPrincipal telaPrincipal;

  public TelaGestaoFornecedor(TelaPrincipal telaPrincipal) {
    this.telaPrincipal = telaPrincipal;
    setLayout(new BorderLayout());
    setBackground(new Color(240, 248, 255));

    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(5, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));

    JButton btnCadastrarFornecedor = telaPrincipal.criarBotao("Cadastrar fornecedor");
    btnCadastrarFornecedor.addActionListener(e -> exibirFormularioCadastroFornecedor());
    botoesPanel.add(btnCadastrarFornecedor);

    JButton btnConsultarFornecedor = telaPrincipal.criarBotao("Consultar fornecedor");
    //btnConsultarFornecedor.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnConsultarFornecedor);

    JButton btnAlterarFornecedor = telaPrincipal.criarBotao("Alterar dados do fornecedor");
    //btnAlterarFornecedor.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnAlterarFornecedor);

    JButton btnRemoverFornecedor = telaPrincipal.criarBotao("Remover fornecedor");
    //btnRemoverFornecedor.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnRemoverFornecedor);

    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(telaPrincipal.exibirTelaGerente()));
    botoesPanel.add(btnVoltar);

    add(botoesPanel, BorderLayout.SOUTH);
  }

  // 🔹 Método que exibe o formulário de cadastro de um fornecedor
  private void exibirFormularioCadastroFornecedor() {
    removeAll();


    revalidate();
    repaint();
  }
}
