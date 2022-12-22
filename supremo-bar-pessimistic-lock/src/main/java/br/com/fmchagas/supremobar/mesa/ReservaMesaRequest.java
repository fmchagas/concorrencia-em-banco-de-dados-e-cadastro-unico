package br.com.fmchagas.supremobar.mesa;

import javax.validation.constraints.NotBlank;

public class ReservaMesaRequest {
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
