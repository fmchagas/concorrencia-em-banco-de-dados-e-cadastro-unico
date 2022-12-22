package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import br.com.fmchagas.supremocadunico.common.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FindBilheteController {
    private final BilheteRepository bilheteRepository;

    public FindBilheteController(BilheteRepository bilheteRepository) {
        this.bilheteRepository = bilheteRepository;
    }


    @GetMapping("/bilhetes/{id}")
    public ResponseEntity<Object> obterPorId(@PathVariable Long id){
        final Bilhete bilhete = bilheteRepository.findById(id).orElseThrow(() -> new NotFoundException("Bilhete não encontrado"));

        byte[] test = {-15,-110,-4,113,-108,-94,95,-82,-18,-114,61,-62,-9,-20,75,25,-5,10,-4,9,70,-92,-63,38,-50,-69,-64,-64,-31,-55,-30,-67};
        System.out.println(Bilhete.toHex(test));

        Map<String, Object> body = Map.of(
                "hashCelular", bilhete.getHashCelular(),
                "hashBinaryCelularToHex", Bilhete.toHex(bilhete.getHashBinaryCelular()),
                "hashBinaryCelular", bilhete.getHashBinaryCelular()
        );

        return ResponseEntity.ok().body(body);
    }
}
