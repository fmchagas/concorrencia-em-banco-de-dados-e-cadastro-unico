package br.com.fmchagas.supremohotel.perfil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PerfilController {

    private final PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @PostMapping("/perfil")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoPerfilRequest request, UriComponentsBuilder uriComponentsBuilder){
        final Perfil perfil = request.toModel();

        perfilRepository.save(perfil);

        URI location = uriComponentsBuilder.path("perfil/{id}")
                .buildAndExpand(perfil.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/perfil/{apelido}/fan")
    @Transactional
    public ResponseEntity<Void> cadastrar(@PathVariable String apelido){
        Perfil perfil = perfilRepository.getByApelido(apelido)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontramos perfil para esse usuário"));

        perfil.incrementaFan();

        perfilRepository.save(perfil);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/perfil/{id}")
    @Transactional
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizaPerfilRequest request){
        final Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontramos perfil para esse usuário"));

        perfil.atualiza(request.getNome());

        perfilRepository.save(perfil);

        return ResponseEntity.ok(perfil.toString());
    }
}
