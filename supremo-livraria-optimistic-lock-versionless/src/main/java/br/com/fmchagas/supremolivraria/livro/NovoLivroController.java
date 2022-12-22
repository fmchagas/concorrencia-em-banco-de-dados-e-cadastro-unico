package br.com.fmchagas.supremolivraria.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NovoLivroController {
    private final LivroRepository livroRepository;

    public NovoLivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    @PostMapping("/livros")
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid NovoLivroRequest request) {
        var livro = request.toModel();

        livro = livroRepository.save(livro);

        Map<String, String> body = new HashMap<>();

        body.put("code", "00");
        body.put("message", "Livro cadastrado com sucesso");
        body.put("id", livro.getId().toString());

        return ResponseEntity.status(201).body(body);
    }
}