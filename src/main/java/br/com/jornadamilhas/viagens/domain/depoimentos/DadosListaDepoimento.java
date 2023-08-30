package br.com.jornadamilhas.viagens.domain.depoimentos;

public record DadosListaDepoimento(Long id, String nome, String depoimento, String imagePath) {
    public DadosListaDepoimento(Depoimento depoimento){
        this(depoimento.getId(), depoimento.getNome(), depoimento.getDepoimento(), depoimento.getImagePath());
    }
}
