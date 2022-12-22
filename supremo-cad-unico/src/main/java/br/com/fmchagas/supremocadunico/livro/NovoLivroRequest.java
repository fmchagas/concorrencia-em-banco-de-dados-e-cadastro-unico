package br.com.fmchagas.supremocadunico.livro;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class NovoLivroRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String isbn;

    @NotNull @Past
    @JsonProperty("publicado-em")
    private LocalDate publicadoEm;

    public NovoLivroRequest(String titulo, String isbn, LocalDate publicadoEm) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.publicadoEm = publicadoEm;
    }

    public Livro toModel() {
        return new Livro(titulo, isbn, publicadoEm);
    }

    public String getIsbn() {
        return isbn;
    }
}