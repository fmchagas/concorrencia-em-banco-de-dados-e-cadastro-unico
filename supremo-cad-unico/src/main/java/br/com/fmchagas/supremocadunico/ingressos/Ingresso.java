package br.com.fmchagas.supremocadunico.ingressos;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_ingresso_numero_data_vento", columnNames = {"numero", "dataEvento"})
})
@Entity
public class Ingresso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeCliente;
    @Column(nullable = false, length = 11)
    private String cpfCliente;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    @Future
    private LocalDateTime dataEvento;

    /**
     * @deprecated uso exclusivo ORM-hibernate
     */
    @Deprecated(since = "1.0.0")
    public Ingresso() { }

    public Ingresso(String nomeCliente, String cpfCliente, String numero, LocalDateTime dataEvento) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.numero = numero;
        this.dataEvento = dataEvento;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }
}