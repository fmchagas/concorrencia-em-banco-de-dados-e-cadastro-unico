package br.com.fmchagas.supremohotel.quarto;

public class QuartoReservadoException extends RuntimeException {
    public QuartoReservadoException(String message) {
        super(message);
    }

    public QuartoReservadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
