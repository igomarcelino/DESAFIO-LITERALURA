package com.github.desafio_literalura.Desafio_LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_autor;

    private String nome;
    private int ano_nascimento;
    private int ano_falecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livro;

    public Autor() {
    }

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.autor();
        this.ano_nascimento = dadosAutor.ano_nascimento();
        this.ano_falecimento = dadosAutor.ano_falecimento();
    }

    public Autor(Autor autor) {
        this.nome = autor.getNome();
        this.ano_nascimento = autor.getAno_nascimento();
        this.ano_falecimento = autor.getAno_falecimento();
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }

    public Long getId_autor() {
        return id_autor;
    }

    public void setId_autor(Long id_autor) {
        this.id_autor = id_autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno_nascimento() {
        return ano_nascimento;
    }

    public void setAno_nascimento(int ano_nascimento) {
        this.ano_nascimento = ano_nascimento;
    }

    public int getAno_falecimento() {
        return ano_falecimento;
    }

    public void setAno_falecimento(int ano_falecimento) {
        this.ano_falecimento = ano_falecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return ano_nascimento == autor.ano_nascimento && ano_falecimento == autor.ano_falecimento && Objects.equals(id_autor, autor.id_autor) && Objects.equals(nome, autor.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_autor, nome, ano_nascimento, ano_falecimento);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id_autor=" + id_autor +
                ", nome='" + nome + '\'' +
                ", ano_nascimento=" + ano_nascimento +
                ", ano_falecimento=" + ano_falecimento +
                ", livro=" + livro +
                '}';
    }
}
