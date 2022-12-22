package br.com.fmchagas.supremocadunico.ingressos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class NovoIngressoRequest {
    @NotBlank
    @JsonProperty("nome-cliente")
    private String nomeCliente;
    @NotBlank
    @CPF
    @JsonProperty("cpf-cliente")
    private String cpfCliente;
    @NotBlank
    private String numero;
    @Future
    @JsonProperty("data-evento")
    private LocalDateTime dataEvento;


    public NovoIngressoRequest(@JsonProperty("nome-cliente") String nomeCliente, @JsonProperty("cpf-cliente") String cpfCliente, String numero, @JsonProperty("data-evento") LocalDateTime dataEvento) {
        this.nomeCliente = nomeCliente.trim();
        this.cpfCliente = cpfCliente.trim();
        this.numero = numero.trim();
        this.dataEvento = dataEvento;
    }

    public Ingresso toModel() {
        return new Ingresso(nomeCliente, cpfCliente, numero, dataEvento);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }
}