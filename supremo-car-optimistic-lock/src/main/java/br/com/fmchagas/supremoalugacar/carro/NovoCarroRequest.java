package br.com.fmchagas.supremoalugacar.carro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoCarroRequest {
    @Size(max = 128)
    @NotBlank
    private final String modelo;

    private final Integer ano;

    @NotBlank
    private final String marca;

    public NovoCarroRequest(String modelo, Integer ano, String marca) {
        this.modelo = modelo;
        this.ano = ano;
        this.marca = marca;
    }

    public Carro toModel() {
        return new Carro(this.modelo, this.ano, this.marca);
    }
}
