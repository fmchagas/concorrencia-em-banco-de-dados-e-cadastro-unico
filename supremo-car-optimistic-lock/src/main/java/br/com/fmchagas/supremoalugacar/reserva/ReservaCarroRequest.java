package br.com.fmchagas.supremoalugacar.reserva;

import br.com.fmchagas.supremoalugacar.carro.Carro;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ReservaCarroRequest {
    @Size(max = 128)
    @NotBlank
    @JsonProperty("reservado_para")
    private String reservadoPara;

    @Future
    @JsonProperty("check_in")
    private LocalDateTime checkIn;

    @Future
    @JsonProperty("check_out")
    private LocalDateTime checkOut;

    public ReservaCarroRequest(String reservadoPara, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva toModel(Carro carro) {
        return new Reserva(carro, this.reservadoPara, this.checkIn, this.checkOut);
    }
}
