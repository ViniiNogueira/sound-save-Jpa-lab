# SoundSave

API REST para cadastro de artistas e musicas, desenvolvida em Java com Spring Boot. O projeto nasceu como uma aplicacao de terminal durante estudos de Java e Spring Data JPA, e foi evoluido para uma API Web com controllers, services, DTOs, persistencia em PostgreSQL e integracao com IA para gerar resumos sobre artistas.

## Funcionalidades

- Cadastrar artistas
- Listar artistas
- Buscar artista por ID
- Buscar artista por nome
- Remover artista
- Cadastrar musicas vinculadas a um artista
- Listar musicas
- Buscar musica por ID
- Remover musica
- Gerar resumo de artista usando IA via Groq/OpenAI SDK
- Visualizar a documentacao da API com Swagger UI

## Tecnologias

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- OpenAI Java SDK configurado para a API da Groq
- Springdoc OpenAPI / Swagger UI
- Maven

## Estrutura

```text
src/main/java/com/vinicius/soundSave/
|-- controller/
|   |-- ArtistaController.java
|   `-- MusicaController.java
|-- dto/
|   |-- ArtistaDTO.java
|   `-- MusicaDTO.java
|-- model/
|   |-- Artista.java
|   `-- Musica.java
|-- repository/
|   |-- ArtistaRepository.java
|   `-- MusicaRepository.java
|-- service/
|   |-- ArtistaService.java
|   |-- GroqIAService.java
|   `-- MusicaService.java
`-- SoundSaveApplication.java
```

## Como Rodar

Configure as variaveis de ambiente:

| Variavel | Descricao |
| --- | --- |
| `DB_HOST` | Host do PostgreSQL. Exemplo: `localhost` |
| `DB_NAME` | Nome do banco de dados |
| `DB_USER` | Usuario do banco |
| `DB_PASSWORD` | Senha do banco |
| `GROQ_API_KEY` | Chave da API da Groq para gerar resumos com IA |

Depois execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Swagger

Com a aplicacao rodando, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

## Endpoints

### Artistas

| Metodo | Rota                   | Descricao                          |
| --- |------------------------|------------------------------------|
| `GET` | `/artistas`            | Lista todos os artistas            |
| `GET` | `/artistas/{id}`       | Busca um artista por ID            |
| `POST` | `/artistas`            | Cadastra um artista                |
| `DELETE` | `/artistas/{id}`       | Remove um artista                  |
| `GET` | `/artistas/{id}/resumo` | Gera um resumo do artista usando IA |
| `GET` | `/artistas/busca`      | busca artista por nome semelhante  |

Exemplo de cadastro de artista:

```json
{
  "nome": "Bad Bunny"
}
```

### Musicas

| Metodo | Rota | Descricao |
| --- | --- | --- |
| `GET` | `/musicas` | Lista todas as musicas |
| `GET` | `/musicas/{id}` | Busca uma musica por ID |
| `POST` | `/musicas` | Cadastra uma musica vinculada a um artista |
| `DELETE` | `/musicas/{id}` | Remove uma musica |

Exemplo de cadastro de musica:

```json
{
  "titulo": "Titi Me Pregunto",
  "artistaId": 1
}
```

O campo `artistaId` deve ser o ID de um artista ja cadastrado.

## Banco De Dados

O projeto usa PostgreSQL e Spring Data JPA. A configuracao principal esta em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

## IA

O endpoint `GET /artistas/{id}/resumo` busca o artista no banco, coleta o nome e as musicas cadastradas, e envia esse contexto para a IA gerar um resumo em portugues.

A integracao usa o OpenAI Java SDK apontando para a API da Groq:

```text
https://api.groq.com/openai/v1
```

## Status Do Projeto

Projeto em desenvolvimento e estudo. Proximos pontos de melhoria:

- Adicionar validacoes com Bean Validation
- Criar tratamento global de excecoes
- Adicionar testes para controllers e services
- Melhorar configuracao da chave da IA
- Padronizar respostas de erro da API
