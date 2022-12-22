package br.com.fmchagas.supremobar.mesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    Optional<Mesa> findById(Long aLong);
}
