# ForumHub API

API REST desenvolvida como parte do challenge **ForumHub** da Alura.

O projeto simula o backend de um fórum, permitindo cadastro, listagem, atualização e exclusão de tópicos, além de autenticação de usuários.

## 🚀 Tecnologias utilizadas

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Banco de dados H2
* Maven

## 📌 Funcionalidades

* Cadastro de usuários
* Autenticação com JWT
* Criar tópico
* Listar tópicos
* Atualizar tópico
* Excluir tópico

## 🔐 Autenticação

A autenticação é feita utilizando **JWT**.

Para acessar os endpoints protegidos, é necessário:

1. Fazer login
2. Receber o token JWT
3. Enviar o token no header das requisições

Exemplo:

Authorization: Bearer SEU_TOKEN

## 📚 Endpoints principais

### Login

POST /login

### Criar tópico

POST /topicos

### Listar tópicos

GET /topicos

### Atualizar tópico

PUT /topicos/{id}

### Deletar tópico

DELETE /topicos/{id}

## ⚙️ Como executar o projeto

1. Clone o repositório

git clone https://github.com/vitoriasouzadev/forumhub-api

2. Entre na pasta do projeto

cd forumhub-api

3. Execute o projeto

./mvnw spring-boot:run

## 🗄 Banco de dados

O projeto utiliza o **H2 Database** para testes.

Console H2:

http://localhost:8080/h2-console

## 👩‍💻 Autora

Projeto desenvolvido por **Vitoria Souza** durante o programa **Oracle Next Education + Alura**.
