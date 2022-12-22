package br.com.fmchagas.supremohospital.leito;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class ReservaLeitoController {
    private final LeitoRepository leitoRepository;

    public ReservaLeitoController(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }


    @PatchMapping("/leitos/{id}")
    @Transactional
    public ResponseEntity<Object> reservar(@PathVariable(name = "id") Long identificador){
        Leito leito = leitoRepository.findById(identificador).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Leito não cadastrado"));

        if(leito.isIndisponivel()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Leito indisponível para reserva");
        }

        leito.reservar();
        leitoRepository.save(leito);

        return ResponseEntity.noContent().build();
    }
}