package br.com.jornadamilhas.viagens.domain.depoimentos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDepoimento(
        @NotBlank
        String nome,
        @NotBlank
        String depoimento,
        @NotBlank
        String imagePath){
}
