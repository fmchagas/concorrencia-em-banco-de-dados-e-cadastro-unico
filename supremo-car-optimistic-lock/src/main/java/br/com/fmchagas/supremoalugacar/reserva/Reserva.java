package br.com.fmchagas.supremoalugacar.reserva;

import br.com.fmchagas.supremoalugacar.carro.Carro;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "carro_id")
    private Carro carro;

    @Column(nullable = false, length = 128)
    private String reservadoPara;

    @Column
    private LocalDateTime checkIn;

    @Column
    private LocalDateTime checkOut;

    @Column(nullable = false)
    private LocalDateTime reservadoEm = now();

    /**
     * @deprecated Uso exclusivo para hibernate
     */
    @Deprecated(since = "1.0.0")
    public Reserva(){}


    public Reserva(Carro carro, String reservadoPara, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.carro = carro;
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.carro.reservar(this);
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", reservadoPara='" + reservadoPara + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", reservadoEm=" + reservadoEm +
                '}';
    }
}