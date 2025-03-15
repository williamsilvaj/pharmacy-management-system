package com.farmacia.pharma_manager.frontend.funcionario;

import com.farmacia.pharma_manager.frontend.TelaPrincipal;

import javax.swing.*;
import java.awt.*;

public class TelaGestaoFuncionario extends JPanel {

  private final TelaPrincipal telaPrincipal;

  public TelaGestaoFuncionario(TelaPrincipal telaPrincipal) {
    this.telaPrincipal = telaPrincipal;
    setLayout(new BorderLayout());
    setBackground(new Color(240, 248, 255));

    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(5, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));

    JButton btnCadastrarFuncionario = telaPrincipal.criarBotao("Cadastrar funcionario");
    btnCadastrarFuncionario.addActionListener(e -> exibirFormularioCadastroFuncionario());
    botoesPanel.add(btnCadastrarFuncionario);

    JButton btnConsultarFuncionario = telaPrincipal.criarBotao("Consultar funcionario");
    //btnConsultarFuncionario.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnConsultarFuncionario);

    JButton btnAlterarFuncionario = telaPrincipal.criarBotao("Alterar dados do funcionario");
    //btnAlterarFuncionario.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnAlterarFuncionario);

    JButton btnRemoverFuncionario = telaPrincipal.criarBotao("Remover funcionario");
    //btnRemoverFuncionario.addActionListener(e -> exibirFormulario());
    botoesPanel.add(btnRemoverFuncionario);

    JButton btnVoltar = telaPrincipal.criarBotao("Voltar");
    btnVoltar.addActionListener(e -> telaPrincipal.trocarTela(telaPrincipal.exibirTelaGerente()));
    botoesPanel.add(btnVoltar);

    add(botoesPanel, BorderLayout.SOUTH);
  }

  // 🔹 Método que exibe o formulário de cadastro de funcionario
  private void exibirFormularioCadastroFuncionario() {
    removeAll();


    revalidate();
    repaint();
  }
}
