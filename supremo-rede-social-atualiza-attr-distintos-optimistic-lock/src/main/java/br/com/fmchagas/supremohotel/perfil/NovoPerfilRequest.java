package br.com.fmchagas.supremohotel.perfil;

import javax.validation.constraints.NotBlank;

public class NovoPerfilRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String apelido;

    @NotBlank
    private String urlImagem;


    public Perfil toModel(){
        return new Perfil(nome, apelido, urlImagem);
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
}