package br.com.fmchagas.supremocadunico.dado_sensivel.pacote_dado;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "pacote_dado",
        uniqueConstraints = {@UniqueConstraint(name = "UK_pacote_dado_hash_cpf", columnNames = "hashDoCpf")}
)
public class PacoteDeDado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 64)
    private String hashDoCpf;
    @Column(nullable = false, length = 19)
    private String telefone;
    @Column(nullable = false, length = 10)
    private Integer quantidadeDeDado;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    /**
     * @deprecated Uso exclusivo ORM
     */
    @Deprecated(since = "1.0.0")
    public PacoteDeDado() { }

    public PacoteDeDado(String nome, String cpf, String telefone, Integer quantidadeDeDado) {
        this.nome = nome;
        this.cpf = CPFUtils.anonymize(cpf);
        this.telefone = telefone;
        this.quantidadeDeDado = quantidadeDeDado;
        hashDoCpf = CPFUtils.hash(cpf);
    }

    public Long getId() {
        return id;
    }

    public void adiciona(final Integer quantidadeDeDado){
        this.quantidadeDeDado += quantidadeDeDado;
    }
}