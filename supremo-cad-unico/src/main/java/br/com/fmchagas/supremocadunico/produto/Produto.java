package br.com.fmchagas.supremocadunico.produto;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false, unique = true, length = 6)
    private String codigo;

    @Column(nullable = false, updatable = false)
    private Instant criadoEm = Instant.now();

    @Column(nullable = false)
    private Instant alteradoEm = Instant.now();

    /**
     * @deprecated uso exclusivo do ORM-hibernate
     * */
    @Deprecated(since = "1.0.0")
    public Produto(){ }

    public Produto(@NotBlank String nome, @NotBlank BigDecimal valor, @NotBlank @Max(6) String codigo) {
        this.nome = nome;
        this.valor = valor;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }
}