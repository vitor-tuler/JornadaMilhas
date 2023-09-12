# JornadaMilhas
Este repositório que disponibiliza uma API rest desenvolvido em Java para reforçar conhecimentos aprendidos com Spring Boot, Maven, banco de dados MySql e a integração da API OpenAI.
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

### Pega depoimentos randômicos
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

### Pega depoimento específico
#### ```GET /depoimentos/{id}```
Quando for selecionado um id de usuário que está ativo, deve retornar as informações armazenadas
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
Caso o usuário queira realizar uma atualização, deve ser informado um id de depoimento que esteja disponivel, com o campo a ser atualizado, seja o ```"nome"```, ```"depoimento``` ou ```imagePath```, em um JSON
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

### Registrar um depoimento
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
    "meta": "meta"
}
```
