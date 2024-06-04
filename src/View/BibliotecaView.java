package View;

import java.util.Scanner;

import Controller.LivroController;
import Model.Livro;

public class BibliotecaView {
    private LivroController livroController;
    private Scanner scanner;

    public BibliotecaView(LivroController livroController) {
        this.livroController = livroController;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar um livro");
            System.out.println("2. Remover um livro");
            System.out.println("3. Buscar um livro");
            System.out.println("4. Listar todos os livros");
            System.out.println("5. Alterar um livro");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    removerLivro();
                    break;
                case 3:
                    buscarLivro();
                    break;
                case 4:
                    listarLivros();
                    break;
                case 5:
                    alterarLivro();
                    break;    
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private void adicionarLivro() {
        System.out.println("\nAdicionar Livro:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        Livro livro = new Livro(id, titulo, autor);
        livroController.adicionarLivro(livro);
    }

    private void removerLivro() {
        System.out.println("\nRemover Livro:");
        System.out.print("ID do livro a ser removido: ");
        int id = scanner.nextInt();
        livroController.removerLivro(id);
    }

    private void buscarLivro() {
        System.out.println("\nBuscar Livro:");
        System.out.print("ID do livro a ser buscado: ");
        int id = scanner.nextInt();
        livroController.buscarLivro(id);
    }

    private void listarLivros() {
        System.out.println("\nListar Livros:");
        livroController.listarLivros();
    }
    
    private void alterarLivro() {
        System.out.println("\nAlterar Livro:");
        System.out.print("ID do livro a ser alterado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Novo Título: ");
        String novoTitulo = scanner.nextLine();
        System.out.print("Novo Autor: ");
        String novoAutor = scanner.nextLine();

        Livro novoLivro = new Livro(id, novoTitulo, novoAutor);
        livroController.alterarLivro(id, novoLivro);
    }
}
