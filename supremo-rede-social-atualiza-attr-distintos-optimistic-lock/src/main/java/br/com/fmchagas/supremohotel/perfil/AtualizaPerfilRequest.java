package br.com.fmchagas.supremohotel.perfil;

import javax.validation.constraints.NotBlank;

public class AtualizaPerfilRequest {
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}