package com.github.desafio_literalura.Desafio_LiterAlura.repository;

import com.github.desafio_literalura.Desafio_LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNomeIgnoreCase(String autorLivro);

    @Query("SELECT a FROM Autor a WHERE :ano BETWEEN a.ano_nascimento AND a.ano_falecimento ")
    Optional<List<Autor>> autoresVivosEmCertoAno(int ano);
}
