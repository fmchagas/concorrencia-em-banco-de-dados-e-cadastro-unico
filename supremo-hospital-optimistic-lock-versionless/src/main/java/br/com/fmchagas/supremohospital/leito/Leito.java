package br.com.fmchagas.supremohospital.leito;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.ALL)
public class Leito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOcupacao status;

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    public Leito(String titulo) {
        this.titulo = titulo;
        this.status = StatusOcupacao.LIVRE;
    }

    /**
     * @deprecated Uso exclusivo para o Hibernate
     */
    @Deprecated(since = "1.1.0")
    public Leito() { }

    public Long getId() {
        return id;
    }

    public boolean isIndisponivel(){
        return !isLivre();
    }

    public boolean isLivre(){
        return StatusOcupacao.LIVRE.equals(this.status);
    }

    public void reservar() {
        status = StatusOcupacao.OCUPADO;
        atualizadoEm = LocalDateTime.now();
    }
}