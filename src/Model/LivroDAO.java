package Model;

import java.util.List;

public interface LivroDAO {
    void addLivro(Livro livro) throws DAOException;
    void removeLivro(int id) throws DAOException;
    Livro getLivro(int id) throws DAOException;
    List<Livro> getAllLivros() throws DAOException;
    void alterarLivro(int id, Livro novoLivro) throws DAOException;
}
