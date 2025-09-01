

# Leilão Spring

Aplicação web de um leilão desenvolvida com **Java Spring Boot** no backend e **React + Vite** no frontend.

## 📋 Pré-requisitos

Antes de iniciar a aplicação, verifique se possui instalado em sua máquina:

- **Java 17** ou superior (JDK) – necessário para rodar o backend com Spring Boot  
- **Maven** – para compilar e gerenciar dependências do backend  
- **Node.js 18** ou superior – necessário para rodar o frontend com Vite  
- **npm** (ou **yarn**) – gerenciador de pacotes do frontend  
- **Git** – para clonar o repositório  
- **PostgreSQL** – banco de dados utilizado  
- **PGAdmin** *(opcional)* – interface gráfica para gerenciar o PostgreSQL  
- Uma **IDE** – recomendável **VSCode** pela facilidade de manipular frontend e backend juntos  

> ⚠️ Certifique-se também de configurar as variáveis de ambiente necessárias para a conexão com o banco.

---

## 🚀 Iniciando

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/mzagury25/NewFinalLeilaoSpring.git
```

### 2️⃣ Configuração do banco de dados

Este projeto utiliza PostgreSQL:

Crie um banco de dados com o nome definido no application.properties do backend.

Ajuste usuário, senha e porta no mesmo arquivo, caso necessário

3️⃣ Rodando o backend

Acesse a pasta backend e então:
```
mvn clean install
mvn spring-boot:run
```
O backend está disponivel em:
```http://localhost:8080```

4️⃣ Rodando o frontend

Acesse a pasta frontend e então:
```
npm install
npm run dev

```

O frontend está disponivel em:
```http://localhost:5173```

## 🛠 Stack utilizada

**Frontend:**

- React  
- Vite  
- JavaScript/TypeScript *(dependendo do projeto)*  
- Axios *(requisições HTTP)*  
- CSS Modules / TailwindCSS *(se aplicável)*  

**Backend:**

- Java 17+  
- Spring Boot  
- Spring Data JPA  
- Spring Web  
- PostgreSQL Driver  

**Banco de Dados:**

- PostgreSQL  

**Ferramentas de Desenvolvimento:**

- Git  
- Maven  
- Node.js / npm  
- VSCode  
