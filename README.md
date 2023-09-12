# JornadaMilhas
Este repositório disponibiliza uma API rest desenvolvido em Java para reforçar conhecimentos aprendidos com Spring Boot, Maven, banco de dados MySql e a integração da API OpenAI.
O projeto teve como base o Desafio de progamação back-end 7 proposto pela Alura, disponibilizado no link abaixo:

https://www.alura.com.br/challenges/back-end-7

## Descrição do projeto
O projeto tem o objetivo de simular uma API de destinos de viagens, disponibilizando opções de CRUD de depoimentos gerados pelos usuarios, e o CRUD de destinos integrando um modelo de linguagem de inteligência artificial

## Endpoints de depoimentos da API

### Registrando um depoimento
#### ```POST /depoimentos```
Permite a criação de um novo depoimento de usuario, por meio da utilização de um JSON com o nome do usuário que está cadastrando(nome), o depoimento do usuário(depoimento) e a imagem escolhida(imagePath). Caso feito corretamente, deverá retornar outro json com o id do depoimento no banco e as informações inseridas

Exemplo de envio:
```json
"nome": "Dwayne Johnson"
"depoimento": "It was fun."
"imagePath": "imagepathurl.jpg"
```
Exemplo de resposta:
```json
"id":1
"nome": "Dwayne Johnson"
"depoimento": "It was fun."
"imagePath": "imageurlpath.jpg"
```

### Lista depoimentos randômicos
#### ```GET /depoimentos-home```
Seleciona até três depoimentos aleatórios dentro do banco e retorna uma lista dos três
Exemplo de resposta:
```json
[
    {
        "id": 1,
        "nome": "Dwayne Johnson"
        "depoimento": "It was fun."
        "imagePath": "imageurlpath.jpg"
    },
    {
        "id": 4,
        "nome": "Vin Diesel",
        "depoimento": "I was very fun.",
        "imagePath": "imageurlpath2.jpg"
    },
    {
        "id": 10,
        "nome": "John Cena",
        "depoimento": "Not funny at all.",
        "imagePath": "imageurlpath3.jpg"
    }
]
```

### Detalha um depoimento específico
#### ```GET /depoimentos/{id}```
Retorna o depoimento que corresponde ao id especificado caso estiver ativo

Exemplo de url:
```http://localhost:8080/depoimentos/1```

Exemplo de resposta:
```json
[
    {
        "id": 1,
        "nome": "Dwayne Johnson"
        "depoimento": "It was fun."
        "imagePath": "imageurlpath.jpg"
    }
]
```

### Atualiza depoimento
#### ```PUT /depoimentos/{id}```
Caso o usuário queira realizar uma atualização, deve ser informado um id de depoimento que esteja disponivel, com o campo a ser atualizado, seja o ```"nome"```, ```"depoimento"``` ou ```"imagePath"```, em um JSON

Exemplo de envio:
```json
"id": 1
"nome": "Dwayne Johnson (The Rock)"
```
Exemplo de resposta:
```json
"id":1
"nome": "Dwayne Johnson (The Rock)"
"depoimento": "It was fun."
"imagePath": "imageurlpath.jpg"
```

### Deleta depoimento
#### ```DELETE /depoimento/{id}```
Permite remover um depoimento com determinado id da visualização

## Endpoints de destinos da API

### Registrar um destino
#### ```POST /destinos```
realiza um cadastro de destinos, contendo o nome do destino("nome"), o preço da viajem("preco"), imagens do local("imagePath1" e "imagePath2"), a meta do destino("meta") como componentes obrigatórios e uma descrição("textoDescritivo") que pode ou não ser inserida no cadastro, caso ela não seja inserida no cadastro, o código irá realizar uma pesquisa no chatGPT para obter uma descrição de no máximo dois paragrafos e 100 caracteres

