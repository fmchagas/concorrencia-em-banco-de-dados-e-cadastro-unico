package br.com.fmchagas.supremocadunico.alunos;

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
public class NovoAlunoController {
    private final AlunoRepository repository;

    @Autowired
    public NovoAlunoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/alunos")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoAlunoRequest request, UriComponentsBuilder builder){
        if(repository.existsByMatriculaAndDataMatricula(request.matricula(), request.dataMatricula())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Aluno com matricula e data de matricula já cadastrada no sistema");
        }

        Aluno aluno = request.toModel();

        repository.save(aluno);

        final URI uri = builder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * Local advice controller - Controller Advice Local
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        Map<String, Object> body = Map.of(
                "message", "Aluno com matricula e data de matricula já cadastrada no sistema",
                "timestamp", Instant.now(),
                "status", "422",
                "error", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}