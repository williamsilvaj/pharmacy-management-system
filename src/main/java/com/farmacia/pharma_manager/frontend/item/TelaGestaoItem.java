package com.farmacia.pharma_manager.frontend.item;

import com.farmacia.pharma_manager.frontend.TelaPrincipal;

import javax.swing.*;
import java.awt.*;

public class TelaGestaoItem extends JPanel {

  private final TelaPrincipal telaPrincipal;

  public TelaGestaoItem(TelaPrincipal telaPrincipal) {
    this.telaPrincipal = telaPrincipal;
    setLayout(new BorderLayout());
    setBackground(new Color(240, 248, 255));

    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(7, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));

    JButton btnCadastrarItem = telaPrincipal.criarBotao("Cadastrar item");
    btnCadastrarItem.addActionListener(e -> exibirFormularioCadastroItem());
    botoesPanel.add(btnCadastrarItem);

    JButton btnConsultarItem = telaPrincipal.criarBotao("Consultar item");
    //btnConsultarItem.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnConsultarItem);

    JButton btnAlterarItem = telaPrincipal.criarBotao("Alterar dados do item");
    //btnAlterarItem.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnAlterarItem);

    JButton btnRemoverItem = telaPrincipal.criarBotao("Remover item");
    //btnRemoverItem.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnRemoverItem);

    JButton btnCadastrarEstoque = telaPrincipal.criarBotao("Cadastrar estoque");
    //btnCadastrarEstoque.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnCadastrarEstoque);

    JButton btnListarEstoque = telaPrincipal.criarBotao("Listar Estoque");
    //btnListarEstoque.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnListarEstoque);

    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(telaPrincipal.exibirTelaGerente()));
    botoesPanel.add(btnVoltar);

    add(botoesPanel, BorderLayout.SOUTH);
  }

  // 🔹 Método que exibe o formulário de cadastro de item
  private void exibirFormularioCadastroItem() {
    removeAll();


    revalidate();
    repaint();
  }
}
