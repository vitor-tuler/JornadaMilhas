package br.com.jornadamilhas.viagens.domain.destinos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosAtualizaDestino(
        @NotNull
        @PositiveOrZero
        Long id,
        String nome,
        Float preco,
        String imagePath1,
        String imagePath2,
        String meta,
        String textoDescritivo
) {
}
