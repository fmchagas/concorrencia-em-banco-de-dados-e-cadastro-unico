package br.com.fmchagas.supremoalugacar.carro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    Optional<Carro> findById(Long id);
}
