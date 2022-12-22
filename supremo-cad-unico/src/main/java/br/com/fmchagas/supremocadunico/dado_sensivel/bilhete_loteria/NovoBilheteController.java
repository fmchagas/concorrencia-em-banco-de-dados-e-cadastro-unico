package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import br.com.fmchagas.supremocadunico.common.NotFoundException;
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
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class NovoBilheteController {
    private final SorteioRepository sorteioRepository;
    private final BilheteRepository bilheteRepository;

    @Autowired
    public NovoBilheteController(SorteioRepository sorteioRepository, BilheteRepository bilheteRepository) {
        this.sorteioRepository = sorteioRepository;
        this.bilheteRepository = bilheteRepository;
    }

    @PostMapping("/bilhetes")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoBilheteRequest request){
        Sorteio sorteio = sorteioRepository.findById(request.sorteioId()).orElseThrow(()-> new NotFoundException("Não encontrado"));

        final Bilhete bilhete = request.toModel(sorteio);

        if (bilheteRepository.existsByHashCelularAndSorteioAndNumeroSorte(bilhete.getHashCelular(), sorteio, request.numeroSorte())){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Bilhete já cadastrado no sistema");
        }

        bilheteRepository.save(bilhete);

        URI uri = UriComponentsBuilder.fromPath("/bilhetes/{id}").buildAndExpand(bilhete.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request){

        Map<String, Object> body = Map.of(
                "message", "Bilhete já cadastrado no sistema",
                "timestamp", LocalDateTime.now(Clock.systemUTC()),
                "status", "422",
                "error", "Unprocessable entity",
                "path", request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}