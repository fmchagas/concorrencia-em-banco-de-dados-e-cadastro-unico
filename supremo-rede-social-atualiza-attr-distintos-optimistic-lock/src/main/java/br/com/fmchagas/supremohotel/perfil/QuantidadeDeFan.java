package br.com.fmchagas.supremohotel.perfil;

import javax.persistence.*;

@Entity
public class QuantidadeDeFan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long quantidade;

    @OneToOne(mappedBy = "quantidadeDeFan")
    private Perfil perfil;

    @Version
    private int versao;

    /**
     * @deprecated Uso exclusivo hibernate
     * **/
    @Deprecated(since = "1.0.0")
    public QuantidadeDeFan(){ }

    public QuantidadeDeFan(Perfil perfil) {
        this.perfil = perfil;
        quantidade = 0;
    }

    public void incrementa() {
        quantidade ++;
    }
}