Exemplo de envio:
```json
{
    "nome": "Bertioga",
    "preco": 1000.00,
    "imagePath1": "C:\\Users\\User\\OneDrive\\Área de Trabalho\\codigos\\imagens teste\\bertioga1.jpg",
    "imagePath2": "C:\\Users\\User\\OneDrive\\Área de Trabalho\\codigos\\imagens teste\\bertioga2.jpg",
    "meta": "meta"
}
```
Exemplo de resposta:
```json
{
    "nome": "Bertioga",
    "preco": 1000.00,
    "imagePath1": "C:\\Users\\User\\OneDrive\\Área de Trabalho\\codigos\\imagens teste\\bertioga1.jpg",
    "imagePath2": "C:\\Users\\User\\OneDrive\\Área de Trabalho\\codigos\\imagens teste\\bertioga2.jpg",
    "meta": "meta",
    "textoDescritivo": "Bertioga é uma cidadezinha praiana no litoral de São Paulo, com praias maravilhosas e um clima relaxado. Perfeita para quem quer descanso e um bom mergulho no mar!"
}
```

### Listar destinos
#### ```GET /destinos-home```
Seleciona até dez destinos disponibilizados pela agência de viagens contendo todas as informações necessárias para o usuário
Exemplo de resposta:
```
[
    {
        "id": 1,
        "nome": "Santos",
        "imagePath1": "Santos.jpg",
        "imagePath2": "Santos2.jpg",
        "meta": null,
        "textoDescritivo": "Santos é uma cidade que combina história, cultura, praias deslumbrantes e uma atmosfera acolhedora. 
        Se você busca uma experiência diversificada que inclua relaxamento à beira-mar, exploração histórica e gastronomia saborosa",
        "preco": 200.0
    },
    {
        "id": 4,
        "nome": "Belo Horizonte",
        "imagePath1": "Belo Horizonte.jpg",
        "imagePath2": "Belo Horizonte 2.jpg",
        "meta": "meta",
        "textoDescritivo": "Belo Horizonte, BH pros íntimos, é aquela cidade das montanhas e comida boa. O pão de queijo e a cerveja gelada são lei por aqui. 
            Na capital mineira, o clima é quente, mas o coração do povo é ainda mais caloroso. 
            Com sua arquitetura marcante e cultura rica, BH é o lugar perfeito para quem quer curtir a vida com simplicidade e alegria.",
        "preco": 400.0
    },
    {
        "id": 5,
        "nome": "Bertioga",
        "imagePath1": "bertioga1.jpg",
        "imagePath2": "bertioga2.jpg",
        "meta": "meta",
        "textoDescritivo": "Bertioga é uma cidadezinha praiana no litoral de São Paulo, com praias maravilhosas e um clima relaxado. Perfeita para quem quer descanso e um bom mergulho no mar!",
        "preco": 600.0
    },
    {
        "id": 6,
        "nome": "Olimpia",
        "imagePath1": "Olimpia 1.jpg",
        "imagePath2": "Olimpia 2.jpg",
        "meta": "meta",
        "textoDescritivo": "Olímpia é um lugar top! Cidade brasileira famosa pelas águas quentes e parques aquáticos. Curtição garantida! 🏊‍♂️🌴 #DiversãoMolhada",
        "preco": 3000.0
    }
]
```

### Detalhar destino por id
#### ```GET destinos/{id}```
Retorna o destino que corresponde ao id especificado caso esteja ativo
exemplo de envio:
```http://localhost:8080/destinos/4```
exemplo de resposta:
```
{
    "id": 4,
    "nome": "Belo Horizonte",
    "imagePath1": "C:\\Users\\User\\OneDrive\\Área de Trabalho\\codigos\\imagens teste\\Belo Horizonte.jpg",
    "imagePath2": "C:\\Users\\User\\OneDrive\\Área de Trabalho\\codigos\\imagens teste\\Belo Horizonte 2.jpg",
    "meta": "meta",
    "textoDescritivo": "Belo Horizonte, BH pros íntimos, é aquela cidade das montanhas e comida boa. O pão de queijo e a cerveja gelada são lei por aqui. 
        Na capital mineira, o clima é quente, mas o coração do povo é ainda mais caloroso.
        Com sua arquitetura marcante e cultura rica, BH é o lugar perfeito para quem quer curtir a vida com simplicidade e alegria.",
    "preco": 400.0
}
```