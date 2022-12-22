package br.com.fmchagas.supremocadunico.livro;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private LocalDate publicadoEm;

    @Column(nullable = false, updatable = false)
    private Instant criadoEm = Instant.now();

    @Column(nullable = false)
    private Instant alteradoEm = Instant.now();

    /**
     * @deprecated uso exclusivo do ORM-hibernate
     * */
    @Deprecated(since = "1.0.0")
    public Livro() { }

    public Livro(String titulo, String isbn, LocalDate publicadoEm) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.publicadoEm = publicadoEm;
    }
}