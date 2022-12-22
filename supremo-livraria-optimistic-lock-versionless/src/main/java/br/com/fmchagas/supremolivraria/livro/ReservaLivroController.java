package br.com.fmchagas.supremolivraria.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestController
@RequestMapping("/livros/{isbn}")
public class ReservaLivroController {
    private final ExemplarRepository repository;

    public ReservaLivroController(ExemplarRepository repository) {
        this.repository = repository;
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<?> reservar(@PathVariable("isbn") String isbn) {
       Exemplar exemplar = repository.findFirstByReservadoIsFalseANDLivro_ISBNequals(isbn)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe exemplar cadastrado para este ISBN"));

       if (exemplar.isReservado()){
           throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Exemplar de livro reservado");
       }

        exemplar.reservar();
        repository.save(exemplar);

        return ResponseEntity.noContent().build();
    }
}