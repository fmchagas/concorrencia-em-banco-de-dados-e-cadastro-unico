package br.com.fmchagas.supremobar.mesa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/mesas")
public class NovaMesaController {
    private final MesaRepository repository;

    public NovaMesaController(MesaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid MesaRequest request, UriComponentsBuilder uriComponentsBuilder){

        Mesa mesa = request.paraMesa();

        repository.save(mesa);

        URI location = uriComponentsBuilder.path("/mesas/{id}")
                .buildAndExpand(mesa.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}