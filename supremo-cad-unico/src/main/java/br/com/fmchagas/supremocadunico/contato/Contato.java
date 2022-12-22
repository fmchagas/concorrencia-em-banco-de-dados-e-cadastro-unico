package br.com.fmchagas.supremocadunico.contato;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_contato_telefone_departamento_id", columnNames = {"telefone", "departamento_id"})
})
@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String funcionarioReponsavel;
    @Past
    @Column(nullable = false)
    private LocalDate cadastradoEm;
    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();
    @ManyToOne(optional = false)
    private Departamento departamento; //relation departamento_id

    /**
     * @deprecated Uso exclusivo do ORM
     * */
    @Deprecated(since = "1.0.0")
    public Contato(){ }

    public Contato(String telefone, String funcionarioReponsavel, LocalDate cadastradoEm, Departamento departamento) {
        this.telefone = telefone;
        this.funcionarioReponsavel = funcionarioReponsavel;
        this.cadastradoEm = cadastradoEm;
        this.departamento = departamento;
    }

    public Long getId() { return id; }
}