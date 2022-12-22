package br.com.fmchagas.supremohotel.quarto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    Optional<Quarto> findById(Long id);
}
