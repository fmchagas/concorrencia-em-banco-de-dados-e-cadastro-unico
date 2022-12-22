package br.com.fmchagas.supremobar.mesa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/mesas/{id}/reservas")
public class ReservaMesaController {
    private final MesaRepository mesaRepository;

    ReservaMesaController(MesaRepository mesaRepository){
        this.mesaRepository = mesaRepository;
    }

    @PostMapping
    @Transactional(Transactional.TxType.REQUIRED)
    public ResponseEntity<Void> execute(@PathVariable Long id, @RequestBody ReservaMesaRequest request){
        final Optional<Mesa> possivelMesa = mesaRepository.findById(id);


        final Mesa mesa = possivelMesa.orElseThrow(()-> new NotFoundException("Mesa não cadastrada"));

        mesa.reservar(request.getNome());
        mesaRepository.save(mesa);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}