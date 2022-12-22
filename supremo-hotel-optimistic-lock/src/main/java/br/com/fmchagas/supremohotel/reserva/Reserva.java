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
    @JoinColumn(name = "quarto_id", nullable = false)
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
     * @deprecated (not removal, Uso exclusivo para hibernate)
     */
    @Deprecated(since = "1.0.0", forRemoval = false)
    public Reserva(){}


    public Reserva(Quarto quarto, String reservadoPara, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.quarto = quarto;
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.quarto.adicionar(this);
    }
    public Long getId() {
        return this.id;
    }
}