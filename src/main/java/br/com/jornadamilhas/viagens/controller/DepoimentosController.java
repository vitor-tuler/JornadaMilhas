package br.com.jornadamilhas.viagens.controller;

import br.com.jornadamilhas.viagens.domain.depoimentos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class DepoimentosController {

    private static final int QUANTIDADE_DEPOIMENTOS = 3;

    @Autowired
    private DepoimentosRepository repository;

    public DepoimentosController(DepoimentosRepository repository) {
        this.repository = repository;
    }


    @PostMapping("depoimentos")
    @Transactional
    public ResponseEntity postDepoimentos(@RequestBody @Valid DadosCadastroDepoimento dados, UriComponentsBuilder uri){
        Depoimento depoimento = new Depoimento(dados);
        repository.save(depoimento);
        return ResponseEntity.created(uri.path("/depoimentos/{id}").buildAndExpand(depoimento.getId()).toUri())
                .body(new DadosListaDepoimento(depoimento));
    }

    @GetMapping("depoimentos/{id}")
    public ResponseEntity getDepoimentoById(@PathVariable Long id){
        Optional<Depoimento> optional = repository.findById(id);
        if (optional.isPresent()) {
            Depoimento depoimento = optional.get();
            if (repository.getReferenceById(id).isAtivo()) {
                return ResponseEntity.ok(new DadosListaDepoimento(depoimento));
            } else return ResponseEntity.notFound().build();
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping("depoimentos-home")
    public ResponseEntity<List<DadosListaDepoimento>> getRandomDepoimentos(){
        List listaDeDepoimentos = new ArrayList<>(repository.findAll().stream()
                .filter(depoimento -> depoimento.isAtivo())
                .map(DadosListaDepoimento::new)
                .collect(Collectors.toList()));
        List listaDeDepoimentosRandomico = new ArrayList<DadosListaDepoimento>();
        Random random = new Random();

        Collections.shuffle(listaDeDepoimentos);
        int depoimentosSelecionados = Math.min(QUANTIDADE_DEPOIMENTOS, listaDeDepoimentos.size());
        for (int i = 0; i < depoimentosSelecionados; i++) {
            listaDeDepoimentosRandomico.add(listaDeDepoimentos.get(i));
        }
        return ResponseEntity.ok(listaDeDepoimentosRandomico);
    }

    @PutMapping("depoimentos/{id}")
    @Transactional
    public ResponseEntity putDepoimento(@RequestBody @Valid DadosAtualizaDepoimento dados){
        Depoimento depoimento = repository.getReferenceById(dados.id());
        depoimento.atualizaInformacoes(dados);
        return ResponseEntity.ok(new DadosListaDepoimento(depoimento));
    }

    @DeleteMapping("depoimentos/{id}")
    @Transactional
    public ResponseEntity deleteDepoimento(@PathVariable Long id){
        Optional<Depoimento> optional = repository.findById(id);
        if (optional.isPresent()){
            Depoimento depoimento = optional.get();
            if (depoimento.isAtivo()){
                depoimento.excluir();
                return ResponseEntity.noContent().build();
            }else return ResponseEntity.notFound().build();
        }else return ResponseEntity.notFound().build();
    }
}
