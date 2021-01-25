package br.com.api.forum.controller.form;

import br.com.api.forum.modelo.Topico;
import br.com.api.forum.repository.TopicoRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class AtualizacaoTopicoForm {

    @NotNull @NotEmpty @Length(min=5)
    private String titulo;

    @NotNull @NotEmpty @Length(min=5)
    private String mensagem;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return  topico;
    }
}
