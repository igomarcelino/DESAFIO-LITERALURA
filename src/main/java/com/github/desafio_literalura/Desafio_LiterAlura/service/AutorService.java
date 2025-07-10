package com.github.desafio_literalura.Desafio_LiterAlura.service;

import com.github.desafio_literalura.Desafio_LiterAlura.model.Autor;
import com.github.desafio_literalura.Desafio_LiterAlura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }



    public List<Autor> autores(){
        return autorRepository.findAll();
    }

    public List<Autor> autoresVivosEmCertoAno(int ano){
        return autorRepository.autoresVivosEmCertoAno(ano)
                .orElseGet(Collections::emptyList);
    }
 }
