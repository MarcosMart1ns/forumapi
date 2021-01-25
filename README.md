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
ser enviado um objeto json através do body da requisição, conforme o exemplo abaixo:

```JSON
//Requisição
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
  //Resposta
  "id": 4,
  "titulo": "Dúvidas Gerais!",
  "mensagem": "Dúvidas de java",
  "dataCriacao": "2021-01-25T09:01:55.714683098"
}
```


### GET /topicos/{id}

Retorna o topico conforme o id informado na uri.

```JSON
{
    "id": 1,
    "titulo": "Dúvida",
    "mensagem": "Erro ao criar projeto",
    "dataCriacao": "2019-05-05T18:00:00"
}
```

### PUT /topicos/{id}

Atualiza no topico informado no uri, os campos título e mensagem conforme o JSON informados na requisição.

```JSON
{
  //Requisição
    "titulo": "Dúvida",
    "mensagem": "Erro ao criar projeto",
}
```

```JSON
{
  //Resposta
  "id": 1,
  "titulo": "Atualizado",
  "mensagem": "mensagem nova",
  "dataCriacao": "2019-05-05T18:00:00"
}
```

### DELETE /topicos/{id}

Apaga o topico do id informado na uri.
Caso o topico tenha sido apagado com sucesso, será retornado o status 200(OK).


