package br.com.jornadamilhas.viagens.domain.destinos;

public record DadosListaDestinos(Long id,
                                 String nome,
                                 String imagePath1,
                                 String imagePath2,
                                 String meta,
                                 String textoDescritivo,
                                 Float preco) {
    public DadosListaDestinos(Destinos destinos){
        this(destinos.getId(),
                destinos.getNome(),
                destinos.getImagePath1(),
                destinos.getImagePath2(),
                destinos.getMeta(),
                destinos.getTextoDescritivo(),
                destinos.getPreco());
    }
}
