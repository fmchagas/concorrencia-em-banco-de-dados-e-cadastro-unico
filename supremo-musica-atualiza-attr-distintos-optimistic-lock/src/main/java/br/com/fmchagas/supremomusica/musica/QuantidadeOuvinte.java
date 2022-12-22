package br.com.fmchagas.supremomusica.musica;

import javax.persistence.*;

@Entity
public class QuantidadeOuvinte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantidade;

    @OneToOne(mappedBy = "quantidadeOuvinte")
    private Musica musica;

    @Version
    private int version;

    /**
     * @deprecated Uso exclusivo do hibernate
     * */
    @Deprecated(since = "1.0.0")
    public QuantidadeOuvinte(){ }

    public QuantidadeOuvinte(final Musica musica){
        quantidade = 0L;
        this.musica = musica;
    }

    public void aumentarOuvinte() {
        quantidade++;
    }
}