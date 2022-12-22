package br.com.fmchagas.supremoapp.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AplicativoRepository extends JpaRepository<Aplicativo, Long> { }
