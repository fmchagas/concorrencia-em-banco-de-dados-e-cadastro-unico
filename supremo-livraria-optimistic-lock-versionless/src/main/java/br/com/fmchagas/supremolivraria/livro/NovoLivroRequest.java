package br.com.fmchagas.supremolivraria.livro;

import javax.validation.constraints.NotBlank;

public class NovoLivroRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String resumo;

    @NotBlank
    private String autor;

    @NotBlank
    @org.hibernate.validator.constraints.ISBN
    private String ISBN;

    public NovoLivroRequest(String nome, String resumo, String autor, String ISBN) {
        this.nome = nome;
        this.resumo = resumo;
        this.autor = autor;
        this.ISBN = ISBN;
    }

    public Livro toModel(){
        return new Livro(this.nome, this.resumo, this.autor, this.ISBN);
    }

    public String getNome() {
        return nome;
    }

    public String getResumo() {
        return resumo;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return ISBN;
    }
}
