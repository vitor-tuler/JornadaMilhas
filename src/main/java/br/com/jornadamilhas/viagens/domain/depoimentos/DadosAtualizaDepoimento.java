package br.com.jornadamilhas.viagens.domain.depoimentos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DadosAtualizaDepoimento(
        @NotNull
        @PositiveOrZero
        Long id,
        String nome,
        String imagePath,
        String depoimento
) {
}
