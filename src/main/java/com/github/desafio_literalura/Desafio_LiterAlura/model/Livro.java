package com.github.desafio_literalura.Desafio_LiterAlura.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_livro;

    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Autor autor;

    private String idioma;
    private int totalDownloads;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro) {
        this.title = dadosLivro.titulo();
        this.autor = new Autor(dadosLivro.autor().get(0));
        this.idioma = dadosLivro.idioma().get(0);
        this.totalDownloads = dadosLivro.totalDownloads();
    }

    public Livro(Livro livro, Autor autor) {
        this.title = livro.getTitle();
        this.autor = autor;
        this.idioma = livro.getIdioma();
        this.totalDownloads = livro.getTotalDownloads();
    }

    public Long getId_livro() {
        return id_livro;
    }

    public void setId_livro(Long id_livro) {
        this.id_livro = id_livro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(int totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return totalDownloads == livro.totalDownloads && Objects.equals(id_livro, livro.id_livro) && Objects.equals(autor, livro.autor) && Objects.equals(idioma, livro.idioma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_livro, autor, idioma, totalDownloads);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id_livro=" + id_livro +
                ", autor=" + autor +
                ", idioma='" + idioma + '\'' +
                ", totalDownloads=" + totalDownloads +
                '}';
    }
}
