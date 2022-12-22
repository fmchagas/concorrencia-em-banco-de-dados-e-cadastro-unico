package br.com.fmchagas.supremocadunico.dado_sensivel.reclamacao;

import org.hibernate.exception.ConstraintViolationException;
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
public class NovaReclamacaoController {
    private final ReclamacaoRepository repository;

    public NovaReclamacaoController(ReclamacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/reclamacoes")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovaReclamacaoRequest request, UriComponentsBuilder uriBuilder){
        Reclamacao reclamacao = request.toModel();

        if (repository.existsByHashDoCelularAndHashDaReclamacao(reclamacao.getHashDoCelular(), reclamacao.getHashDaReclamacao())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Reclamação já cadastrada no sistema");
        }

        repository.save(reclamacao);

        final URI uri = uriBuilder.path("/reclamacoes/{id}").buildAndExpand(reclamacao.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handle(ConstraintViolationException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "message", "Reclamação já cadastrada no sistema",
                "timestamp", Instant.now(),
                "status", "422",
                "error", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}