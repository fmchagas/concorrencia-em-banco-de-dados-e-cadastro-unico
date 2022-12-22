package br.com.fmchagas.supremoapp.app;

import javax.persistence.*;
import java.time.Instant;

import static java.time.Instant.*;

@Entity
public class Aplicativo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String linkArmazenamento;

    @OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    private QuantidadeDownload quantidadeDownload;

    @OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    private QuantidadeLike quantidadeLike;

    @Column(nullable = false)
    private Instant criadoEm = now();

    @Column(nullable = false)
    private Instant atualizadoEm = now();

    @Version
    private int version;

    /**
     * @deprecated Uso exclusivo do hibernate
     * */
    @Deprecated(since = "1.0.0")
    public Aplicativo() { }

    public Aplicativo(String nome, String descricao, String linkArmazenamento) {
        this.nome = nome;
        this.descricao = descricao;
        this.linkArmazenamento = linkArmazenamento;
        quantidadeDownload = new QuantidadeDownload(this);
        quantidadeLike = new QuantidadeLike(this);
    }

    public Long getId() {
        return id;
    }

    public void aumentarDownload() {
        quantidadeDownload.aumentar();
    }

    public void aumentarLike() {
        quantidadeLike.aumentar();
    }

    public String getLinkArmazenamento() {
        return linkArmazenamento;
    }
}