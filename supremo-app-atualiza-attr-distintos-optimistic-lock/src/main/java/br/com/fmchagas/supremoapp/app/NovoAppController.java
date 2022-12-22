package br.com.fmchagas.supremoapp.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class NovoAppController {

    private final AplicativoRepository repository;

    public NovoAppController(AplicativoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/apps")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoAppRequest request){
        Aplicativo aplicativo = request.toModel();

        repository.save(aplicativo);

        Map<String, String> body = new HashMap<>();
        body.put("mensagem", "Criado com sucesso");
        body.put("id", aplicativo.getId().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
