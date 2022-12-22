package br.com.fmchagas.supremoapp.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaixaAppController {
    private final AplicativoRepository repository;

    public BaixaAppController(AplicativoRepository repository) {
        this.repository = repository;
    }

    @PatchMapping("/apps/{id}/downloads")
    @Transactional
    public ResponseEntity<Object> incrementa(@PathVariable Long id){
        Aplicativo aplicativo = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aplicativo não encontrado."));

        aplicativo.aumentarDownload();

        repository.save(aplicativo);

        Map<String, String> body = Map.of("link", aplicativo.getLinkArmazenamento());

        return ResponseEntity.ok(body);
    }
}