package br.com.fmchagas.supremocadunico.contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    boolean existsByTelefoneAndDepartamento(String telefone, Departamento departamento);
}
