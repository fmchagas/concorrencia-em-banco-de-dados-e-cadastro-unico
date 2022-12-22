package br.com.fmchagas.supremovideo.video;

import javax.validation.constraints.NotBlank;

public class NovoVideoRequest {
    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String link;


    public Video toModel(){
        return new Video(titulo, descricao, link);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}