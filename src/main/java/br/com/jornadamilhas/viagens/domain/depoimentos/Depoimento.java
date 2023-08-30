package br.com.jornadamilhas.viagens.domain.depoimentos;

import br.com.jornadamilhas.viagens.infra.StringValidation;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "depoimento")
@Entity(name = "Depoimento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Depoimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String depoimento;
    private String imagePath;
    private boolean ativo;


    public Depoimento(DadosCadastroDepoimento dados) {
        this.nome = dados.nome();
        this.depoimento = dados.depoimento();
        this.imagePath = dados.imagePath();
        this.ativo = true;
    }

    public void atualizaInformacoes(DadosAtualizaDepoimento dados){
        if(!StringValidation.isNullEmptyOrBlank(dados.nome()))
            this.nome = dados.nome();

        if(!StringValidation.isNullEmptyOrBlank(dados.depoimento()))
            this.depoimento = dados.depoimento();

        if(!StringValidation.isNullEmptyOrBlank(dados.imagePath()))
            this.imagePath = dados.imagePath();
    }

    public void excluir() {
        this.ativo = false;
    }
}
