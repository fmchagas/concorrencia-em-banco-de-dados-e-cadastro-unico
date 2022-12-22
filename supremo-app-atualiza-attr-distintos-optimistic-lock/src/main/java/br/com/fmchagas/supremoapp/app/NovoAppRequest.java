package br.com.fmchagas.supremoapp.app;

import javax.validation.constraints.NotBlank;

public class NovoAppRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private String linkArmazenamento;

    public NovoAppRequest(String nome, String descricao, String linkArmazenamento) {
        this.nome = nome;
        this.descricao = descricao;
        this.linkArmazenamento = linkArmazenamento;
    }

    public Aplicativo toModel(){
        return new Aplicativo(nome, descricao, linkArmazenamento);
    }
}
