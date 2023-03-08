package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";

    public static Connection getConexao() {
        Connection conexao = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }

        return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexão: " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
      return null;
    }

    public static void fecharConexao(Connection conexao, PreparedStatement ps, ResultSet rs) {
    }
}
