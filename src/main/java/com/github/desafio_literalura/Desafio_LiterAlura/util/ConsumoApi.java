package com.github.desafio_literalura.Desafio_LiterAlura.util;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ConsumoApi {


    private static final String ENDERECO_API = "https://gutendex.com/books/";

    public static Optional<String> consultaAPI(String nomeLivro){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDERECO_API+"?search="+nomeLivro.replace(" ","+").trim()))
                .build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() != 200){
            return Optional.empty();
        }
        return Optional.of(response.body());
    }

}
