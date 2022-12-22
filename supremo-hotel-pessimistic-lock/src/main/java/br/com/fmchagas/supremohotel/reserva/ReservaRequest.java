package br.com.fmchagas.supremohotel.reserva;

import br.com.fmchagas.supremohotel.quarto.Quarto;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

// https://gist.github.com/jordisilvazup/2c8a83826913555177b77bd6cb5fd1e4
public class ReservaRequest {
    @NotBlank
    @JsonProperty("reservado_para")
    private String reservadoPara;

    @Future
    @JsonProperty("check_in")
    private LocalDateTime checkIn;

    @Future
    @JsonProperty("check_out")
    private LocalDateTime checkOut;

    public ReservaRequest(String reservadoPara, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva toModel(Quarto quarto) {
        return new Reserva(quarto, this.reservadoPara, this.checkIn, this.checkOut);
    }
}
