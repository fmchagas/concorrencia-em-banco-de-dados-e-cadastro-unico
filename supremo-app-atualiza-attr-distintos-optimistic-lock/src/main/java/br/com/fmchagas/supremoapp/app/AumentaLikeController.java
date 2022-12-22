package br.com.fmchagas.supremoapp.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class AumentaLikeController {
    private final AplicativoRepository repository;

    public AumentaLikeController(AplicativoRepository repository) {
        this.repository = repository;
    }

    @PatchMapping("/apps/{id}/likes")
    @Transactional
    public ResponseEntity<Object> incrementa(@PathVariable Long id){
        Aplicativo aplicativo = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aplicativo não encontrado."));

        aplicativo.aumentarLike();

        repository.save(aplicativo);

        return ResponseEntity.noContent().build();
    }
}
