package br.com.fmchagas.supremocadunico.contato;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NovoDepartamentoRequest {
    @NotBlank @Size(max = 120)
    private final String nome;

    @NotBlank
    @Size(min=1, max = 3)
    @Pattern(regexp = "^[A-Z]+$", message = "deve ser letras maiúsculas")
    private final String sigla;

    public NovoDepartamentoRequest(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Departamento toModel(){
        return new Departamento(nome, sigla);
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}
