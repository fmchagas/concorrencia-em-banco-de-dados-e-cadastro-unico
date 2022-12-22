package br.com.fmchagas.supremobar.mesa;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.fmchagas.supremobar.mesa.StatusOcupacao.LIVRE;
import static br.com.fmchagas.supremobar.mesa.StatusOcupacao.OCUPADO;
import static java.time.LocalDateTime.now;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOcupacao status = LIVRE;

    @Column
    private String reservadoPara;

    @Column(nullable = false)
    private LocalDateTime criadoEm = now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = now();

    @Column(nullable = false)
    private Integer capacidade;

    public Mesa(Integer capacidade){
        this.capacidade=capacidade;
    }

    public void reservar(final String reservarPara) throws MesaReservadaException{
        if (isReservado()){
            throw new MesaReservadaException("Mesa reservada");
        }

        this.reservadoPara = reservarPara;
        this.status = OCUPADO;
        this.atualizadoEm = now();
    }

    public boolean isReservado(){
        return this.status == OCUPADO;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public Mesa() {
    }

    public Long getId() {
        return id;
    }
}