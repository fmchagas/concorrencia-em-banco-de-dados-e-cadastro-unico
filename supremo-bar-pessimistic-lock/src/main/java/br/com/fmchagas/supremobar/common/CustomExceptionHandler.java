package br.com.fmchagas.supremobar.common;

import br.com.fmchagas.supremobar.mesa.MesaReservadaException;
import br.com.fmchagas.supremobar.mesa.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static java.time.Instant.now;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MesaReservadaException.class)
    public ResponseEntity<Object> handleMesaReservadaException(MesaReservadaException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", now().toString(),
                "message", "Mesa reservada!"
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "status", 404,
                "error", "Not found",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", now().toString(),
                "message", "Mesa não encontrada!"
        );

        return ResponseEntity.status(404).body(body);
    }
}
