package com.github.desafio_literalura.Desafio_LiterAlura.service;

import com.github.desafio_literalura.Desafio_LiterAlura.model.Autor;
import com.github.desafio_literalura.Desafio_LiterAlura.model.Livro;
import com.github.desafio_literalura.Desafio_LiterAlura.repository.AutorRepository;
import com.github.desafio_literalura.Desafio_LiterAlura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void salvarLivro(Livro livro){
        //pega o nome do autor do livro
        var autorLivro = livro.getAutor().getNome();
        //verifica se o autor ja tem um cadastro no banco
        var verificaAutorNoBanco = autorRepository.findByNomeIgnoreCase(autorLivro);

        //aqui utilizamos a logia, caso o optional possua um valor utilizamos ele, se nao criamos outro autor
        Autor autor = verificaAutorNoBanco.orElseGet(()-> new Autor(livro.getAutor()));


        Livro novoLivro = new Livro(livro,autor);

        livroRepository.save(novoLivro);
    }
    public Optional<Livro> pegaLivro(String titulo){
        var livro =  livroRepository.findByTitleContainingIgnoreCase(titulo);

        return livro;
    }

    public List<Livro> getLivros(){
        return livroRepository
                .findAll();
    }

    public String livrosPorAutor(String nome){
        List<String> livros = livroRepository.livrosPorAutor(nome).orElseGet(Collections::emptyList);
        if (!livros.isEmpty()){
            return livros
                    .stream()
                    .map(String::new)
                    .collect(Collectors.joining(", "));
        }else {
            return "";
        }
    }

    public String idiomasRegistrados(){
        return livroRepository.idiomas()
                .stream()
                .flatMap(strings -> strings.stream().map(String::new))
                .collect(Collectors.joining(", "));

    }

    public List<Livro> livrosPorIdioma(String idioma){
        return livroRepository.findByIdioma(idioma)
                .orElseGet(Collections::emptyList);
    }



}
