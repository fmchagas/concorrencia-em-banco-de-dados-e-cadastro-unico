package br.com.fmchagas.supremomusica.musica;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    /*@Column(nullable = false)
    private Integer quantidadeOuvinte;*/
    @OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})
    private QuantidadeOuvinte quantidadeOuvinte;

    /*@Column(nullable = false)
    private Integer quantidadeLike;*/
    @OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})
    private QuantidadeLike quantidadeLike;

    @Column(nullable = false)
    private LocalDateTime criadoEm = now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = now();

    @Version
    private int version;


    public Musica(String nome) {
        this.nome = nome;
        quantidadeOuvinte = new QuantidadeOuvinte(this);
        quantidadeLike = new QuantidadeLike(this);
    }

    /**
     * @deprecated construtor de uso exclusivo
     */
    @Deprecated(since = "1.0.0")
    public Musica() { }


    public void aumentarOuvinte() { quantidadeOuvinte.aumentarOuvinte(); }

    public void aumentarLikes() { quantidadeLike.aumentarLikes(); }

    public Long getId() {
        return id;
    }
}