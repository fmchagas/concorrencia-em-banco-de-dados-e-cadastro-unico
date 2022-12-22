package br.com.fmchagas.supremocadunico.common;

import org.hibernate.StaleStateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static java.time.Instant.now;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "status", 404,
                "error", "Not found",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", now().toString(),
                "message", "Não encontrado!"
        );

        return ResponseEntity.status(404).body(body);
    }

    @ExceptionHandler(StaleStateException.class)
    public ResponseEntity<Object> handleOptimisticLockingException(StaleStateException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", now().toString(),
                "message", "Informações no servidor de dados estão obsoletas, tente novamente"
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}
