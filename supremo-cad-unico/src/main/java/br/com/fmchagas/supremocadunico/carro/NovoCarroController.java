package br.com.fmchagas.supremocadunico.carro;

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
public class NovoCarroController {
    private final CarroRepository repository;

    @Autowired
    public NovoCarroController(CarroRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/carros")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoCarroRequest request, UriComponentsBuilder builder){
        Carro carro = request.toModel();

        if(repository.existsByPlaca(request.getPlaca())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carro já cadastrado no sistema");
        }

        if(repository.existsByChassi(request.getChassi())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carro já cadastrado no sistema");
        }

        repository.save(carro);


        final URI uri = builder.path("/carros/{id}").buildAndExpand(carro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handler(ConstraintViolationException exception, WebRequest request){

        String mensagem = "Carro com placa já cadastrada no sistema";
        if("UK_carro_chassi".equals(exception.getConstraintName())){
            mensagem = "Carro com chassi já cadastrada no sistema";
        }

        Map<String, Object> body = Map.of(
                "message", mensagem,
                "timestamp", Instant.now(),
                "status", "422",
                "error", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}