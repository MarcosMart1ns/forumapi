package br.com.api.forum.controller.form;

import br.com.api.forum.modelo.Curso;
import br.com.api.forum.modelo.Topico;
import br.com.api.forum.repository.CursoRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class TopicoForm {

    @NotNull @NotEmpty @Length(min=5)
    private String titulo;
    @NotNull @NotEmpty @Length(min=5)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;


    public TopicoForm(String titulo, String mensagem, String nomeCurso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo,mensagem,curso);
    }
}
