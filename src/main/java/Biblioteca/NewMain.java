/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author marce
 */
public class NewMain extends JFrame {

  public static void limparTela() {
    try {
      if (System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        Runtime.getRuntime().exec("clear");
      }
    } catch (Exception e) {
      System.out.println("Erro ao limpar a tela: " + e.getMessage());
    }
  }

  public void MinhaInterfaceGrafica() {
    // Configurações básicas da janela
    setTitle("Minha Janela");
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Adicionando um botão na janela
    JButton botao = new JButton("Escolha uma opção do Menu: ");

    // Torna a janela visível

    setVisible(true);

    add(botao);

    // Exibe um diálogo com as opções

    String[] opcoes = { "Opção 1", "Opção 2", "Opção 3" };
    int opcaoEscolhida = JOptionPane.showOptionDialog(this, "Escolha uma opção:", "Menu",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

    // Imprime a opção escolhida pelo usuário

    System.out.println("Opção escolhida: " + opcoes[opcaoEscolhida]);

    // Torna a janela visível

    setVisible(true);

  }

  public void MinhaInterfaceGrafica2() {
    // Configurações básicas da janela
    setTitle("Minha Janela");
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Exibe um diálogo com as opções
    String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"};
    int opcaoEscolhida = JOptionPane.showOptionDialog(
            this,
            "Escolha uma opção:",
            "Menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
    );

    // Imprime a opção escolhida pelo usuário
    System.out.println("Opção escolhida: " + opcoes[opcaoEscolhida]);

    // Torna a janela visível
    setVisible(true);
}

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    MinhaInterfaceGrafica janela = new MinhaInterfaceGrafica();
    janela.setVisible(true);

    Livro[] livros;
    Livro livro = new Livro();
    livros = livro.carregaLivros("data/livros.txt");

    Leitor[] leitores;
    Leitor leitor = new Leitor();
    leitores = leitor.carregaLeitores("data/leitores.txt");

    Emprestimo[] emprestimos;
    Emprestimo emprestimo = new Emprestimo();
    emprestimos = emprestimo.carregaEmprestimos("data/emprestimos.txt");

    Fila[] filas;
    Fila fila = new Fila();
    filas = fila.carregaFila("data/fila.txt");

    Operacoes operacoes;
    operacoes = new Operacoes();

    Scanner teclado;
    teclado = new Scanner(System.in);

    String opcao = "";

    for (;;) {
      // limpando a tela
      System.out.print("\033[H\033[2J");
      limparTela();
      System.out.println("Escolha uma opção ");
      System.out.println("1 - Listar livros ");
      System.out.println("2 - Listar livros por ordem de Titulo ");
      System.out.println("3 - Listar livros por ordem de Autor ");
      System.out.println("4 - Buscar livros ");

      System.out.println("5 - Buscar leitor-FAZER");
      System.out.println("6 - Listar leitores por ordem de nome-FAZER");
      System.out.println("7 - Listar leitores por ordem de id-FAZER");
      System.out.println("8 - Realizar empréstimo");
      System.out.println("9 - Devolver livro");

      System.out.println("a - Listar empréstimo");
      System.out.println("b - Listar fila de espera");

      System.out.println("0 - Sair");
      opcao = teclado.nextLine();
      // quando o usuário selecionar uma opção, irá limpando a tela
      System.out.print("\033[H\033[2J");

      switch (opcao) {
        case "1":
          livro.listaLivros(livros);
          break;
        case "2":
          livro.listaOrdenado(livros);
          break;
        case "3":
          livro.listaOrdenadoAutor(livros);
          break;
        case "4":
          operacoes.buscarLivros(livros);
          // livro.buscar(livros);
          break;
        case "8":
          emprestimo.realizarEmprestimo(emprestimos, livros, leitores, filas);
          emprestimos = emprestimo.carregaEmprestimos("data/emprestimos.txt");
          filas = fila.carregaFila("data/fila.txt");
          // teclado = new Scanner(System.in);
          break;
        case "a":
          emprestimo.lista(emprestimos);
          break;
        case "b":
          fila.lista(filas);
          break;
        case "9":
          emprestimo.devolverLivro(emprestimos, livros, leitores, filas);
          emprestimos = emprestimo.carregaEmprestimos("data/emprestimos.txt");
          break;
        case "0":
          System.out.println("FIM");
          System.exit(0);
          break;
        default:
          System.out.println("Escolha outra opção");
      }

    }

  }

  /*

     public void MinhaInterfaceGrafica() {
    // Configurações básicas da janela
    setTitle("Menu Biblioteca");
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Adicionando um painel na janela
    JPanel painel = new JPanel();
    add(painel);

    // Adicionando uma label no painel
    JLabel label = new JLabel("Escolha uma opção:");
    painel.add(label);

    // Adicionando botões no painel
    JButton botao1 = new JButton("Listar livros");
    painel.add(botao1);

    JButton botao2 = new JButton("Listar livros por ordem de Título");
    painel.add(botao2);

    JButton botao3 = new JButton("Listar livros por ordem de Autor");
    painel.add(botao3);

    JButton botao4 = new JButton("Buscar livros");
    painel.add(botao4);

    JButton botao5 = new JButton("Buscar leitor");
    painel.add(botao5);

    JButton botao6 = new JButton("Listar leitores por ordem de nome");
    painel.add(botao6);

    JButton botao7 = new JButton("Listar leitores por ordem de id");
    painel.add(botao7);

    JButton botao8 = new JButton("Realizar empréstimo");
    painel.add(botao8);

    JButton botao9 = new JButton("Devolver livro");
    painel.add(botao9);

    JButton botaoA = new JButton("Listar empréstimo");
    painel.add(botaoA);

    JButton botaoB = new JButton("Listar fila de espera");
    painel.add(botaoB);

    JButton botaoSair = new JButton("Sair");
    painel.add(botaoSair);

    // Ação do botão de sair
    botaoSair.addActionListener(event -> System.exit(0));

    setVisible(true);
}

public static void main(String[] args) {
    new MinhaInterfaceGrafica();
}

   */

}
