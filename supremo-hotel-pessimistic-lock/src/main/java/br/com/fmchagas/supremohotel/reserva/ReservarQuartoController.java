package br.com.fmchagas.supremohotel.reserva;

import br.com.fmchagas.supremohotel.common.NotFoundException;
import br.com.fmchagas.supremohotel.quarto.Quarto;
import br.com.fmchagas.supremohotel.quarto.QuartoRepository;
import br.com.fmchagas.supremohotel.quarto.QuartoReservadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/quartos/{id}/reservas")
public class ReservarQuartoController {
    private final QuartoRepository quartoRepository;
    private final ReservaRepository reservaRepository;

    public ReservarQuartoController(QuartoRepository quartoRepository, ReservaRepository reservaRepository) {
        this.quartoRepository = quartoRepository;
        this.reservaRepository = reservaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> reservar(@PathVariable Long id, @RequestBody @Valid ReservaRequest request, UriComponentsBuilder uriComponentsBuilder){
        Quarto quarto = quartoRepository.findById(id).orElseThrow(()-> new NotFoundException("Quarto não cadastrado"));

        if(quarto.isReservado()){
            throw new QuartoReservadoException("Quarto reservado");
        }

        Reserva reserva = request.toModel(quarto);
        reserva.reservar();

        reserva = reservaRepository.save(reserva);

        URI location = uriComponentsBuilder.path("reservas/{id}")
                .buildAndExpand(reserva.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
