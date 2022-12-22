package br.com.fmchagas.supremoalugacar.carro;

import br.com.fmchagas.supremoalugacar.reserva.Reserva;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private boolean disponivel;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "carro", orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    public Carro(String modelo, Integer ano, String marca) {
        this.modelo = modelo;
        this.ano = ano;
        this.marca = marca;
        this.disponivel=true;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated(since="1.0.0", forRemoval=false)
    public Carro() {
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean isIndiponivel(){
        return !isDisponivel();
    }

    public boolean reservar(Reserva reserva){
        if (isIndiponivel()){
            return false;
        }
        disponivel = false;
        reservas.add(reserva);
        return true;
    }

    public Long getId() {
        return id;
    }
}