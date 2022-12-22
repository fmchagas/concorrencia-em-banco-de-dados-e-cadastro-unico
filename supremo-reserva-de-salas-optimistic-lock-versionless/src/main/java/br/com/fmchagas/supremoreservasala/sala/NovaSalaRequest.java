package br.com.fmchagas.supremoreservasala.sala;

import javax.validation.constraints.NotBlank;

public class NovaSalaRequest {
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sala toModel(){
        return new Sala(this.nome);
    }
}
