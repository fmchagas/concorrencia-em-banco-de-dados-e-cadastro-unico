package br.com.fmchagas.supremocadunico.ingressos;

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
public class NocoIngressoController {

    private final IngressoRepository repository;

    @Autowired
    public NocoIngressoController(IngressoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/ingressos")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoIngressoRequest request, UriComponentsBuilder uriBuilder){

        if(repository.existsByNumeroAndDataEvento(request.getNumero(), request.getDataEvento())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ingresso já criado no sistema");
        }

        Ingresso ingresso = request.toModel();

        repository.save(ingresso);

        final URI location = uriBuilder.path("/ingressos/{id}")
                .buildAndExpand(ingresso.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    /**
     * Local advice controller
     * ConstraintViolationException do hibernate
     * */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "message", "Ingresso já criado no sistema",
                "timestamp", Instant.now(),
                "status", "422",
                "erro", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}