package br.com.fmchagas.supremohotel.common;

import br.com.fmchagas.supremohotel.quarto.QuartoReservadoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static java.time.Instant.now;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(QuartoReservadoException.class)
    public ResponseEntity<Object> handleQuartoReservadoException(QuartoReservadoException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", now().toString(),
                "message", "Quarto reservado!"
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
                "message", "Quarto não encontrado!"
        );

        return ResponseEntity.status(404).body(body);
    }
}
