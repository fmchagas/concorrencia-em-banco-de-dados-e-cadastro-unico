package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovoSorteioController {
    private final SorteioRepository sorteioRepository;

    @Autowired
    public NovoSorteioController(SorteioRepository sorteioRepository) {
        this.sorteioRepository = sorteioRepository;
    }

    @PostMapping("/sorteios")
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoSorteioRequest request){
        final Sorteio sorteio = request.toModel();

        sorteioRepository.save(sorteio);

        final URI uri = UriComponentsBuilder.fromPath("/sorteios/{id}").buildAndExpand(sorteio.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}