package br.com.fmchagas.supremomusica.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class IncrementaOuvintesController {
    private final MusicaRepository repository;

    public IncrementaOuvintesController(MusicaRepository repository) {
        this.repository = repository;
    }

    @PatchMapping("/musicas/{id}/ouvintes")
    @Transactional
    public ResponseEntity<?> incrementa(@PathVariable Long id){
        Musica musica = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica nao cadastrada no sistema."));

        musica.aumentarOuvinte();

        repository.save(musica);

        return ResponseEntity.noContent().build();
    }
}
