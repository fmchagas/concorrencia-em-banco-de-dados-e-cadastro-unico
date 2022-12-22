package br.com.fmchagas.supremohotel.quarto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/quartos")
public class NovoQuartoController {
    private final QuartoRepository quartoRepository;

    public NovoQuartoController(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody QuartoRequest request, UriComponentsBuilder uriComponentsBuilder){
        Quarto quarto = request.toModel();

        var quartoSalvo = quartoRepository.save(quarto);

        URI location = uriComponentsBuilder.path("quartos/{id}")
                .buildAndExpand(quartoSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
