package br.com.fmchagas.supremomusica.musica;

import javax.persistence.*;

@Entity
public class QuantidadeLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantidade;

    @OneToOne(mappedBy = "quantidadeLike")
    private Musica musica;

    @Version
    private int version;

    /**
     * @deprecated Uso exclusivo do hibernate
     * */
    @Deprecated(since = "1.0.0")
    public QuantidadeLike(){ }

    public QuantidadeLike(final Musica musica){
        this.musica = musica;
        quantidade = 0L;
    }

    public void aumentarLikes() { quantidade++; }
}