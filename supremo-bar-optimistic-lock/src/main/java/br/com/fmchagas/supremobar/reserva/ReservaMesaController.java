package br.com.fmchagas.supremobar.reserva;

import br.com.fmchagas.supremobar.mesa.Mesa;
import br.com.fmchagas.supremobar.mesa.MesaRepository;
import br.com.fmchagas.supremobar.mesa.MesaReservadaException;
import br.com.fmchagas.supremobar.mesa.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ReservaMesaController {
    private final MesaRepository mesaRepository;

    ReservaMesaController(MesaRepository mesaRepository){
        this.mesaRepository = mesaRepository;
    }

    @PatchMapping("/mesas/{id}")
    @Transactional
    public ResponseEntity<Void> execute(@PathVariable Long id, @RequestBody @Valid ReservaMesaRequest request){
        final Mesa mesa = mesaRepository.findById(id).orElseThrow(()-> new NotFoundException("Mesa não cadastrada"));

        if(mesa.isReservado()){
            throw new MesaReservadaException("Mesa reservada");
        }

        mesa.reservar(request.getReservadoPara());
        mesaRepository.save(mesa);

        return ResponseEntity.noContent().build();
    }
}