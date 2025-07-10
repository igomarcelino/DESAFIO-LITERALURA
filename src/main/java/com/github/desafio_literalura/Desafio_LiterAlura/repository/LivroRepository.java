package com.github.desafio_literalura.Desafio_LiterAlura.repository;

import com.github.desafio_literalura.Desafio_LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitleContainingIgnoreCase(String titulo);

    @Query("SELECT l.title FROM Livro l JOIN l.autor a WHERE a.nome = :nomeAutor ")
    Optional<List<String>> livrosPorAutor(String nomeAutor);

    @Query("SELECT DISTINCT l.idioma FROM Livro l")
    Optional<List<String>> idiomas();

    Optional<List<Livro>> findByIdioma(String idioma);
}
