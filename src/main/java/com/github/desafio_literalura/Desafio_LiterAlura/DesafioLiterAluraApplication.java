package com.github.desafio_literalura.Desafio_LiterAlura;

import com.github.desafio_literalura.Desafio_LiterAlura.service.AutorService;
import com.github.desafio_literalura.Desafio_LiterAlura.service.LivroService;
import com.github.desafio_literalura.Desafio_LiterAlura.view.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiterAluraApplication implements CommandLineRunner {

	@Autowired
	LivroService livroService;
	@Autowired
	AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(DesafioLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Principal principal = new Principal(livroService, autorService);
		principal.menu();
	}
}
