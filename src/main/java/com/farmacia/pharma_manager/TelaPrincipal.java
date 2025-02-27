package com.farmacia.pharma_manager;

import java.awt.*;
import javax.swing.*;

public class TelaPrincipal extends JFrame {
  private JPanel painelPrincipal; // Painel principal fixo
  private JPanel telaAtual; // Painel que representa a tela atual

  public TelaPrincipal() {
    setTitle("Phama Manager - Sistema de Gestão de Farmácia");
    setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(240, 248, 255));
    
    configurarTela(); // Configura os componentes da tela principal
    trocarTela(criarPainelInicial()); // Define a tela inicial
        
    setVisible(true);
  }

  private void configurarTela() {
    setLayout(new BorderLayout());
        
    // Painel do topo com o botão para voltar ao inicio
    JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    painelTopo.setBackground(new Color(157, 222, 240));

    JButton btnHome = criarBotao("Voltar ao início");
    btnHome.addActionListener(e -> trocarTela(criarPainelInicial()));
    painelTopo.add(btnHome);
    add(painelTopo, BorderLayout.NORTH);
        
    painelPrincipal = new JPanel(new BorderLayout());
    painelPrincipal.setBackground(new Color(240, 248, 255));
    add(painelPrincipal, BorderLayout.CENTER);
  }

  public void trocarTela(JPanel novaTela) {
    if (telaAtual != null) {
      painelPrincipal.remove(telaAtual);
    }
        
    telaAtual = novaTela;
    painelPrincipal.add(telaAtual, BorderLayout.CENTER);
    painelPrincipal.revalidate();
    painelPrincipal.repaint();
  }

  // Método que cria a tela inicial
  public JPanel criarPainelInicial() {
    JPanel painel = new JPanel(new BorderLayout());
    painel.setBackground(new Color(240, 248, 255));
    setTitle("Phama Manager - Sistema de Gestão de Farmácia");

    JPanel botoesPanel = new JPanel(new GridLayout(3, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));
    
    JButton btnGerente = criarBotao("Gerente");
    btnGerente.addActionListener(e -> trocarTela(exibirTelaGerente()));
    botoesPanel.add(btnGerente);

    JButton btnFarmaceutico = criarBotao("Farmacêutico");
    btnFarmaceutico.addActionListener(e -> trocarTela(exibirTelaFarmaceutico()));
    botoesPanel.add(btnFarmaceutico);

    JButton btnSair = criarBotao("Sair");
    btnSair.addActionListener(e -> System.exit(0));
    botoesPanel.add(btnSair);
        
    painel.add(botoesPanel, BorderLayout.SOUTH);
        
    return painel;
  }

  // Método que cria a tela do Gerente
  public JPanel exibirTelaGerente() {
    setTitle("(Gerente) Phama Manager - Sistema de Gestão de Farmácia");
    JPanel painelGerente = new JPanel(new BorderLayout());
    painelGerente.setBackground(new Color(240, 248, 255));
    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(7, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));
    
    JButton btnGerenciarProduto = criarBotao("Gerenciar Produtos");
    btnGerenciarProduto.addActionListener(e -> trocarTela(new TelaGestaoProduto(this)));
    botoesPanel.add(btnGerenciarProduto);
    
    JButton btnGerenciarItem = criarBotao("Gerenciar Itens");
    btnGerenciarItem.addActionListener(e -> trocarTela(new TelaGestaoItem(this)));
    botoesPanel.add(btnGerenciarItem);
    
    JButton btnGerenciarClientes = criarBotao("Gerenciar Clientes");
    btnGerenciarClientes.addActionListener(e -> trocarTela(new TelaGestaoCliente(this)));
    botoesPanel.add(btnGerenciarClientes);
    
    JButton btnGerenciarFuncionario = criarBotao("Gerenciar Funcionários");
    btnGerenciarFuncionario.addActionListener(e -> trocarTela(new TelaGestaoFuncionario(this)));
    botoesPanel.add(btnGerenciarFuncionario);
    
    JButton btnGerenciarForncedor = criarBotao("Gerenciar Fornecedores");
    btnGerenciarForncedor.addActionListener(e -> trocarTela(new TelaGestaoFornecedor(this)));
    botoesPanel.add(btnGerenciarForncedor);
    
    JButton btnGerarRelatorios = criarBotao("Gerar Relatórios");
    btnGerarRelatorios.addActionListener(e -> trocarTela(new TelaRelatorios(this)));
    botoesPanel.add(btnGerarRelatorios);

    JButton btnVoltar = criarBotao("Voltar");
    btnVoltar.addActionListener(e -> trocarTela(criarPainelInicial()));
    botoesPanel.add(btnVoltar);

    painelGerente.add(botoesPanel, BorderLayout.SOUTH);
    return painelGerente;
  }

  // Método que cria a tela do Farmacêutico
  public JPanel exibirTelaFarmaceutico() {
    setTitle("(Farmacêutico) Phama Manager - Sistema de Gestão de Farmácia");
    JPanel painelFarmaceutico = new JPanel(new BorderLayout());
    painelFarmaceutico.setBackground(new Color(240, 248, 255));
    JPanel botoesPanel = new JPanel();
    botoesPanel.setLayout(new GridLayout(3, 1, 10, 10));
    botoesPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
    botoesPanel.setBackground(new Color(240, 248, 255));

    JButton btnGerenciarClientes = criarBotao("Cadastrar Cliente");
    btnGerenciarClientes.addActionListener(e -> trocarTela(new TelaGestaoCliente(this)));
    botoesPanel.add(btnGerenciarClientes);
    
    JButton btnVenderProduto = criarBotao("Vender Produto");
    btnVenderProduto.addActionListener(e -> trocarTela(new TelaVenda(this)));
    botoesPanel.add(btnVenderProduto);

    JButton btnVoltar = criarBotao("Voltar");
    btnVoltar.addActionListener(e -> trocarTela(criarPainelInicial()));
    botoesPanel.add(btnVoltar);

    painelFarmaceutico.add(botoesPanel, BorderLayout.SOUTH);

    return painelFarmaceutico;
  }
  
  public JButton criarBotao(String texto) {
    JButton botao = new JButton(texto);
    botao.setFont(new Font("Arial", Font.BOLD, 18));
    botao.setBackground(new Color(30, 144, 255));
    botao.setForeground(Color.WHITE);
    botao.setFocusPainted(false);
    botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borda preta
    botao.setBorderPainted(true);
    botao.setPreferredSize(new Dimension(220, 60)); // Aumenta o tamanho do botão
    botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    return botao;
  }
  
  public JLabel criarLabel(String texto) {
    JLabel label = new JLabel(texto);
    label.setFont(new Font("Arial", Font.BOLD, 16));
    return label;
  }

  public JTextField criarCampoTexto() {
    JTextField campo = new JTextField();
    campo.setFont(new Font("Arial", Font.PLAIN, 14));
    campo.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY, 1),
        BorderFactory.createEmptyBorder(50, 50, 50, 50)
    ));
    return campo;
  }
}
