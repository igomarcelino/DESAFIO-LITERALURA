
-----

# Desafio LiterAlura 📚

## 📖 Sobre o Projeto

O **LiterAlura** é um Catálogo de Livros interativo via console, desenvolvido como parte do desafio do curso "Formação Spring Boot" da Alura. O projeto tem como objetivo principal consumir a API pública [Gutendex](https://gutendex.com/), uma API web para livros de domínio público, persistir os dados em um banco de dados e permitir que o usuário interaja com esses dados através de um menu no terminal.

A aplicação foi construída utilizando uma arquitetura em camadas (View, Service, Repository) para garantir a organização, manutenibilidade e boas práticas de desenvolvimento.

-----

## ✨ Funcionalidades

O menu interativo da aplicação oferece as seguintes funcionalidades:

1.  **Buscar livro pelo título:** O usuário pode digitar o título de um livro. A aplicação primeiro verifica se o livro já existe no banco de dados local. Caso não exista, realiza uma busca na API Gutendex, salva o livro e seu autor no banco de dados e exibe as informações.
2.  **Listar livros registrados:** Exibe todos os livros que já foram buscados e salvos no banco de dados.
3.  **Listar autores registrados:** Mostra todos os autores salvos, com seus anos de nascimento e falecimento, e a lista de livros de sua autoria que estão registrados no banco.
4.  **Listar autores vivos em um determinado ano:** O usuário informa um ano e a aplicação exibe uma lista de autores que estavam vivos naquele período.
5.  **Listar livros em um determinado idioma:** Permite ao usuário filtrar e listar todos os livros registrados em um idioma específico (ex: `es`, `en`, `fr`, `pt`).

-----

## 🛠️ Tecnologias Utilizadas

As seguintes ferramentas e tecnologias foram utilizadas na construção do projeto:

* **Java 17:** Versão da linguagem Java utilizada.
* **Spring Boot:** Framework principal para a criação da aplicação.
* **Spring Data JPA:** Para a persistência de dados e comunicação com o banco de dados de forma simplificada.
* **PostgreSQL:** Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os livros e autores.
* **Maven:** Gerenciador de dependências e build do projeto.
* **Jackson:** Biblioteca para conversão de dados JSON (da API) para objetos Java.

-----

## 🚀 Como Executar o Projeto

Siga os passos abaixo para executar a aplicação em seu ambiente local.

### Pré-requisitos

* **Java JDK 17** ou superior.
* **Maven** instalado e configurado.
* **PostgreSQL** instalado e em execução.

### 1\. Configuração do Banco de Dados

Antes de executar a aplicação, você precisa criar um banco de dados no PostgreSQL e configurar as credenciais de acesso no arquivo `application.properties`.

1.  Crie um banco de dados no PostgreSQL. Exemplo: `CREATE DATABASE literalura_db;`
2.  Abra o arquivo `src/main/resources/application.properties` e atualize as seguintes propriedades com suas informações:

<!-- end list -->

```properties
# URL de conexão com o banco de dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db

# Usuário do banco de dados
spring.datasource.username=seu_usuario_aqui

# Senha do banco de dados
spring.datasource.password=sua_senha_aqui

# Configurações do Hibernate (JPA)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 2\. Execução da Aplicação

Com o banco de dados configurado, você pode iniciar a aplicação.

1.  Abra um terminal na pasta raiz do projeto.
2.  Execute o seguinte comando Maven:

<!-- end list -->

```bash
mvn spring-boot:run
```

3.  Após a inicialização, o menu interativo será exibido no console e você poderá começar a usar a aplicação.

-----

## 🏗️ Estrutura do Projeto

O projeto está organizado nos seguintes pacotes, seguindo as melhores práticas de uma arquitetura em camadas:

* `com.github.desafio_literalura.Desafio_LiterAlura`: Pacote raiz.
    * **model**: Contém as entidades JPA (`Livro`, `Autor`) e os DTOs/Records (`DadosLivro`, `DadosAutor`) que representam os dados da API.
    * **repository**: Interfaces do Spring Data JPA (`LivroRepository`, `AutorRepository`) responsáveis pela abstração do acesso aos dados.
    * **service**: Classes de serviço (`LivroService`, `AutorService`) que contêm a lógica de negócio da aplicação.
    * **util**: Classes utilitárias para tarefas como consumo da API (`ConsumoApi`) e conversão de objetos (`ConverterObjetoLivro`).
    * **view**: Classe responsável pela interface com o usuário no console (`Principal`).
    * `DesafioLiterAluraApplication.java`: Classe principal que inicia a aplicação Spring Boot.

-----

## 👨‍💻 Autor

**[Igo Marcelino]**

* **GitHub:** [Igo Marcelino](https://github.com/igomarcelino)
* **LinkedIn:** [Igo Marcelino](https://www.linkedin.com/in/igo-marcelino/)