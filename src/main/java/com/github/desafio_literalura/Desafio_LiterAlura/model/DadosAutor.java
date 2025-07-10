package com.github.desafio_literalura.Desafio_LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name") String autor,
                         @JsonAlias("birth_year")int ano_nascimento,
                         @JsonAlias("death_year")int ano_falecimento) {
}
