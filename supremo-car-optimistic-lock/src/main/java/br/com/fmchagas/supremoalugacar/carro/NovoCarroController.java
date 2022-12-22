package br.com.fmchagas.supremoalugacar.carro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NovoCarroController {
    private final CarroRepository carroRepository;

    public NovoCarroController(CarroRepository carroRepository){
        this.carroRepository = carroRepository;
    }

    @PostMapping("/carros")
    @Transactional
    public ResponseEntity<Object> reservar(@RequestBody @Valid NovoCarroRequest request){
        Carro carro = request.toModel();

        carroRepository.save(carro);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Cadastrado com sucesso");
        response.put("id", carro.getId().toString());
        response.put("disponível", String.valueOf(carro.isDisponivel()));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
