package br.com.jornadamilhas.viagens.domain.destinos;

import br.com.jornadamilhas.viagens.infra.StringValidation;
import br.com.jornadamilhas.viagens.infra.services.OpenAI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "destinos")
@Entity(name = "Destinos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destinos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagePath1;
    private String imagePath2;
    private String meta;
    private String textoDescritivo;
    private boolean ativo;

    public Destinos(DadosCadastroDestinos dados){
        this.nome = dados.nome();
        this.imagePath1 = dados.imagePath1();
        this.imagePath2 = dados.imagePath2();
        this.meta = dados.meta();
        this.textoDescritivo = dados.textoDescritivo();
        this.ativo = true;
        if (StringValidation.isNullEmptyOrBlank(textoDescritivo)){
            OpenAI gpt = new OpenAI();
            this.textoDescritivo = gpt.generateAIText(this.getNome());
        }
    }

    public void atualizaInformacoes(DadosAtualizaDestino dados){
        if(!StringValidation.isNullEmptyOrBlank(dados.nome()))
            this.nome = dados.nome();

        if(!StringValidation.isNullEmptyOrBlank(dados.imagePath1()))
            this.imagePath1 = dados.imagePath1();

        if(!StringValidation.isNullEmptyOrBlank(dados.imagePath2()))
            this.imagePath2 = dados.imagePath2();

        if(!StringValidation.isNullEmptyOrBlank(dados.meta()))
            this.meta = dados.meta();

        if(!StringValidation.isNullEmptyOrBlank(dados.textoDescritivo()))
            this.textoDescritivo = dados.textoDescritivo();
    }

    public void excluir() {
        this.ativo = false;
    }
}
