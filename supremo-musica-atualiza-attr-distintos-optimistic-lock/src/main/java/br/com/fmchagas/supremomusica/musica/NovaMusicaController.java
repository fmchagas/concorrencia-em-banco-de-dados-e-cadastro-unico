package br.com.fmchagas.supremomusica.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NovaMusicaController {
    private final MusicaRepository musicaRepository;

    public NovaMusicaController(MusicaRepository musicaRepository) { this.musicaRepository = musicaRepository; }

    @PostMapping("/musicas")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody NovaMusicaRequest request){
        Musica musica = request.toModel();

        musicaRepository.save(musica);

        Map<String, String> body = new HashMap<>();
        body.put("mensagem", "Criado com sucesso");
        body.put("id", musica.getId().toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
