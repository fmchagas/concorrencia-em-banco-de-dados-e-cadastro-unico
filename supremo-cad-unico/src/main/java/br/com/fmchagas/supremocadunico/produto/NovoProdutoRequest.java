package br.com.fmchagas.supremocadunico.produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class NovoProdutoRequest {
    @NotBlank
    private String nome;

    @NotBlank @Size(min = 1, max = 6)
    private String codigo;
    @Positive
    private BigDecimal valor;

    public NovoProdutoRequest(String nome, String codigo, BigDecimal valor) {
        this.nome = nome.trim();
        this.valor = valor;
        this.codigo = codigo.trim();
    }


    public Produto toModel() {
        return new Produto(nome, valor, codigo);
    }

    public String getCodigo() {
        return codigo;
    }
}
