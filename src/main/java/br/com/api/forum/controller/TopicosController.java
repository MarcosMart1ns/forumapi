package br.com.api.forum.controller;
import br.com.api.forum.controller.DTO.DetalhesTopicoDTO;
import br.com.api.forum.controller.DTO.TopicoDTO;
import br.com.api.forum.controller.form.AtualizacaoTopicoForm;
import br.com.api.forum.controller.form.TopicoForm;
import br.com.api.forum.modelo.Topico;
import br.com.api.forum.repository.CursoRepository;
import br.com.api.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id){

        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok((new DetalhesTopicoDTO(topico.get())));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoForm form){

        Optional<Topico> optional = topicoRepository.findById(id);

        if(optional.isPresent()){
            Topico topico = form.atualizar(id,topicoRepository);
            return ResponseEntity.ok((new TopicoDTO(topico)));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> optional = topicoRepository.findById(id);

        if(optional.isPresent()) {
            topicoRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
