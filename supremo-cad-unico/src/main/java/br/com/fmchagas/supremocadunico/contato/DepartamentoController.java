package br.com.fmchagas.supremocadunico.contato;

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

import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.Map;

@RestController
public class DepartamentoController {
    private final DepartamentoRepository repository;

    public DepartamentoController(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/departamentos")
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoDepartamentoRequest request, UriComponentsBuilder uriBuilder){
        if (repository.existsBySigla(request.getSigla())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Departamento com a mesma sigla já criada no sistema");
        }

        Departamento departamento = request.toModel();

        repository.save(departamento);

        final URI location = uriBuilder.path("/departamentos/{id}").buildAndExpand(departamento.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request){

        Map<String, Object> body = Map.of(
                "message", "Departamento com a mesma sigla já criada no sistema",
                "timestamp", Instant.now(),
                "status", "422",
                "erro", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}
