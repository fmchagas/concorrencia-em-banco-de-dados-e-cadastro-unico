package br.com.fmchagas.supremocadunico.livro;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Map;

@RestController
public class NovoLivroController {

    private final LivroRepository repository;

    public NovoLivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/livros")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoLivroRequest request){
        Livro livro = request.toModel();

        if(repository.existsByIsbn(request.getIsbn())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Livro cadastrado");
        }

        repository.save(livro);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Local advice controller - Controller Advice Local
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "message", "Livro já cadastrado",
                "timestamp", Instant.now(),
                "status", "422",
                "error", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}