package com.github.desafio_literalura.Desafio_LiterAlura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class ConverterObjetoLivro {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> Optional<T> obterDados(Optional<String> json, Class<T> tClass) {
        if (json.isEmpty()) {
            return null;
        }
        try {
            JsonNode root = mapper.readTree(json.get());
            JsonNode result = root.get("results").get(0);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(mapper.readValue(result.toString(), tClass));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
