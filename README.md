# Publications API

Backend de um sistema simplificado de publicações, desenvolvido como avaliação técnica. A API permite gerenciar usuários, publicações e comentários.

---

## Tecnologias utilizadas

- Java 21
- Spring Boot 3
- PostgreSQL
- Hibernate / JPA
- Maven
- Lombok
- Spring Security (BCrypt)

---

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- [JDK 21](https://adoptium.net)
- [PostgreSQL](https://www.postgresql.org/download)
- [Maven](https://maven.apache.org/download.cgi)

---

## Configuração

**1. Clone o repositório**

```bash
git clone https://github.com/seu-usuario/publications-api.git
cd publications-api
```

**2. Crie o banco de dados**

Acesse o PostgreSQL e execute:

```sql
CREATE DATABASE publications_db;
```

**3. Configure as variáveis de ambiente**

Copie o arquivo de exemplo e preencha com seus dados:

```bash
cp src/main/resources/application-local.properties
```

Edite o `application-local.properties` com suas credenciais:

```properties
DB_URL=sua_url
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

---

## Como rodar

```bash
mvn spring-boot:run
```

A aplicação vai subir na porta `8080`. As tabelas serão criadas automaticamente no banco.

---

## Endpoints

### Users

#### Criar usuário
```
POST /users
```
Request body:
```json
{
    "username": "joaosilva",
    "name": "João Silva",
    "email": "joao@email.com",
    "password": "123456",
    "biography": "Desenvolvedor Java"
}
```
Response `201 Created`:
```json
{
    "username": "joaosilva",
    "name": "João Silva",
    "email": "joao@email.com",
    "biography": "Desenvolvedor Java"
}
```

---

#### Buscar usuário por ID
```
GET /users/{id}
```
Response `200 OK`:
```json
{
    "username": "joaosilva",
    "name": "João Silva",
    "email": "joao@email.com",
    "biography": "Desenvolvedor Java"
}
```

---

#### Atualizar usuário
```
PUT /users/{id}
```
Request body:
```json
{
    "username": "joaosilva",
    "name": "João Silva Atualizado",
    "email": "joao@email.com",
    "password": "novaSenha123",
    "biography": "Desenvolvedor Java Sênior"
}
```
Response `200 OK`:
```json
{
    "username": "joaosilva",
    "name": "João Silva Atualizado",
    "email": "joao@email.com",
    "biography": "Desenvolvedor Java Sênior"
}
```

---

#### Apagar usuário
```
DELETE /users/{id}
```
Response `200 OK`

---


