package br.com.fmchagas.supremocadunico.dado_sensivel.pacote_dado;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CPFUtils {
    /*
     * Data masking - https://satoricyber.com/data-masking/data-masking-8-techniques-and-how-to-implement-them-successfully
     */
    /**
     * @param cpf Apenas números, não nulo e não vazio, com tamanho de 11 caracteres numéricos ex: 26211122209
     * @return ofuscação ex: 262******09
     * */
    public static String anonymize(@NotBlank String cpf) {
        String regex = "([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})";
        String masked = cpf.replaceAll(regex, "$1******$4");
        return masked;
    }

    /*
     * https://reflectoring.io/creating-hashes-in-java/
     * https://www.baeldung.com/sha-256-hashing-java
     */
    public static String hash(String cpf) {
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
}
