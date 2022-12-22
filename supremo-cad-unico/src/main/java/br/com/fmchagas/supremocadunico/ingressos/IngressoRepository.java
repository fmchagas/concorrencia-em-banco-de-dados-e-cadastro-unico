package br.com.fmchagas.supremocadunico.ingressos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    boolean existsByNumeroAndDataEvento(String numero, LocalDateTime dataEvento);
}
