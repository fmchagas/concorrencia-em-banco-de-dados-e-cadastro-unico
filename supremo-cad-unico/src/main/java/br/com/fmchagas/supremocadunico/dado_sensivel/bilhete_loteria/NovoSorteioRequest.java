package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record NovoSorteioRequest(
        @NotBlank
        @JsonProperty("descricao-premio")
        String descricaoPremio,
        @NotNull @Future
        @JsonProperty("sorteio-em")
        LocalDate sorteioEm
) {

    public Sorteio toModel() {
        return new Sorteio(descricaoPremio, sorteioEm);
    }
}