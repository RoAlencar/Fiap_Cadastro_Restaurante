# 🍽️ Cadastro de Restaurante API

Projeto desenvolvido como parte da **Fase 2 de avaliação**, com o objetivo de implementar uma API REST completa para **cadastro e gerenciamento de restaurantes**, usuários, tipos de usuário e itens de cardápio, seguindo os princípios de **Clean Architecture**, boas práticas do **Spring Boot** e com suporte a **Docker**.

---

## 📌 Objetivo do Projeto

Criar um **CRUD completo de Restaurante**, permitindo:

- Cadastro de Restaurantes
- Associação de um **usuário responsável** (dono do restaurante)
- Gerenciamento de **itens do cardápio**
- Cadastro de **tipos de usuário**

O sistema foi projetado para ser **escalável, organizado e testável**, atendendo todos os critérios de avaliação definidos.

---

## 🧩 Funcionalidades Implementadas

### ✅ Cadastro de Tipo de Usuário
- Criar
- Listar
- Buscar por ID
- Atualizar
- Remover

### ✅ Cadastro de Usuário
- Criar usuários do sistema
- Associar usuário a um restaurante como dono

### ✅ Cadastro de Restaurante
Campos obrigatórios:
- **Nome**
- **Endereço**
- **Tipo de cozinha**
- **Horário de funcionamento**
- **Dono do restaurante** (usuário existente)

Operações disponíveis:
- Criar restaurante
- Listar restaurantes
- Buscar restaurante por ID
- Atualizar restaurante
- Excluir restaurante

### ✅ Cadastro de Itens do Restaurante (Cardápio)
- Nome do item
- Descrição
- Preço
- Restaurante associado

---

## 🏗️ Arquitetura do Projeto (Clean Architecture)

O projeto segue os princípios da **Clean Architecture**, garantindo separação de responsabilidades e facilidade de manutenção.

```text
src/main/java
└── br.com.fiap.app.restaurante
    ├── domain
    │   ├── entity
    │   ├── repository
    │   └── valueobject
    ├── application
    │   ├── usecase
    │   └── service
    ├── infrastructure
    │   ├── controller
    │   ├── repository
    │   ├── config
    │   └── persistence
    └── CadastroDeRestauranteApplication.java
```

### 🔹 Camadas

- **Domain**: Entidades, regras de negócio e contratos
- **Application**: Casos de uso e orquestração da lógica
- **Infrastructure**: Controllers, JPA, configurações, banco de dados

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3 / 4**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Docker & Docker Compose**
- **JUnit 5**
- **Mockito**
- **Postman**

---

## 🚀 Como Executar o Projeto

### 📋 Pré-requisitos

- Docker
- Docker Compose
- Java 21+
- Maven

---

### 🐳 Subindo o Banco de Dados com Docker

```bash
docker-compose up -d
```

O container irá subir:
- PostgreSQL
- Banco: `cadastra_restaurante_db`

---

### ▶️ Executando a Aplicação

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 🔌 Endpoints da API (Resumo)

### 📌 Tipo de Usuário
- `POST /api/tipos-usuario`
- `GET /api/tipos-usuario`
- `GET /api/tipos-usuario/{id}`
- `PUT /api/tipos-usuario/{id}`
- `DELETE /api/tipos-usuario/{id}`

### 📌 Usuários
- `POST /api/usuarios`
- `GET /api/usuarios`

### 📌 Restaurantes
- `POST /api/restaurantes`
- `GET /api/restaurantes`
- `GET /api/restaurantes/{id}`
- `PUT /api/restaurantes/{id}`
- `DELETE /api/restaurantes/{id}`

### 📌 Itens do Cardápio
- `POST /api/itens`
- `GET /api/itens`
- `GET /api/itens/{id}`
- `PUT /api/itens/{id}`
- `DELETE /api/itens/{id}`

---

## 🧪 Testes

### ✅ Testes Unitários
- Implementados com **JUnit 5** e **Mockito**
- Cobertura mínima: **80%**

### ✅ Testes de Integração
- Testes com contexto real da aplicação
- Integração com banco PostgreSQL (via Docker)

---

## 📦 Collections para Teste (Postman)

O projeto possui uma **collection do Postman** contendo:
- Fluxo completo de criação de usuários
- Cadastro de tipos de usuário
- Cadastro de restaurantes
- Cadastro de itens do cardápio

📁 A collection está disponível no repositório:

```
/postman/Cadastro-Restaurante.postman_collection.json
```

---

## 📂 Docker Compose

O projeto possui um `docker-compose.yml` configurado para subir:
- PostgreSQL isolado
- Volume persistente exclusivo

---

## 📁 Repositório

Repositório público disponível para avaliação:

```
https://github.com/seu-usuario/cadastro-restaurante
```

---

## ✅ Critérios de Avaliação Atendidos

✔ Funcionalidades solicitadas implementadas  
✔ Endpoints funcionais  
✔ Código organizado e documentado  
✔ Clean Architecture aplicada  
✔ Docker Compose configurado  
✔ Testes unitários e de integração  
✔ Collections de teste disponíveis  
✔ Repositório público acessível  

---

## 👨‍💻 Autor

Projeto desenvolvido para fins acadêmicos, aplicando boas práticas de arquitetura e desenvolvimento backend com Java e Spring Boot.

---

🚀 **Projeto pronto para avaliação e evolução futura**