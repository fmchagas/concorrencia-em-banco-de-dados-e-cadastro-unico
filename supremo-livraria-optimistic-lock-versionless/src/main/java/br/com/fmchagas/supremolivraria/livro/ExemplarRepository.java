package br.com.fmchagas.supremolivraria.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
    @Query(value = "SELECT * FROM exemplar e INNER JOIN livro l ON (e.livro_id = l.id) WHERE e.reservado = false AND l.isbn= :isbn LIMIT 1", nativeQuery = true)
    Optional<Exemplar> findFirstByReservadoIsFalseANDLivro_ISBNequals(String isbn);
}
