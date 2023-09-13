package br.com.jornadamilhas.viagens.controller;

import br.com.jornadamilhas.viagens.domain.destinos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class DestinosController {

    @Autowired
    private DestinosRepository repository;

    public DestinosController(DestinosRepository repository) {
        this.repository = repository;
    }

    @PostMapping("destinos")
    @Transactional
    public ResponseEntity postDestinos(@RequestBody @Valid DadosCadastroDestinos dados, UriComponentsBuilder uri){
        Destinos destino = new Destinos(dados);
        repository.save(destino);
        return ResponseEntity.created(uri.path("/destinos/{id}").buildAndExpand(destino.getId()).toUri())
                .body(new DadosListaDestinos(destino));
    }

    @GetMapping("destinos-home")
    public ResponseEntity<List<DadosListaDestinos>> getDestinos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        List listaDeDestinos = new ArrayList<>(repository.findAll().stream()
                .filter(depoimento -> depoimento.isAtivo())
                .map(DadosListaDestinos::new)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(repository.findAll().stream()
                .filter(destinos -> destinos.isAtivo()).map(DadosListaDestinos::new).toList());
    }

    @GetMapping("destinos")
    @ResponseBody
    public ResponseEntity<List<DadosListaDestinos>> getDestinosByNome(@RequestParam String nome)
    {
        List<DadosListaDestinos> destinosFiltrados = repository.findAll()
                .stream()
                .filter(destination -> destination.getNome().equals(nome) && destination.isAtivo())
                .map(DadosListaDestinos::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(destinosFiltrados);
    }

    @GetMapping("destinos/{id}")
    public ResponseEntity getDestinosById(@PathVariable Long id){
        Optional<Destinos> optional = repository.findById(id);
        if (optional.isPresent()) {
            Destinos destinos = optional.get();
            if (repository.getReferenceById(id).isAtivo()) {
                return ResponseEntity.ok(new DadosListaDestinos(destinos));
            } else return ResponseEntity.notFound().build();
        }else return ResponseEntity.notFound().build();
    }

    @PutMapping("destinos")
    @Transactional
    public ResponseEntity putDestinos(@RequestBody @Valid DadosAtualizaDestino dados){
        Destinos destinos = repository.getReferenceById(dados.id());
        destinos.atualizaInformacoes(dados);
        return ResponseEntity.ok(new DadosListaDestinos(destinos));
    }

    @DeleteMapping("destinos/{id}")
    @Transactional
    public ResponseEntity deleteDestinos(@PathVariable Long id){
        Optional<Destinos> optional = repository.findById(id);
        if (optional.isPresent()){
            Destinos destinos = optional.get();
            if (destinos.isAtivo()){
                destinos.excluir();
                return ResponseEntity.noContent().build();
            }else return ResponseEntity.notFound().build();
        }else return ResponseEntity.notFound().build();
    }
}
