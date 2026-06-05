# Publications API

Backend de um sistema simplificado de publicações, desenvolvido como teste técnico para gerenciamento de usuários, publicações e comentários.

## Visão geral

Esta API foi construída com Java e Spring Boot e permite:

- Cadastrar, buscar, atualizar e remover usuários;
- Criar, buscar, atualizar, arquivar e remover publicações;
- Criar, atualizar e remover comentários;
- Listar publicações públicas de um usuário;
- Listar comentários feitos por um usuário em publicações públicas;
- Listar comentários de uma publicação.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Security
- PostgreSQL
- Hibernate / JPA
- Maven Wrapper
- Lombok

## Estrutura do projeto

```text
src/main/java/com/example/publications_api
|-- config
|   `-- SecurityConfig.java
|-- controller
|   |-- UserController.java
|   |-- PostController.java
|   `-- CommentController.java
|-- dto
|   |-- user
|   |-- post
|   |-- comment
|   `-- error
|-- exceptions
|   |-- BusinessException.java
|   |-- ResourceNotFoundException.java
|   |-- UnauthorizedException.java
|   `-- GlobalControllerAdvice.java
|-- model
|   |-- User.java
|   |-- Post.java
|   `-- Comment.java
|-- repository
|   |-- UserRepository.java
|   |-- PostRepository.java
|   `-- CommentRepository.java
`-- service
    |-- UserService.java
    |-- PostService.java
    `-- CommentService.java
```

## Modelagem de dados

### User

- `idUser`
- `username`
- `name`
- `email`
- `password`
- `biography`
- `createdAt`
- `updatedAt`

### Post

- `idPost`
- `userId`
- `text`
- `archived`
- `createdAt`
- `updatedAt`

### Comment

- `idComment`
- `userId`
- `postId`
- `message`
- `createdAt`
- `updatedAt`

## Pré-requisitos

Antes de rodar o projeto, tenha instalado:

- JDK 21
- PostgreSQL
- Git

Opcional:

- IntelliJ IDEA ou outra IDE Java
- Postman ou Insomnia para testar os endpoints

## Configuração do banco de dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE publications_db;
```

## Variáveis de ambiente

O projeto usa estas variáveis:

```properties
DB_URL=jdbc:postgresql://localhost:5432/publications_db
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/publications-api.git
cd publications-api
```

### 2. Compilar

No Windows:

```powershell
.\mvnw.cmd clean compile
```

No Linux/macOS:

```bash
./mvnw clean compile
```

### 3. Rodar a aplicação

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

A API será iniciada por padrão em:

```text
http://localhost:8080
```

## Configurações da aplicação

Arquivo: `src/main/resources/application.properties`

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

## Comportamento de segurança

- O projeto usa Spring Security apenas para configurações básicas.
- Todas as rotas estão liberadas (`permitAll()`).
- Não existe autenticação por JWT, OAuth ou sessão.
- A posse de post/comentário é controlada por comparação do `idUser` enviado na URL.

## Validações implementadas

### UserRequestDTO

- `username`: obrigatório
- `name`: obrigatório
- `email`: obrigatório e com formato válido
- `password`: obrigatória e com mínimo de 6 caracteres
- `biography`: máximo de 500 caracteres

### PostRequestDTO

- `userId`: obrigatório
- `text`: obrigatório
- `text`: entre 1 e 5000 caracteres

### CommentRequestDTO

- `userId`: obrigatório
- `postId`: obrigatório
- `message`: obrigatória
- `message`: entre 1 e 5000 caracteres

## Tratamento de erros

Erros são retornados em formato padronizado:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "text: É obrigatório escrever um texto.",
  "timestamp": "2026-06-05T20:00:00"
}
```

### Status tratados globalmente

- `400 Bad Request`
- `403 Forbidden`
- `404 Not Found`

## Base URL

```text
http://localhost:8080
```

## Resumo dos endpoints

### Users

| Método | Rota | Descrição |
|---|---|---|
| POST | `/users` | Criar usuario |
| GET | `/users/{idUser}` | Buscar usuario por ID |
| PUT | `/users/{idUser}` | Atualizar usuario |
| DELETE | `/users/{idUser}` | Deletar usuario |
| GET | `/users/{idUser}/posts` | Listar posts públicos do usuário |
| GET | `/users/{idUser}/comments` | Listar comentários feitos pelo usuário em posts públicos |

### Posts

| Método | Rota | Descrição |
|---|---|---|
| POST | `/posts` | Criar post |
| GET | `/posts/{idPost}` | Buscar post por ID |
| PUT | `/posts/{idPost}/{idUser}` | Atualizar post com validação de posse |
| DELETE | `/posts/{idPost}/{idUser}` | Deletar post com validação de posse |
| PATCH | `/posts/{idPost}/{idUser}` | Arquivar post com validação de posse |
| GET | `/posts/{idPost}/comments` | Listar comentários do post |

### Comments

| Método | Rota | Descrição |
|---|---|---|
| POST | `/comments` | Criar comentário |
| PUT | `/comments/{idComment}/{idPost}/{idUser}` | Atualizar comentário com validação de posse |
| DELETE | `/comments/{idComment}/{idUser}` | Deletar comentário com validação de posse |

## Exemplos de uso dos endpoints

### 1. Criar usuário

![Criar usuário](docs/create%20user.png)


### 2. Buscar usuário por ID

![Buscar usuário](docs/find%20user.png)

### 3. Atualizar usuário

![Atualizar usuário](docs/update%20user.png)

### 4. Deletar usuário

![Deletar usuário](docs/delete%20user.png)

### 5. Listar posts públicos de um usuário

![Listar posts públicos de um usuário](docs/find%20user%20public%20posts.png)

### 6. Listar comentários feitos por um usuário em posts públicos

![Listar comentários feitos por um usuário em posts públicos](docs/find%20user%20comments%20on%20public%20posts.png)

### 7. Criar post

![Criar post](docs/create%20post.png)

### 8. Buscar post por ID

![Buscar post por ID](docs/find%20post.png)

### 9. Atualizar post

![Atualizar post](docs/update%20post.png)

### 10. Deletar post

![Deletar post](docs/delete%20post.png)

### 11. Arquivar post

![Arquivar post](docs/archive%20post.png)

### 12. Listar comentários de um post

![Listar comentários de um post](docs/find%20comments%20on%20posts.png)

### 13. Criar comentário

![Criar comentário](docs/create%20comment.png)

### 14. Atualizar comentário

![Atualizar comentário](docs/update%20comment.png)

### 15. Deletar comentário

![Deletar comentário](docs/delete%20comment.png)

## Fluxo recomendado para demonstração no Postman

1. Criar usuário 1
2. Criar usuário 2
3. Criar post com usuário 1
4. Buscar post por ID
5. Criar comentário no post
6. Atualizar comentário
7. Listar comentários do post
8. Listar comentários do usuário em posts públicos
9. Testar validação com body inválido
10. Testar regra de posse com `idUser` incorreto

