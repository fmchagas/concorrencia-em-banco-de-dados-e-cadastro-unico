package br.com.fmchagas.supremohotel.quarto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class QuartoRequest {
   @NotBlank @Size(max = 200)
    private final String descricao;
   @NotNull
   @JsonProperty("tipo_quarto")
   private final TipoQuarto tipoQuarto;
   @Positive
   @NotNull
   @JsonProperty("valor_diaria")
   private final BigDecimal valorDiaria;

    public QuartoRequest(String descricao, TipoQuarto tipoQuarto, BigDecimal valorDiaria) {
        this.descricao = descricao;
        this.tipoQuarto = tipoQuarto;
        this.valorDiaria = valorDiaria;
    }

    public Quarto toModel() {
        return new Quarto(this.descricao, this.valorDiaria, this.tipoQuarto);
    }
}
