package br.com.fmchagas.supremoalugacar.reserva;

import br.com.fmchagas.supremoalugacar.carro.Carro;
import br.com.fmchagas.supremoalugacar.carro.CarroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ReservaCarroController {
    private final CarroRepository carroRepository;

    public ReservaCarroController(CarroRepository carroRepository, ReservaRepository reservaRepository) {
        this.carroRepository = carroRepository;
    }

    @PostMapping("/carros/{id}/reservas")
    @Transactional
    public ResponseEntity<Object> reservar(@PathVariable Long id, @RequestBody @Valid ReservaCarroRequest request){
        Carro carro = carroRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não cadastrado!"));

        if (carro.isIndiponivel()){
            return new ResponseEntity<>("Carro indisponível para reserva", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Reserva reserva = request.toModel(carro);
        carro.reservar(reserva);

        carroRepository.saveAndFlush(carro);

        return ResponseEntity.noContent().build();
    }
}