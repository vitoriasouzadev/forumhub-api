package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.*;
import br.com.alura.forumhub.model.Topico;
import br.com.alura.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    // CADASTRAR
    @PostMapping
    public ResponseEntity<DadosListagemTopico> cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder) {

        if(repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            throw new RuntimeException("Tópico já existe");
        }

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(dados.autor());
        topico.setCurso(dados.curso());

        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new DadosListagemTopico(topico));
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(Pageable paginacao){

        var page = repository.findAll(paginacao)
                .map(DadosListagemTopico::new);

        return ResponseEntity.ok(page);
    }

    // DETALHAR
    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemTopico> detalhar(@PathVariable Long id){

        Topico topico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }

    // ATUALIZAR
    @PutMapping
    public ResponseEntity<DadosListagemTopico> atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){

        Topico topico = repository.getReferenceById(dados.id());

        if(dados.titulo() != null){
            topico.setTitulo(dados.titulo());
        }

        if(dados.mensagem() != null){
            topico.setMensagem(dados.mensagem());
        }

        if(dados.status() != null){
            topico.setStatus(dados.status());
        }

        repository.save(topico);

        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}