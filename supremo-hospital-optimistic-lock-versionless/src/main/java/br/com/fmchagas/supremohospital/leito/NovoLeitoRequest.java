package br.com.fmchagas.supremohospital.leito;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovoLeitoRequest {
    @NotBlank
    private String titulo;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoLeitoRequest(String titulo) {
        this.titulo = titulo;
    }


    public Leito toModel() {
        return new Leito(titulo);
    }
}
