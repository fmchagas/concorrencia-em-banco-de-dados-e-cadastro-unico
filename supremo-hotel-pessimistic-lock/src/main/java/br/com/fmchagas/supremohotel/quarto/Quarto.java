package br.com.fmchagas.supremohotel.quarto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

import static java.time.Instant.now;

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

    /**
     * @Deprecated Uso exclusivo para hibernate
     */
    @Deprecated
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

    public Long getId() {
        return id;
    }
}
