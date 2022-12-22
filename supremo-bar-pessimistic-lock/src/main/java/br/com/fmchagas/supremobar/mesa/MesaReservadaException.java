package br.com.fmchagas.supremobar.mesa;

public class MesaReservadaException extends RuntimeException {
    public MesaReservadaException(String message) {
        super(message);
    }

    public MesaReservadaException(String message, Throwable cause) {
        super(message, cause);
    }
}
