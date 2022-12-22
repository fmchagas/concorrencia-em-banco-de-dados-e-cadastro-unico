package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Sorteio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String descricaoPremio;
    @Column(nullable = false)
    @Future
    private LocalDate sorteioEm;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    /**
     * @deprecated Uso exclusivo ORM
     * */
    @Deprecated(since = "1.0.0")
    public Sorteio(){ }

    public Sorteio(String descricaoPremio, LocalDate sorteioEm){
        this.descricaoPremio = descricaoPremio;
        this.sorteioEm = sorteioEm;
    }

    public Long getId() { return id; }
}