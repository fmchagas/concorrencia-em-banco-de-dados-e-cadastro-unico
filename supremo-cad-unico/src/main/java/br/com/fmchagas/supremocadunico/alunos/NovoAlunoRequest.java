package br.com.fmchagas.supremocadunico.alunos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record NovoAlunoRequest(
        @NotBlank String nome,
        @NotBlank @CPF String cpf,
        @NotBlank @Size(min = 1, max = 6) String matricula,
        @PastOrPresent @JsonProperty("data-matricula") @NotNull LocalDate dataMatricula
) {
    public Aluno toModel() {
        return new Aluno(nome, cpf, matricula, dataMatricula);
    }
}
