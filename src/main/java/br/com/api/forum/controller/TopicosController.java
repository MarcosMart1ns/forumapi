package br.com.api.forum.controller;
import br.com.api.forum.controller.DTO.TopicoDTO;
import br.com.api.forum.controller.form.TopicoForm;
import br.com.api.forum.modelo.Topico;
import br.com.api.forum.repository.CursoRepository;
import br.com.api.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> lista(String curso){
        if(curso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        }else{
            List<Topico> topicos = topicoRepository.findByCursoNome(curso);
            return TopicoDTO.converter(topicos);
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuider){
        Topico topico = form.converter(cursoRepository);
        try {
            topicoRepository.save(topico);
        }catch (Exception e){
            System.out.println(e);
        }
        URI uri = uriBuider.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }
}
