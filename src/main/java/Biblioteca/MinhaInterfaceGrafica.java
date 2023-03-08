package Biblioteca;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MinhaInterfaceGrafica extends JFrame {

    public MinhaInterfaceGrafica() {
        // Configurações básicas da janela
        setTitle("Minha Janela");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adicionando um botão na janela
        JButton botao = new JButton("Clique Aqui!");
        add(botao);
    }

    public static void main(String[] args) {
        // Criando e exibindo a janela
        MinhaInterfaceGrafica janela = new MinhaInterfaceGrafica();
        janela.setVisible(true);
    }
}
