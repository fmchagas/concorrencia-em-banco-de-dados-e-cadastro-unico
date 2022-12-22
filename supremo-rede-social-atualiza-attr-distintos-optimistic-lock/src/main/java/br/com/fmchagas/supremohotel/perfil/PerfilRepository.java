package br.com.fmchagas.supremohotel.perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query(value = "from Perfil p inner join p.quantidadeDeFan where p.apelido= :apelido")
    Optional<Perfil> getByApelido(String apelido);
}
