package br.com.fmchagas.supremocadunico.dado_sensivel.reclamacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record NovaReclamacaoRequest(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^\\+[1-9]\\d\\d{1,14}", message = "deve se um número valido como no exemplo: +5511987654321")
        String celular,
        @NotBlank @Size(max = 4000)
        String reclamacao
) {

        public Reclamacao toModel() {
                return new Reclamacao(nome, email, celular, reclamacao);
        }
}