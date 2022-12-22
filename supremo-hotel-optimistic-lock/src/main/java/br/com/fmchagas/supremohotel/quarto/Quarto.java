package br.com.fmchagas.supremohotel.quarto;

import br.com.fmchagas.supremohotel.reserva.Reserva;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.time.Instant.now;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorDiaria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipoQuarto;

    @Column(nullable = false)
    private boolean reservado = false;

    @Column(nullable = false)
    private Instant criadoEm = now();

    @Column(nullable = false)
    private Instant atualizadoEm = now();

    @OneToMany(mappedBy = "quarto", cascade = {MERGE, PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    @Column(nullable = false)
    @Version
    private int versao;

    /**
     * @deprecated (not removal, Uso exclusivo para hibernate)
     */
    @Deprecated(since = "1.0.0", forRemoval = false)
    public Quarto(){}

    public Quarto(String descricao, BigDecimal valorDiaria, TipoQuarto tipoQuarto) {
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        this.tipoQuarto = tipoQuarto;
    }

    public boolean isReservado() {
        return reservado;
    }

    public boolean reservar(){
        if (isReservado()){
            return false;
        }

        reservado = true;
        atualizadoEm = now();
        return true;
    }

    public void adicionar(Reserva reserva){
        reservas.add(reserva);
    }

    public Long getId() {
        return id;
    }
}