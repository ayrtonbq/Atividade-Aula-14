package Controller;

import java.util.List;
import Model.DAOException;
import Model.Livro;
import Model.LivroDAO;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public void adicionarLivro(Livro livro) {
        try {
            livroDAO.addLivro(livro);
            System.out.println("Livro adicionado com sucesso!");
        } catch (DAOException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    public void removerLivro(int id) {
        try {
            livroDAO.removeLivro(id);
            System.out.println("Livro removido com sucesso!");
        } catch (DAOException e) {
            System.out.println("Erro ao remover livro: " + e.getMessage());
        }
    }

    public void buscarLivro(int id) {
        try {
            Livro livro = livroDAO.getLivro(id);
            System.out.println("Livro encontrado: " + livro);
        } catch (DAOException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    public void listarLivros() {
        try {
            List<Livro> livros = livroDAO.getAllLivros();
            System.out.println("Livros:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        } catch (DAOException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }
    
    public void alterarLivro(int id, Livro novoLivro) {
        try {
            livroDAO.alterarLivro(id, novoLivro);
            System.out.println("Livro alterado com sucesso!");
        } catch (DAOException e) {
            System.out.println("Erro ao alterar livro: " + e.getMessage());
        }
    }    
}
