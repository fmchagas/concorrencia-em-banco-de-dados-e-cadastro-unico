package br.com.fmchagas.supremocadunico.dado_sensivel.bilhete_loteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long> { }
