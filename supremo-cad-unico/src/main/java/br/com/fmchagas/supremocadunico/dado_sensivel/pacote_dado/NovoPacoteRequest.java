package br.com.fmchagas.supremocadunico.dado_sensivel.pacote_dado;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

public record NovoPacoteRequest(
        @NotBlank
        String nome,
        @NotBlank
        @CPF
        String cpf,
        @NotBlank
        @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}", message = "deve se um número valido como no exemplo: +5511987654321")
        String telefone,
        @NotNull @Min(5) @Max(50)
        @JsonProperty("quantidade-de-dado")
        Integer quantidadeDeDado // em gigas
) {

        public PacoteDeDado toModel(){
                return new PacoteDeDado(nome, cpf, telefone, quantidadeDeDado);
        }
}