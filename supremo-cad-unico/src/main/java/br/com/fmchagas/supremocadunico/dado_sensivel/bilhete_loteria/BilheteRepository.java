package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilheteRepository extends JpaRepository<Bilhete, Long> {
    boolean existsByHashCelularAndSorteioAndNumeroSorte(String hashCelular, Sorteio sorteio, Integer numeroSorte);
    boolean existsByHashCelularAndSorteioIdAndNumeroSorte(String hashCelular, Long sorteioId, Integer numeroSorte); // faz inner join
}
