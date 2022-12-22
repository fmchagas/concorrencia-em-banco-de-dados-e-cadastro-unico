package br.com.fmchagas.supremoreservasala.sala;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NovaSalaController {
    private final SalaRepository salaRepository;

    public NovaSalaController(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @PostMapping("/salas")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid NovaSalaRequest request){
        var sala = request.toModel();

        sala = salaRepository.save(sala);

        Map<String, String> body = new HashMap<>();

        body.put("code", "00");
        body.put("message", "Sala cadastrada com sucesso");
        body.put("id", sala.getId().toString());

        return ResponseEntity.status(201).body(body);
    }
}