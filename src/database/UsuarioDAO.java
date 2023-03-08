package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.minhaempresa.biblioteca.Usuario;

public class UsuarioDAO {

    private static Connection conexao;

    public UsuarioDAO() {
        conexao = Conexao.getConnection();
    }

    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, matricula, curso) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getMatricula());
            stmt.setString(3, usuario.getCurso());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setMatricula(rs.getString("matricula"));
                usuario.setCurso(rs.getString("curso"));
                listaUsuarios.add(usuario);
            }

            rs.close();
            stmt.close();

            return listaUsuarios;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, matricula = ?, curso = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getMatricula());
            stmt.setString(3, usuario.getCurso());
            stmt.setInt(4, usuario.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setMatricula(rs.getString("matricula"));
                usuario.setCurso(rs.getString("curso"));
                return usuario;
            }

            rs.close();
            stmt.close();

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvar(Usuario usuario) {
    }
}
