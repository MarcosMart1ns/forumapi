# Forum Api

Uma simples API Rest de representação de um fórum, utilizada para aplicar alguns conhecimentos.
Utiliza conexão com um banco de dados local, H2 Database e construído com Spring.

### Tecnologias
- Java 11
- Spring Framework
- H2 Database

## Colocando pra funcionar

### Requisitos

- JDK 11 ou acima;
- Maven (Opcional)

### Como?

Abra o terminal na raíz no projeto e utilize o comando abaixo:

Caso não possua Maven instalado, o projeto já vem o mesmo 
embutido, portanto é possível rodar diretamente o projeto.

- Linux
 ```bash
 ./mvnw spring-boot:run
```
- Windows
 ```bash
 mvnw spring-boot:run
```

Agora é só acessar através do endereço localhost:8080/

## Rotas
As rotas abaixo são descritas no formato método/recurso, logo abaixo é descrito o formato da
requisição se necessário e o formato da resposta.


### GET /
Retorna uma mensagem constatando que a aplicação está rodando.

### GET /topicos

Retorna todos os tópicos cadastrados.

```JSON
{
  "id": 2,
  "titulo": "Dúvida 2",
  "mensagem": "Projeto não compila",
  "dataCriacao": "2019-05-05T19:00:00"
}
```

### GET /topicos?curso=nomeDoCurso

Retorna todos os tópicos cadastrados conforme o parâmetro informado na requisição,
onde deve ser ser informado a variável "?curso=" e o nome do curso a ser filtrado em seguida.

```JSON
[
  {
    "id": 3,
    "titulo": "Dúvida 3",
    "mensagem": "Tag HTML",
    "dataCriacao": "2019-05-05T20:00:00"
  }
]
```

### POST /topico

Cria um novo tópico, deve 
ser enviado um objeto json através do body da requisição, conforme o exemplo abaixo

```JSON
{
  "titulo":"Dúvidas Gerais!",
  "mensagem": "Dúvidas de java",
  "nomeCurso":"Spring Boot"
}
```

Em caso de sucesso na criação, será retornado o status 201(Create) e o json contendo 
a data de criação e o id criado no banco conforme o exemplo abaixo:

```JSON
{
  "id": 4,
  "titulo": "Dúvidas Gerais!",
  "mensagem": "Dúvidas de java",
  "dataCriacao": "2021-01-25T09:01:55.714683098"
}
```

