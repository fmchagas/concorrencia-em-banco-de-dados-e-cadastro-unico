package br.com.fmchagas.supremocadunico.dado_sensivel.pacote_dado;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.Map;

@RestController
public class NovoPacoteController {
    private final PacoteDeDadoRepository repository;

    @Autowired
    public NovoPacoteController(PacoteDeDadoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/pacotes")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoPacoteRequest request, UriComponentsBuilder uriBuilder){
        final String hashDoCpf = CPFUtils.hash(request.cpf());

        if(repository.existsByHashDoCpf(hashDoCpf)){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Pacote de dado já cadastrado no sistema");
        }

        PacoteDeDado pacoteDeDado = request.toModel();

        repository.save(pacoteDeDado);

        final URI uri = uriBuilder.path("/pacotes/{id}").buildAndExpand(pacoteDeDado.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Controller Advice Local
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "message", "Pacote de dado já cadastrado no sistema",
                "timestamp", Instant.now(),
                "status", "422",
                "error", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}