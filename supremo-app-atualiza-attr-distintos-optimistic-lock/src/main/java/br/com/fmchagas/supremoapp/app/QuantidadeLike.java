package br.com.fmchagas.supremoapp.app;

import javax.persistence.*;

@Entity
public class QuantidadeLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantidade;

    @OneToOne(mappedBy = "quantidadeLike")
    private Aplicativo aplicativo;

    @Version
    private int version;

    /**
     * @deprecated Uso exclusivo do hibernate
     * */
    @Deprecated(since = "1.0.0")
    public QuantidadeLike(){ }

    public QuantidadeLike(final Aplicativo aplicativo){
        this.aplicativo = aplicativo;
        quantidade = 0L;
    }

    public void aumentar() { quantidade++; }
}