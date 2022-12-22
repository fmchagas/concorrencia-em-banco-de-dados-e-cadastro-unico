package br.com.fmchagas.supremocadunico.dado_sensivel.reclamacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//https://gist.github.com/rafaelpontezup/77035f489958d3d8fb312ae543900307
@Repository
public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long> {
    boolean existsByHashDoCelularAndHashDaReclamacao(String hashDoCelular, String hashDaReclamacao);
}
