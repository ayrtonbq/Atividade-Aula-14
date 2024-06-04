package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImpl implements LivroDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/aula14";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void addLivro(Livro livro) throws DAOException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO livros (id, titulo, autor) VALUES (?, ?, ?)")) {
            stmt.setInt(1, livro.getId());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getAutor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    @Override
    public void removeLivro(int id) throws DAOException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM livros WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Livro não encontrado.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao remover livro: " + e.getMessage());
        }
    }

    @Override
    public Livro getLivro(int id) throws DAOException {
        Livro livro = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM livros WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"));
            } else {
                throw new DAOException("Livro não encontrado.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar livro: " + e.getMessage());
        }
        return livro;
    }

    @Override
    public List<Livro> getAllLivros() throws DAOException {
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM livros");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao listar livros: " + e.getMessage());
        }
        return livros;
    }
    
    @Override
    public void alterarLivro(int id, Livro novoLivro) throws DAOException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE livros SET titulo = ?, autor = ? WHERE id = ?")) {
            stmt.setString(1, novoLivro.getTitulo());
            stmt.setString(2, novoLivro.getAutor());
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Livro não encontrado.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao alterar livro: " + e.getMessage());
        }
    }    
}

