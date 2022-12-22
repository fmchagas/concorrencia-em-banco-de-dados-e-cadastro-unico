package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_bilhete_hash_celular_sorteio_id_numero_sorte", columnNames = {"hashCelular", "sorteio_id", "numeroSorte"})
})
public class Bilhete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, length = 20)
    private String celular;
    @Column(nullable = false, length = 64)
    private String hashCelular;
    @Column(nullable = false)
    private byte[] hashBinaryCelular;
    @Column(nullable = false) @Min(1) @Max(9999)
    private Integer numeroSorte;
    @ManyToOne(optional = false)
    private Sorteio sorteio; //sorteio_id

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    /**
     * @deprecated Uso exclusivo ORM
     * */
    @Deprecated(since = "1.0.0")
    public Bilhete(){ }
    public Bilhete(String nome, String celular, Integer numeroSorte, Sorteio sorteio) {
        this.nome = nome;
        this.celular = anonymize(celular);
        hashCelular = toHex(hash(celular));
        hashBinaryCelular = hash(celular);
        this.numeroSorte = numeroSorte;
        this.sorteio = sorteio;
    }

    private String anonymize(final String celular){
        String regex = "(\\+[1-9]\\d{6})(\\d{1,14})(\\d{2})";
        return celular.replaceAll(regex, "$1***$3");
    }

    private static byte[] hash(String celular) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            return digest.digest(celular.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash: " + celular);
        }
    }

    public static String toHex(byte[] bytes) {
        System.out.println();
        StringBuilder result = new StringBuilder();
        System.out.print("{");
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
            System.out.print(aByte +",");
        }
        System.out.println("}");
        return result.toString();
    }

    public Long getId() {
        return id;
    }

    public String getHashCelular() {
        return hashCelular;
    }

    public byte[] getHashBinaryCelular() {
        return hashBinaryCelular;
    }
}