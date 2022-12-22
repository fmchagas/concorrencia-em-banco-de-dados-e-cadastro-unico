package br.com.fmchagas.supremolivraria.livro;

import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.constraints.ISBN.Type.ANY;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String resumo;


    @Column(nullable = false)
    private String autor;

    /*@OneToMany(mappedBy = "livro")
    private List<Exemplar> exemplares =  new ArrayList<>();*/

    @Column(nullable = false)
    @ISBN(type = ANY)
    private String ISBN;


    public Livro(String nome, String resumo, String autor, String ISBN) {
        this.nome = nome;
        this.resumo = resumo;
        this.autor = autor;
        this.ISBN = ISBN;
    }

    /**
     * @deprecated Uso exclusivo do Hibernate
     */
    @Deprecated(since = "1.0.0")
    public Livro() {
    }

    public Long getId() {
        return id;
    }
}
