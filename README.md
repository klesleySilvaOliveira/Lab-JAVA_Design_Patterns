# Lab JAVA Design Patterns

![Java](https://img.shields.io/badge/Java-25-blue?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen?style=for-the-badge&logo=springboot)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-ORM-green?style=for-the-badge)
![OpenFeign](https://img.shields.io/badge/OpenFeign-HTTP%20Client-orange?style=for-the-badge)
![H2](https://img.shields.io/badge/H2-Database-lightgrey?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger)

## Sobre o projeto

Este projeto foi desenvolvido como parte de um desafio prático de **Padrões de Projeto com Java e Spring**, com o objetivo de aplicar conceitos de **Design Patterns** em uma API REST simples, funcional e integrada a um serviço externo.

A aplicação permite o cadastro, consulta, atualização e exclusão de clientes. Durante o cadastro ou atualização, o sistema recebe um **CEP**, consulta automaticamente os dados de endereço por meio da API pública **ViaCEP** e calcula o valor e o prazo de entrega conforme o tipo de entrega escolhido.

Além da construção da API, o projeto explora três padrões de projeto estudados no laboratório:

- **Singleton**
- **Strategy**
- **Facade**

A proposta foi manter o projeto simples, mas com uma estrutura clara o suficiente para demonstrar os padrões de forma prática em uma aplicação Spring Boot.

---

## Objetivos do desafio

O desafio tinha como proposta consolidar o aprendizado sobre padrões de projeto, permitindo reproduzir e evoluir os exemplos trabalhados em aula ou criar uma nova implementação aplicando os conceitos estudados.

Neste projeto, a ideia escolhida foi criar uma API de cadastro de clientes com:

- integração externa com a API ViaCEP;
- uso de DTOs para entrada e saída de dados;
- persistência em banco H2 com Spring Data JPA;
- documentação com Swagger/OpenAPI;
- aplicação dos padrões Singleton, Strategy e Facade.

---

## Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Cloud OpenFeign
- H2 Database
- Swagger/OpenAPI com Springdoc
- Maven

---

## Funcionalidades

A API possui as seguintes funcionalidades principais:

- Consultar endereço a partir de um CEP;
- Cadastrar cliente com endereço preenchido automaticamente;
- Calcular valor e prazo de entrega conforme o tipo de entrega;
- Listar clientes de forma paginada;
- Buscar cliente por ID;
- Atualizar dados de um cliente;
- Remover cliente por ID;
- Acessar documentação interativa via Swagger UI;
- Utilizar banco H2 em memória para testes locais.

---

## Padrões de projeto aplicados

### Singleton

O padrão **Singleton** foi aplicado de duas formas no projeto.

A primeira ocorre naturalmente pelo próprio Spring, que gerencia classes anotadas como `@Service`, `@RestController`, `@Repository` e clientes Feign como beans reutilizáveis no contexto da aplicação.

A segunda forma aparece nas estratégias de entrega. Cada implementação da estratégia possui uma instância única acessada por meio do método `getInstance()`:

```java
private static FastDeliveryImpl instance = new FastDeliveryImpl();

public static FastDeliveryImpl getInstance() {
    return instance;
}
```

Essas instâncias são associadas ao enum `DeliveryType`, permitindo que cada tipo de entrega conheça sua respectiva estratégia de cálculo.

---

### Strategy

O padrão **Strategy** foi utilizado para separar as regras de cálculo de entrega.

A interface `DeliveryStrategy` define o contrato comum:

```java
public interface DeliveryStrategy {
    DeliveryDataDTO calculate(String uf);
}
```

As implementações concretas representam diferentes formas de cálculo:

- `EconomicDeliveryImpl`
- `FastDeliveryImpl`
- `NoDeliveryImpl`

Com isso, o sistema evita concentrar todas as regras de entrega em uma única estrutura condicional dentro do service. Cada tipo de entrega possui sua própria classe responsável pelo cálculo.

---

### Facade

O padrão **Facade** aparece na camada de serviço, especialmente no fluxo de cadastro e atualização de clientes.

O controller não precisa conhecer diretamente todos os detalhes envolvidos na operação. Ele delega a chamada para o `ClientService`, que concentra a orquestração do processo:

1. Recebe os dados enviados pelo usuário;
2. Identifica o tipo de entrega;
3. Consulta o endereço no ViaCEP por meio do `CepService`;
4. Calcula o valor e o prazo da entrega usando a Strategy correspondente;
5. Copia os dados para a entidade `Client`;
6. Persiste os dados no banco usando o repository;
7. Retorna um DTO de resposta.

Esse comportamento simplifica a interface exposta ao controller e esconde os detalhes de integração, cálculo e persistência.

---

## Regras de entrega

O projeto possui três tipos de entrega disponíveis.

| Tipo de entrega | Descrição |
|---|---|
| `FAST_DELIVERY` | Entrega rápida, com menor prazo e maior custo |
| `ECONOMIC_DELIVERY` | Entrega econômica, com prazo maior e custo reduzido |
| `NO_DELIVERY` | Sem entrega, representando retirada ou entrega sem cobrança |

### Entrega rápida

| UF | Valor | Prazo |
|---|---:|---:|
| MG | R$ 19,90 | 1 dia |
| SP, RJ ou ES | R$ 29,90 | 2 dias |
| Demais estados | R$ 39,90 | 4 dias |

### Entrega econômica

| UF | Valor | Prazo |
|---|---:|---:|
| MG | R$ 9,90 | 3 dias |
| SP, RJ ou ES | R$ 14,90 | 5 dias |
| Demais estados | R$ 24,90 | 8 dias |

### Sem entrega

| Valor | Prazo |
|---:|---:|
| R$ 0,00 | 0 dias |

---

## Endpoints da API

### Clientes

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/clients` | Lista clientes cadastrados com paginação |
| `GET` | `/clients/{id}` | Busca cliente por ID |
| `POST` | `/clients` | Cadastra um novo cliente |
| `PUT` | `/clients/{id}` | Atualiza os dados de um cliente |
| `DELETE` | `/clients/{id}` | Remove um cliente por ID |

### Endereços

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/address/{cep}` | Consulta os dados de endereço de um CEP |

---

## Exemplos de uso

### Cadastrar cliente

**Requisição**

```http
POST /clients
Content-Type: application/json
```

```json
{
  "nome": "Klesley Silva de Oliveira",
  "cep": "38405082",
  "tipoEntrega": "FAST_DELIVERY"
}
```

**Resposta esperada**

```json
{
  "id": 1,
  "nome": "Klesley Silva de Oliveira",
  "cep": "38405082",
  "logradouro": "Rua Exemplo",
  "bairro": "Bairro Exemplo",
  "complemento": "",
  "cidade": "Uberlândia",
  "uf": "MG",
  "tipoEntrega": "FAST_DELIVERY",
  "valorEntrega": 19.9,
  "prazoDias": 1
}
```

> Os dados de endereço retornados podem variar de acordo com o CEP consultado na API ViaCEP.

---

### Listar clientes com paginação

```http
GET /clients?page=0&size=10&sort=id,asc
```

Também é possível ordenar por outros campos da entidade, como:

```http
GET /clients?page=0&size=10&sort=nome,asc
```

```http
GET /clients?page=0&size=10&sort=valorEntrega,desc
```

> Observação: no Swagger, não utilize o exemplo genérico `"string"` no campo `sort`, pois ele não representa um atributo real da entidade. Use campos como `id`, `nome`, `uf`, `tipoEntrega`, `valorEntrega` ou `prazoDias`.

---

### Buscar cliente por ID

```http
GET /clients/1
```

---

### Atualizar cliente

```http
PUT /clients/1
Content-Type: application/json
```

```json
{
  "nome": "Klesley Oliveira",
  "cep": "38405082",
  "tipoEntrega": "ECONOMIC_DELIVERY"
}
```

---

### Deletar cliente

```http
DELETE /clients/1
```

---

### Consultar endereço por CEP

```http
GET /address/38405082
```

---

## Documentação com Swagger

A API possui documentação interativa com Swagger/OpenAPI.

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

A documentação permite visualizar e testar os endpoints diretamente pelo navegador.

---

## Banco de dados H2

O projeto utiliza banco H2 em memória para facilitar os testes locais.

Após iniciar a aplicação, o console do H2 pode ser acessado em:

```text
http://localhost:8080/h2-console
```

Configurações padrão:

```text
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: 
```

O projeto também possui um arquivo `import.sql` com dados iniciais para popular a tabela de clientes durante a execução local.

---

## Como executar o projeto

### Pré-requisitos

Antes de começar, é necessário ter instalado:

- Java 25 ou versão compatível com o projeto;
- Git;
- Maven, caso não utilize o Maven Wrapper.

### Clonar o repositório

```bash
git clone https://github.com/klesleySilvaOliveira/Lab-JAVA_Design_Patterns.git
```

```bash
cd Lab-JAVA_Design_Patterns
```

### Executar com Maven Wrapper

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

### Executar com Maven instalado

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

---

## Estrutura geral do projeto

```text
src/main/java/one/digitalinnovation/gof
├── config
│   └── OpenApiConfig.java
├── controller
│   ├── AddressController.java
│   └── ClientController.java
├── model
│   ├── dto
│   │   ├── ClientRequestDTO.java
│   │   ├── ClientResponseDTO.java
│   │   ├── DeliveryDataDTO.java
│   │   └── ViaCepResponseDTO.java
│   ├── entity
│   │   ├── Client.java
│   │   ├── DeliveryType.java
│   │   └── strategy
│   │       ├── DeliveryStrategy.java
│   │       └── impl
│   │           ├── EconomicDeliveryImpl.java
│   │           ├── FastDeliveryImpl.java
│   │           └── NoDeliveryImpl.java
│   └── repository
│       └── ClientRepository.java
├── service
│   ├── CepService.java
│   └── ClientService.java
└── service/client
    └── ViaCepClient.java
```

---

## Fluxo de cadastro de cliente

O fluxo principal da aplicação pode ser resumido da seguinte forma:

```text
ClientController
      ↓
ClientService
      ↓
CepService
      ↓
ViaCepClient
      ↓
API ViaCEP
```

Depois da consulta ao ViaCEP, o `ClientService` utiliza o `DeliveryType` para acessar a estratégia correspondente e calcular o valor e o prazo da entrega.

```text
DeliveryType
      ↓
DeliveryStrategy
      ↓
FastDeliveryImpl / EconomicDeliveryImpl / NoDeliveryImpl
```

---

## Principais aprendizados

Durante o desenvolvimento deste projeto, foram praticados conceitos importantes do ecossistema Java/Spring, como:

- criação de API REST com Spring Boot;
- separação de responsabilidades entre controller, service, repository, DTO e entity;
- integração com API externa usando OpenFeign;
- documentação da API com Swagger/OpenAPI;
- persistência com Spring Data JPA;
- uso de banco H2 para testes locais;
- implementação prática dos padrões Singleton, Strategy e Facade;
- uso de paginação com `Pageable`;
- tratamento básico de validação de CEP;
- inicialização de dados com `import.sql`.

---

## Possíveis melhorias futuras

Algumas melhorias que podem ser adicionadas futuramente:

- Criar uma camada específica de tratamento de exceções com `@ControllerAdvice`;
- Adicionar validações com Bean Validation, como `@NotBlank`, `@Pattern` e `@NotNull`;
- Tratar CEP inexistente retornado pela API ViaCEP;
- Criar testes automatizados para services e controllers;
- Criar uma classe específica de Facade, separando ainda mais a orquestração do `ClientService`;
- Substituir o Singleton manual das estratégias por beans gerenciados com `@Component`;
- Criar perfis separados para desenvolvimento, teste e produção;
- Adicionar Dockerfile para facilitar a execução da aplicação.

---

## Autor

Desenvolvido por **Klesley Silva de Oliveira**.

GitHub: [klesleySilvaOliveira](https://github.com/klesleySilvaOliveira)
