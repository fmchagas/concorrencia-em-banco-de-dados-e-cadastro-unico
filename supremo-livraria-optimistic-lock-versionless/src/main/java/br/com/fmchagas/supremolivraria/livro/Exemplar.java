package br.com.fmchagas.supremolivraria.livro;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.ALL)
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    @Column(nullable = false)
    private boolean reservado = false;

    public Exemplar(Livro livro) {
        this.livro = livro;
    }

    /**
     * @deprecated construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Exemplar() {
    }

    public void reservar() {
        this.reservado = true;
    }

    public boolean isReservado() {
        return reservado;
    }

    public Long getId() {
        return id;
    }
}