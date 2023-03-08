package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.minhaempresa.biblioteca.Livro;

public class LivroDAO {
    private Connection conexao;

    public LivroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean cadastrar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, editora, ano, qtd_copias, qtd_disponiveis) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setInt(5, livro.getQtdCopias());
            stmt.setInt(6, livro.getQtdDisponiveis());

            int resultado = stmt.executeUpdate();
            stmt.close();

            return resultado == 1;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano = ?, qtd_copias = ?, qtd_disponiveis = ? WHERE id_livro = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setInt(5, livro.getQtdCopias());
            stmt.setInt(6, livro.getQtdDisponiveis());
            stmt.setInt(7, livro.getId());

            int resultado = stmt.executeUpdate();
            stmt.close();

            return resultado == 1;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM livros WHERE id_livro = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            int resultado = stmt.executeUpdate();
            stmt.close();

            return resultado == 1;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
            return false;
        }
    }

    public List<Livro> buscarTodos() {
        String sql = "SELECT * FROM livros";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            List<Livro> livros = new ArrayList<Livro>();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAno(rs.getInt("ano"));
                livro.setQtdCopias(rs.getInt("qtd_copias"));
                livro.setQtdDisponiveis(rs.getInt("qtd_disponiveis"));

                livros.add(livro);
            }

            rs.close();
            stmt.close();

            return livros;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca um livro pelo parâmetro informado.
     * 
     * @param parametro O parâmetro de busca.
     * @return Uma lista de livros que atendem ao parâmetro informado.
     * @throws SQLException
     */
    public List<Livro> buscarPor(String parametro) throws SQLException {
        List<Livro> listaLivros = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        String sql = "SELECT * FROM livro WHERE titulo LIKE ? OR autor LIKE ? OR editora LIKE ?";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, "%" + parametro + "%");
        statement.setString(2, "%" + parametro + "%");
        statement.setString(3, "%" + parametro + "%");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Livro livro = new Livro();
            livro.setId(resultSet.getInt("id"));
            livro.setTitulo(resultSet.getString("titulo"));
            livro.setAutor(resultSet.getString("autor"));
            livro.setEditora(resultSet.getString("editora"));
            livro.setAnoPublicacao(resultSet.getInt("ano_publicacao"));
            livro.setQuantidade(resultSet.getInt("quantidade"));

            listaLivros.add(livro);
        }

        resultSet.close();
        statement.close();
        conexao.close();

        return listaLivros;
    }

    public void salvar(Livro livro) {
    }

    public static Livro buscarPorId(int int1) {
      return null;
    }
}
// public void salvar(Livro livro) {
// }
