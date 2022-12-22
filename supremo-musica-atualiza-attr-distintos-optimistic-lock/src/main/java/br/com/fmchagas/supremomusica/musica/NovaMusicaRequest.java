package br.com.fmchagas.supremomusica.musica;

import javax.validation.constraints.NotBlank;

public class NovaMusicaRequest {
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Musica toModel() {
        return new Musica(nome);
    }
}
