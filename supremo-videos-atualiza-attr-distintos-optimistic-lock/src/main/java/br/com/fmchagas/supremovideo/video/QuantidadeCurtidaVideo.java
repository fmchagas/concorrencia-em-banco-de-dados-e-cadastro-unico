package br.com.fmchagas.supremovideo.video;

import javax.persistence.*;

@Entity
public class QuantidadeCurtidaVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidadeCurtida;

    @OneToOne(mappedBy = "quantidadeDescurtidaVideo")
    private Video video;

    @Version
    private int version;

    /**
     * @deprecated Uso exclusivo do hibernate
     */
    @Deprecated(since = "1.0.0")
    public QuantidadeCurtidaVideo() {}

    public QuantidadeCurtidaVideo(final Video video) {
        this.video = video;
        quantidadeCurtida = 0;
    }

    public void incrementaGostar(){
        quantidadeCurtida++;
    }
}