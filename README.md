# SoundSave 🎵

Projeto de estudo em **Java + Spring Boot**, desenvolvido durante a trilha "Carreira em Desenvolvimento Back-End Java" da Alura. O objetivo foi praticar persistência de dados com **Spring Data JPA** (relacionamentos entre entidades) e consumo de uma **API externa de IA** para gerar informações sobre artistas.

## 💡 Sobre o projeto

O SoundSave simula um pequeno catálogo de músicas e artistas. Cada artista pode ter várias músicas associadas, e o sistema usa uma LLM (via API compatível com a OpenAI) para gerar um resumo textual sobre um artista específico.

Ao rodar a aplicação, o próprio `CommandLineRunner`:
1. Cadastra alguns artistas e músicas de exemplo no banco;
2. Lista todas as músicas com seus respectivos artistas;
3. Faz uma chamada à API da Groq pedindo um resumo sobre um dos artistas cadastrados.

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.7
- Spring Data JPA / Hibernate
- PostgreSQL
- [OpenAI Java SDK](https://github.com/openai/openai-java) apontando para a API da [Groq](https://groq.com/) (modelo `llama-3.3-70b-versatile`)
- Maven

## 📁 Estrutura do projeto

```
src/main/java/com/vinicius/soundSave/
├── application/
│   └── Program.java          # Lógica de inicialização e demonstração
├── model/
│   ├── Artista.java           # Entidade Artista (1:N com Música)
│   └── Musica.java            # Entidade Música (N:1 com Artista)
├── repository/
│   ├── ArtistaRepository.java # Busca por nome e músicas por artista
│   └── MusicaRepository.java
└── service/
    └── GroqIAService.java     # Integração com a API da Groq
```

## ⚙️ Como rodar

O projeto precisa das seguintes variáveis de ambiente:

| Variável | Descrição |
|---|---|
| `DB_HOST` | Host do banco PostgreSQL |
| `DB_NAME` | Nome do banco de dados |
| `DB_USER` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `GROQ_API_KEY` | Chave de API da Groq (para o resumo de artistas) |

Com o banco criado e as variáveis configuradas, basta rodar:

```bash
./mvnw spring-boot:run
```

> ⚠️ O projeto usa `spring.jpa.hibernate.ddl-auto=create`, ou seja, o schema é recriado a cada execução — os dados de exemplo cadastrados no `Program.java` também serão inseridos novamente.

## O que foi praticado

- Modelagem de relacionamento `@OneToMany` / `@ManyToOne` com JPA
- Consultas customizadas com `@Query` (JPQL)
- Consumo de uma API HTTP externa a partir de um serviço Java

