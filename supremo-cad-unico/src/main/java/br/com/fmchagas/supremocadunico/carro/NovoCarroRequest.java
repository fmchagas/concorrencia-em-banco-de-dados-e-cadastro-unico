package br.com.fmchagas.supremocadunico.carro;

import javax.validation.constraints.*;

public class NovoCarroRequest {

    @NotBlank
    public String marca;
    @NotBlank
    public String modelo;
    @Min(2010) @Max(2099)
    public Integer ano;
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}", message = "deve ter formato do mercosul")
    public String placa;
    @NotBlank
    @Size(min = 17, max = 17)
    public String chassi;

    public NovoCarroRequest(String marca, String modelo, Integer ano, String placa, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    public Carro toModel() {
        return new Carro(marca, modelo, ano, placa, chassi);
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public String getChassi() {
        return chassi;
    }
}