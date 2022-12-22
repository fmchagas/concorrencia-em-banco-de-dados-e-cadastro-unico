package br.com.fmchagas.supremocadunico.contato;

import javax.persistence.*;
import java.io.Serializable;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_departamento_sigla", columnNames = "sigla")
})
@Entity
public class Departamento implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 3)
    private String sigla;

    /**
     * @deprecated Uso exclusivo ORM
     * */
    @Deprecated(since = "1.0.0")
    public Departamento(){ }

    public Departamento(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla.toUpperCase();
    }

    public Long getId() {
        return id;
    }
}
