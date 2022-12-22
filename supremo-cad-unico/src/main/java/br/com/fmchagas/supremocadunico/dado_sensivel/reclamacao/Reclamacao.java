package br.com.fmchagas.supremocadunico.dado_sensivel.reclamacao;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

//https://gist.github.com/rafaelpontezup/374ba168270de522699fd7050ff8a9cc
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_reclamacao_hash_celular_hash_reclamacao", columnNames = {"hash_celular", "hash_reclamacao"})
})
@Entity
public class Reclamacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 128)
    private String nome;
    @Column(nullable = false, length = 128)
    private String email;
    @Column(nullable = false, length = 20)
    private String celular; //anonymize
    @Column(name = "hash_celular", nullable = false, length = 64)
    private String hashDoCelular;
    @Column(nullable = false, length = 4000)
    private String reclamacao;

    @Column(name = "hash_reclamacao", nullable = false, length = 64)
    private String hashDaReclamacao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    /**
     * @deprecated uso exclusivo ORM
     */
    @Deprecated(since = "1.0.0")
    public Reclamacao(){ }

    public Reclamacao(String nome, String email, String celular, String reclamacao) {
        this.nome = nome;
        this.email = email;
        this.celular = anonymize(celular);
        this.reclamacao = reclamacao;
        hashDoCelular = hash(celular);
        hashDaReclamacao = hash(reclamacao);
    }

    private String anonymize(final String celular){
        String regex = "(\\+[1-9]\\d{6})(\\d{1,14})(\\d{2})";
        String masked = celular.replaceAll(regex, "$1***$3"); // masked: +5564987***21
        return masked;
    }

    private static String hash(String cpf) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hash = digest.digest(cpf.getBytes(StandardCharsets.UTF_8));
            return toHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash de um CPF: " + cpf);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    public Long getId() {
        return id;
    }

    public String getHashDoCelular() {
        return hashDoCelular;
    }

    public String getHashDaReclamacao() { return hashDaReclamacao; }
}