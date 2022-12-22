package br.com.fmchagas.supremocadunico.carro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByPlaca(String placa);
    boolean existsByChassi(String chassi);
}
