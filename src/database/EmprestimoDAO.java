package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.minhaempresa.biblioteca.Emprestimo;
import com.minhaempresa.biblioteca.Livro;
import com.minhaempresa.biblioteca.Usuario;

public class EmprestimoDAO {
    
    private Connection conexao;
    
    public EmprestimoDAO() {
        conexao = Conexao.getConnection();
    }
    
    public void adicionar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_usuario, id_livro, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            // stmt.setString(3, emprestimo.getDataEmprestimo());
            // stmt.setString(4, emprestimo.getDataDevolucao());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Emprestimo> listar() {
        String sql = "SELECT e.id, e.data_emprestimo, e.data_devolucao, u.id AS usuario_id, u.nome AS usuario_nome, l.id AS livro_id, l.titulo AS livro_titulo FROM emprestimo e INNER JOIN usuario u ON e.id_usuario = u.id INNER JOIN livro l ON e.id_livro = l.id";
        List<Emprestimo> emprestimos = new ArrayList<>();
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(0, null, null, null, null, false);
                Usuario usuario = new Usuario();
                Livro livro = new Livro();
                
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setDataEmprestimo(rs.getString("data_emprestimo"));
                emprestimo.setDataDevolucao(rs.getString("data_devolucao"));
                
                usuario.setId(rs.getInt("usuario_id"));
                usuario.setNome(rs.getString("usuario_nome"));
                
                livro.setId(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("livro_titulo"));
                
                emprestimo.setUsuario(usuario);
                emprestimo.setLivro(livro);
                
                emprestimos.add(emprestimo);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return emprestimos;
    }
    
    public void remover(int id) {
        String sql = "DELETE FROM emprestimo WHERE id=?";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
 * Busca todos os empréstimos feitos por um determinado usuário.
 * @param usuario o usuário a ser buscado.
 * @return uma lista de empréstimos realizados pelo usuário.
 */
public List<Emprestimo> buscarPorUsuario(Usuario usuario) {
  Connection conexao = null;
  PreparedStatement ps = null;
  ResultSet rs = null;
  List<Emprestimo> emprestimos = new ArrayList<>();

  try {
      conexao = Conexao.getConexao();
      ps = conexao.prepareStatement("SELECT * FROM emprestimo WHERE id_usuario = ?");
      ps.setInt(1, usuario.getId());
      rs = ps.executeQuery();

      while (rs.next()) {
          Livro livro = LivroDAO.buscarPorId(rs.getInt("id_livro"));
          Usuario user = UsuarioDAO.buscarPorId(rs.getInt("id_usuario"));
          Emprestimo emprestimo = new Emprestimo(rs.getInt("id"), livro, user, rs.getDate("data_emprestimo").toLocalDate());
          emprestimos.add(emprestimo);
      }

  } catch (SQLException e) {
      System.out.println("Erro ao buscar empréstimos por usuário: " + e);
  } finally {
      Conexao.fecharConexao(conexao, ps, rs);
  }

  return emprestimos;
}

    public void salvar(Emprestimo emprestimo) {
    }
}
