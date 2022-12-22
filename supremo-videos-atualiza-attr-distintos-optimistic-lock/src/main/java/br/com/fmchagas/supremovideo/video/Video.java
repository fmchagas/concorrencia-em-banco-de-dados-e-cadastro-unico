package br.com.fmchagas.supremovideo.video;

import javax.persistence.*;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private Integer visualizacoes;

    /*@Column(nullable = false)
    private Integer gostei;*/ // old gostei
    @OneToOne(optional = false, orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE})
    private QuantidadeCurtidaVideo quantidadeCurtidaVideo; //joinha para cima

    /*@Column(nullable = false)
    private Integer naoGostei;*/ // old nao gostei
    @OneToOne(optional = false, orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE})
    private QuantidadeDescurtidaVideo quantidadeDescurtidaVideo; //joinha para baixo

    @Version
    private int version;

    public Video(String titulo, String descricao, String link) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        this.visualizacoes = 0;
        this.quantidadeCurtidaVideo = new QuantidadeCurtidaVideo(this);
        this.quantidadeDescurtidaVideo = new QuantidadeDescurtidaVideo(this);
    }

    /**
     * @deprecated Uso exclusivo do hibernate
     */
    @Deprecated(since = "1.0.0")
    public Video() { }

    public void incrementaVisualizacao(){
        visualizacoes++;
    }

    public void incrementaGostar(){
        quantidadeCurtidaVideo.incrementaGostar();
    }

    public void incrementaNaoGostar(){
        quantidadeDescurtidaVideo.incrementaNaoGostar();
    }

    public Long getId() {
        return id;
    }
}