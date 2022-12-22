package br.com.fmchagas.supremocadunico.produto;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Map;

@RestController
public class NovoProdutoController {

    private final static Logger log = LoggerFactory.getLogger(NovoProdutoController.class);

    private ProdutoRepository repository;

    public NovoProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/produtos")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoProdutoRequest request) {
        Produto produto = request.toModel();

        if (repository.existsByCodigo(request.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Produto cadastrado");
        }

        repository.save(produto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Local advice controller
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException exception) {
        log.warn("422 - Unprocessable entity, Caused by: ", exception.getCause());

        Map<String, Object> body = Map.of(
                "message", "Produto cadastrado",
                "timestamp", Instant.now(),
                "status", "422",
                "error", "Unprocessable entity"
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}