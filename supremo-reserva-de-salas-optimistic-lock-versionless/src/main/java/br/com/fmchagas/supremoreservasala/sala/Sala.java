package br.com.fmchagas.supremoreservasala.sala;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.fmchagas.supremoreservasala.sala.StatusOcupacao.LIVRE;
import static br.com.fmchagas.supremoreservasala.sala.StatusOcupacao.OCUPADO;

@Entity
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.DIRTY)
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOcupacao status = LIVRE;

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();


    public Sala(String nome) {
        this.nome = nome;
    }


    /**
     * @deprecated Uso exclusivo para hibernate
     */
    @Deprecated(since = "1.0.0")
    public Sala() { }

    public boolean isReservado(){
        return OCUPADO.equals(this.status);
    }

    public boolean reservar(){
        if(isReservado()) { return false; }

        status = OCUPADO;
        atualizadoEm = LocalDateTime.now();
        return true;
    }

    public Long getId() {
        return id;
    }
}