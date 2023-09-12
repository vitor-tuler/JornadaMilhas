package br.com.jornadamilhas.viagens.domain.destinos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDestinos(
        @NotBlank
        String nome,
        @NotBlank
        Float preco,
        @NotNull
        @NotBlank
        String imagePath1,
        @NotBlank
        String imagePath2,
        @NotBlank
        String meta,
        String textoDescritivo) {

}
