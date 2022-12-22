package br.com.fmchagas.supremovideo.video;

import javax.persistence.*;

@Entity
public class QuantidadeDescurtidaVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidadeDescurtida;

    @OneToOne(mappedBy = "quantidadeDescurtidaVideo")
    private Video video;

    @Version
    private int version;

    /**
     * @deprecated Uso exclusivo do hibernate
     */
    @Deprecated(since = "1.0.0")
    public QuantidadeDescurtidaVideo() {}

    public QuantidadeDescurtidaVideo(final Video video) {
        this.video = video;
        quantidadeDescurtida = 0;
    }

    public void incrementaNaoGostar(){
        quantidadeDescurtida++;
    }
}