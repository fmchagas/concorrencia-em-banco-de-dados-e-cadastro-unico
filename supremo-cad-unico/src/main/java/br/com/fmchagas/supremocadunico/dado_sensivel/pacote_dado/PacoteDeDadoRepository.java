package br.com.fmchagas.supremocadunico.dado_sensivel.pacote_dado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteDeDadoRepository extends JpaRepository<PacoteDeDado, Long> {
    boolean existsByHashDoCpf(String hashDoCpf);
}
