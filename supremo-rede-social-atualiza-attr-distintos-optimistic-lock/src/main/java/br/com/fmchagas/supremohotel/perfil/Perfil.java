package br.com.fmchagas.supremohotel.perfil;


import javax.persistence.*;

@Entity
public class Perfil {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String apelido;
    private String urlImagem;

    /*private int quantidadeDeFan;*/ // old
    @OneToOne(optional = false, orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE})
    @JoinColumn(name = "quantidade_fan_id")
    private QuantidadeDeFan quantidadeDeFan;

    @Version
    private int versao;

    /**
     * @deprecated Uso exclusivo do hibernate
     **/
    @Deprecated(since = "1.0.0")
    public Perfil(){ }

    public Perfil(String nome, String apelido, String urlImagem) {
        this.nome = nome;
        this.apelido = apelido;
        this.urlImagem = urlImagem;
        quantidadeDeFan = new QuantidadeDeFan(this);
    }

    public Long getId() {
        return id;
    }

    public void incrementaFan() {
        // this.quantidadeDeFan ++; // old
        quantidadeDeFan.incrementa();
    }

    public void atualiza(String nome) {
        this.nome = nome;
    }
}
