package br.com.fmchagas.supremocadunico.alunos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByMatriculaAndDataMatricula(String matricula, LocalDate dataMatricula);
}
