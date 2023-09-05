# JornadaMilhas
Este repositório que disponibiliza uma API rest desenvolvido em Java para reforçar conhecimentos aprendidos com Spring Boot, Maven, banco de dados MySql e a integração da API OpenAI.
O projeto teve como base o Desafio de progamação back-end 7 proposto pela Alura, disponibilizado no link abaixo:
https://www.alura.com.br/challenges/back-end-7

## Descrição do projeto
O projeto tem o objetivo de simular uma API de destinos de viagens, disponibilizando opções de CRUD de depoimentos gerados pelos usuarios, e o CRUD de destinos integrando um modelo de linguagem de inteligência artificial

## Endpoints da API

### Registrando um depoimento
#### ```POST /depoimentos```
Permite a criação de um novo depoimento de usuario, por meio da utilização de um JSON com o nome do usuário que está cadastrando(nome), o depoimento do usuário(depoimento) e a imagem escolhida(imagePath). Caso feito corretamente, deverá retornar outro json com o id do depoimento no banco e as informações inseridas
Exemplo de envio:
```json
"nome": "Vitor Tuler"
"depoimento": "Tive uma experiência ótima"
"imagePath": "imagepathurl.jpg"
```
Exemplo de resposta:
```json
"id":1
"nome": "Vitor Tuler"
"depoimento": "It was fun."
"imagePath": "imageurlpath.jpg"
```

### Pega depoimentos randômicos
#### ```GET /depoimentos-home```
Seleciona até três depoimentos aleatórios dentro do banco e retorna uma lista dos três
```json
[
    {
        "id": 1,
        "nome": "John Galt"
        "depoimento": "It was fun."
        "imagePath": "imageurlpath.jpg"
    },
    {
        "id": 4,
        "nome": "Leornardo da Vinci",
        "depoimento": "I was very fun.",
        "imagePath": "imageurlpath2.jpg"
    },
    {
        "id": 10,
        "nome": "Michelangelo",
        "depoimento": "Not funny at all.",
        "imagePath": "imageurlpath3.jpg"
    }
]
```
