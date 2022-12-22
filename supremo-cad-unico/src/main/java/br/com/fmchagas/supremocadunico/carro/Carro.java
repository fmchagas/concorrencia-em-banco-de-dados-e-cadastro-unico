package br.com.fmchagas.supremocadunico.carro;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_carro_placa", columnNames = {"placa"}),
        @UniqueConstraint(name = "UK_carro_chassi", columnNames = {"chassi"})
})
public class Carro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false, length = 4)
    private Integer ano;
    @Column(nullable = false, length = 10)
    private String placa;
    @Column(nullable = false, length = 17)
    private String chassi;


    /**
     * @deprecated uso exclusivo do ORM-hibernate
     * */
    @Deprecated(since = "1.0.0")
    public Carro(){ }
    public Carro(String marca, String modelo, Integer ano, String placa, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    public Long getId() {
        return id;
    }
}