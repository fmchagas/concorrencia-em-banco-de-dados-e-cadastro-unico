package br.com.fmchagas.supremobar.reserva;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ReservaMesaRequest {
    @NotBlank
    @JsonProperty("reservado_para")
    private String reservadoPara;

    public String getReservadoPara() {
        return reservadoPara;
    }

    public void setReservadoPara(String nome) {
        this.reservadoPara = nome;
    }
}
