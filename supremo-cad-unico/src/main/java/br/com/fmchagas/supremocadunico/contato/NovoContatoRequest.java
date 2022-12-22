package br.com.fmchagas.supremocadunico.contato;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public record NovoContatoRequest(
        @NotBlank
        @Pattern(regexp = "^\\+[1-9]\\d\\d{1,14}", message = "deve se um número valido como no exemplo: +5511987654321")
        String telefone,
        @NotBlank
        @JsonProperty("funcionario-reponsavel")
        String funcionarioReponsavel,
        @NotNull
        @Past
        @JsonProperty("cadastrado-em")
        LocalDate cadastradoEm
) {

    public Contato toModel(Departamento departamento) {
        return new Contato(telefone, funcionarioReponsavel, cadastradoEm, departamento);
    }

    @Override
    public String telefone() {
        return telefone;
    }

    @Override
    public String funcionarioReponsavel() {
        return funcionarioReponsavel;
    }

    @Override
    public LocalDate cadastradoEm() {
        return cadastradoEm;
    }
}