package br.com.fmchagas.supremohotel.reserva;

import br.com.fmchagas.supremohotel.quarto.Quarto;
import br.com.fmchagas.supremohotel.quarto.QuartoReservadoException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReservaRequest {
    @NotBlank
    @JsonProperty("reservado_para")
    private final String reservadoPara;

    @Future
    @JsonProperty("check_in")
    private final LocalDateTime checkIn;

    @Future
    @JsonProperty("check_out")
    private final LocalDateTime checkOut;

    public ReservaRequest(String reservadoPara, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva toModel(Quarto quarto) {
        Assert.notNull(quarto, "Quarto não pode ser nulo para fazermos a reserva");

        if(quarto.isReservado()){
            throw new QuartoReservadoException("Quarto reservado");
        }

        quarto.reservar();
        return new Reserva(quarto, reservadoPara, checkIn, checkOut);
    }
}
