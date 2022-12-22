package br.com.fmchagas.supremocadunico.contato;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.Map;

@RestController
public class NonoContatoController {
    public final DepartamentoRepository departamentoRepository;
    public final ContatoRepository contatoRepository;

    public NonoContatoController(DepartamentoRepository departamentoRepository, ContatoRepository contatoRepository) {
        this.departamentoRepository = departamentoRepository;
        this.contatoRepository = contatoRepository;
    }

    @PostMapping("/departamentos/{id}/telefones")
    @Transactional
    public ResponseEntity<Object> cadastrar(@PathVariable Long id, @Valid @RequestBody NovoContatoRequest request, UriComponentsBuilder uriBuilder){
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado"));

        if (contatoRepository.existsByTelefoneAndDepartamento(request.telefone(), departamento)){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Contato para o departamento já cadastrado no sistema");
        }

        Contato contato = request.toModel(departamento);
        contatoRepository.save(contato);

        final URI location = uriBuilder.path("/departamentos/{id}/telefones/{id}").build(id, contato.getId());

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        String message = "";

        if("uk_contato_telefone_departamento_id".equals(exception.getConstraintName())){
            message = "Contato para o departamento já cadastrado no sistema";
        }

        Map<String, Object> body = Map.of(
                "message", message,
                "timestamp", Instant.now(),
                "status", "422",
                "erro", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}