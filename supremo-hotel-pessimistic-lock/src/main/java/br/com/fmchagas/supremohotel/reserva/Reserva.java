package br.com.fmchagas.supremohotel.reserva;

import br.com.fmchagas.supremohotel.quarto.Quarto;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.Instant.now;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Column(nullable = false, length = 64)
    private String reservadoPara;

    @Column
    private LocalDateTime checkIn;

    @Column
    private LocalDateTime checkOut;

    @Column(nullable = false)
    private Instant reservadoEm = now();

    /**
     * @Deprecated Uso exclusivo para hibernate
     */
    @Deprecated
    public Reserva(){}


    public Reserva(Quarto quarto, String reservadoPara, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.quarto = quarto;
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public boolean reservar(){
        return this.quarto.reservar();
    }
    public Long getId() {
        return this.id;
    }
}
