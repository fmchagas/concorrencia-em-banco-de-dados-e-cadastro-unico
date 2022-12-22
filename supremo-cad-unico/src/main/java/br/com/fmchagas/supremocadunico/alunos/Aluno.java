package br.com.fmchagas.supremocadunico.alunos;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_aluno_matricula_data_matricula", columnNames = {"matricula", "dataMatricula"})
})
@Entity
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false, length = 6)
    private String matricula;
    @Column(nullable = false)
    private LocalDate dataMatricula;

    /**
     * @deprecated uso exclusivo ORM-hibernate
     */
    @Deprecated(since = "1.0.0")
    public Aluno() { }

    public Aluno(@NotBlank String nome, @CPF String cpf, @Size(min = 1, max = 6) String matricula, @PastOrPresent @NotNull LocalDate dataMatricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.dataMatricula = dataMatricula;
    }

    public Long getId() {
        return id;
    }
}