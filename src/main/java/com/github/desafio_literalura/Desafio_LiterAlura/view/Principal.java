package com.github.desafio_literalura.Desafio_LiterAlura.view;

import com.github.desafio_literalura.Desafio_LiterAlura.model.Autor;
import com.github.desafio_literalura.Desafio_LiterAlura.model.DadosLivro;
import com.github.desafio_literalura.Desafio_LiterAlura.model.Livro;
import com.github.desafio_literalura.Desafio_LiterAlura.service.AutorService;
import com.github.desafio_literalura.Desafio_LiterAlura.service.LivroService;
import com.github.desafio_literalura.Desafio_LiterAlura.util.ConsumoApi;
import com.github.desafio_literalura.Desafio_LiterAlura.util.ConverterObjetoLivro;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private LivroService livroService;
    private AutorService autorService;

    public Principal(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    ConverterObjetoLivro conversor = new ConverterObjetoLivro();
    Scanner scanner = new Scanner(System.in);


    public void menu() {
        int opcao = -1;
        String opcoesMenu = """
                
                |---------- ESCOLHA UMA OPCAO ABAIXO -------------|
                |                                                 |
                | 1 - buscar livro pelo titulo                    |
                | 2 - listar livros registrados                   |
                | 3 - listar autores registrados                  |
                | 4 - listar autores vivos em um determinado ano  |
                | 5 - listar livros em um determinado idioma      |
                | 0 - sair                                        |
                |-------------------------------------------------|
                """;
        do {
            System.out.println(opcoesMenu);
            System.out.print("opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    buscarLivroBanco();
                    break;
                case 2:
                    buscarLivros();
                    break;
                case 3:
                    monstarViewAutores();
                    break;
                case 4:
                    autoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    livroPorIdioma();
                    break;

                case 0:
                    System.out.println("Saindo da aplicacao....");
                    break;
                default:
                    System.out.println("Escolha uma opcao");
                    break;
            }

        } while (opcao != 0);
    }


    public void buscarLivroBanco() {
        System.out.println("Informe o nome do livro: ");
        var livroParaBusca = scanner.nextLine();
        var livroBuscado = getLivroBanco(livroParaBusca);
        if (livroBuscado.isEmpty()) {
            System.out.println("Livro nao localizado no banco, buscando na web");
            salvarLivro(livroParaBusca);
        } else {
            montarViewLivro(livroBuscado.get());
        }


    }

    private void buscarLivros() {
        var livros = livroService.getLivros();
        if (!livros.isEmpty()) {
            livros.forEach(this::montarViewLivro);
        } else {
            System.out.println("Nenhum Livro Salvo");
        }
    }


    private void salvarLivro(String titulo) {
        var json = ConsumoApi.consultaAPI(titulo);

        if (json.isPresent()) {
            var livroObtido = conversor.obterDados(json, DadosLivro.class);
            if (livroObtido.isEmpty()) {
                System.err.println("ERRO: Livro nao localizado");
            } else {
                DadosLivro dadosLivro = livroObtido.get();
                Livro novoLivro = new Livro(dadosLivro);
                System.out.println(novoLivro);
                livroService.salvarLivro(novoLivro);
            }
        }
    }

    private Optional<Livro> getLivroBanco(String titulo) {
        return livroService.pegaLivro(titulo);
    }

    private void montarViewLivro(Livro livros) {
        System.out.printf("""
                        \n
                        |--------- LIVRO -----------|
                        | Titulo: %s
                        | Autor: %s
                        | Idioma: %s
                        | Numero de downloads: %d
                        |---------------------------|
                        """, livros.getTitle(),
                livros.getAutor().getNome(),
                livros.getIdioma(),
                livros.getTotalDownloads());
    }

    private void monstarViewAutores() {
        var autores = autorService.autores();

        autores.forEach(autor -> {

            String livros = livroService.livrosPorAutor(autor.getNome());
            System.out.printf("""
                            \n
                            |------------- AUTOR -------------|
                            | Nome: %s
                            | Nascimento: %d
                            | Falecimento: %d
                            | 
                            | Livros: [ %s ]
                            |---------------------------------|
                            \n
                            """, autor.getNome(),
                    autor.getAno_nascimento(),
                    autor.getAno_falecimento(),
                    livros);
        });
    }

    private void autoresVivosEmDeterminadoAno() {
        System.out.println("Informe o ano para pesquisa: ");
        var anoDigitado = scanner.nextInt();
        var autores = autorService.autoresVivosEmCertoAno(anoDigitado);
        if (!autores.isEmpty()) {
            autores
                    .stream()
                    .forEach(autor -> {
                        System.out.printf("""
                                        |------------- AUTOR -------------|
                                        | Nome: %s
                                        | Nascimento: %d
                                        | Falecimento: %d
                                        |---------------------------------|
                                        """, autor.getNome(),
                                autor.getAno_nascimento(),
                                autor.getAno_falecimento());
                    });
        }else {
            System.out.println("Nenhum Autor encontrado para o ano informado");
        }
    }

    private void livroPorIdioma(){
        System.out.println("Idiomas: " + "[ "+livroService.idiomasRegistrados()+" ]");
        System.out.println("Informe um dos idiomas acima: ");
        var idiomaInformado = scanner.nextLine();
        var livros = livroService.livrosPorIdioma(idiomaInformado);
        if (!livros.isEmpty()){
            System.out.printf("----- LIVROS NO IDIOMA %S ------", idiomaInformado.toUpperCase());
            livros.forEach(this::montarViewLivro);
            System.out.println("\n-------------------------------\n");
        }else {
            System.out.println("Idioma nao localizado");
        }

    }

}
