package br.com.fmchagas.supremohospital.leito;

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
public class NovoLeitoController {
    private final LeitoRepository leitoRepository;

    public NovoLeitoController(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }


    @PostMapping("/leitos")
    @Transactional
    public ResponseEntity<Object> novo(@RequestBody @Valid NovoLeitoRequest request){
        Leito leito = request.toModel();

        leitoRepository.save(leito);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Cadastrado com sucesso");
        response.put("id", leito.getId().toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
