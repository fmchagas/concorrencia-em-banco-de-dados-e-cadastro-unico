package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public record NovoBilheteRequest(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "^\\+[1-9]\\d\\d{1,14}", message = "deve se um número valido como no exemplo: +5511987654321")
        String celular,
        @NotNull
        @JsonProperty("numero-sorte") @Min(1) @Max(9999)
        Integer numeroSorte,
        @NotNull
        @JsonProperty("sorteio-id")
        Long sorteioId
) {

    public Bilhete toModel(Sorteio sorteio) {
        return new Bilhete(nome, celular, numeroSorte, sorteio);
    }
}