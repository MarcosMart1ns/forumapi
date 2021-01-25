package br.com.api.forum.repository;

import br.com.api.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    Curso findByNome(String curso);
}
