package br.com.fmchagas.supremoreservasala.sala;

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
public class ReservaSalaController {

    private final SalaRepository salaRepository;

    public ReservaSalaController(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @PatchMapping("/salas/{id}")
    @Transactional
    public ResponseEntity<Object> cadastrar(@PathVariable Long id){
        var sala = salaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não cadastrada"));

        Map<String, String> body = new HashMap<>();

        if (sala.reservar()){
            body.put("code", "00");
            body.put("message", "Sala reservada");

            salaRepository.save(sala);
            return ResponseEntity.status(200).body(body);
        }

        body.put("message", "Não foi possível fazer a reserva, sala reservada.");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
    }
}
