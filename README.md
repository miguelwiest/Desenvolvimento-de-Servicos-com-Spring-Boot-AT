# Relatório de Desenvolvimento de Serviços com Spring Boot

## 1. Tipos de Requisições HTTP Suportadas pelo Spring Boot

O Spring Boot, através do Spring MVC, suporta todos os tipos de requisições HTTP padrão. As quatro mais comuns são:

*   **GET:** Utilizada para recuperar recursos. Uma requisição GET é idempotente, o que significa que múltiplas requisições idênticas devem produzir o mesmo resultado. No Spring Boot, a anotação `@GetMapping` é usada para mapear requisições GET para métodos de tratamento específicos.

*   **POST:** Utilizada para criar novos recursos. Uma requisição POST não é idempotente, pois múltiplas requisições criarão múltiplos recursos. No Spring Boot, a anotação `@PostMapping` é usada para mapear requisições POST.

*   **PUT:** Utilizada para atualizar um recurso existente ou criar um novo se ele não existir. É idempotente. No Spring Boot, a anotação `@PutMapping` é usada para mapear requisições PUT.

*   **DELETE:** Utilizada para remover um recurso. Não é idempotente. No Spring Boot, a anotação `@DeleteMapping` é usada para mapear requisições DELETE.

## 2. Semelhanças entre Redis e MongoDB

*   **NoSQL:** Ambos são bancos de dados NoSQL, o que significa que não usam o modelo relacional tradicional de tabelas.
*   **Armazenamento de Documentos:** Ambos podem armazenar dados na forma de documentos, embora o MongoDB seja mais orientado a documentos e o Redis seja um armazenamento de chave-valor que pode armazenar estruturas de dados complexas.
*   **Escalabilidade:** Ambos são projetados para serem altamente escaláveis, permitindo a distribuição de dados em múltiplos servidores.
*   **Flexibilidade de Esquema:** Ambos oferecem um esquema flexível, permitindo que a estrutura dos dados evolua ao longo do tempo.

## 3. Diferenças entre Redis e MongoDB

| Característica | Redis | MongoDB |
| --- | --- | --- |
| **Modelo de Dados** | Chave-valor na memória | Orientado a documentos |
| **Armazenamento** | Principalmente na memória (com persistência opcional em disco) | Baseado em disco (com cache na memória) |
| **Desempenho** | Extremamente rápido para operações de leitura e escrita, ideal para caching | Rápido, mas geralmente mais lento que o Redis devido ao armazenamento em disco |
| **Casos de Uso** | Cache, filas de mensagens, sessões de usuário, leaderboards em tempo real | Aplicações web, gerenciamento de conteúdo, análise de dados, catálogos de produtos |
| **Consultas** | Consultas simples baseadas em chaves | Consultas complexas com agregações, índices secundários e queries geoespaciais |

## 4. Framework de Testes Unitários no Spring Boot

O Spring Boot utiliza o **JUnit** como o framework padrão para testes unitários. O JUnit é um framework de código aberto para a linguagem de programação Java. O Spring Boot se integra perfeitamente com o JUnit para fornecer um ambiente de teste robusto.

**Funcionamento:**

O Spring Boot fornece a dependência `spring-boot-starter-test`, que inclui:

*   **JUnit 5:** A versão mais recente do JUnit para escrever testes.
*   **Spring Test & Spring Boot Test:** Utilitários e anotações para testar aplicações Spring.
*   **AssertJ:** Uma biblioteca de asserções fluentes.
*   **Hamcrest:** Uma biblioteca de matchers para escrever asserções flexíveis.
*   **Mockito:** Um framework de mocking para isolar as unidades de código sob teste.
*   **JSONassert:** Uma biblioteca de asserções para JSON.
*   **JsonPath:** XPath para JSON.

Para escrever um teste unitário, você cria uma classe de teste e anota os métodos de teste com `@Test`. O Spring Boot fornece anotações como `@SpringBootTest` para carregar o contexto completo da aplicação ou `@WebMvcTest` para testar a camada de controller da web. Você pode usar `@MockBean` para mockar dependências e `@Autowired` para injetar beans do contexto do Spring.

#
# Relatório do projeto  

## 1. Criar aplicações web a partir do Spring Boot Initializer

O projeto foi construído utilizando **Gradle**, uma ferramenta de automação de compilação que oferece flexibilidade e desempenho. A escolha pelo Gradle se deu por sua capacidade de gerenciar dependências de forma eficiente e por seus scripts de build concisos e poderosos.

O projeto foi iniciado utilizando o **Spring Initializr** via interface web, o que permitiu uma configuração rápida e fácil do projeto, com a inclusão das dependências necessárias, como Spring Web, Spring Data JPA, H2 Database e Spring Security.

## 2. Desenvolver RESTful APIs com Spring Boot

Foram desenvolvidos endpoints RESTful para todas as funcionalidades solicitadas, utilizando as anotações do Spring MVC para mapear as requisições HTTP:

*   `@GetMapping`: Para listar alunos, disciplinas, alunos aprovados e reprovados.
*   `@PostMapping`: Para cadastrar alunos, disciplinas e alocar alunos em disciplinas.
*   `@PutMapping`: Para atribuir notas aos alunos.
*   `@DeleteMapping`: Para remover alunos e disciplinas

## 3. Implementar persistência de dados com JPA, Redis e MongoDB

O projeto utiliza **Spring Data MongoDB** para a persistência dos dados. A escolha pelo MongoDB se deu por sua flexibilidade e escalabilidade como um banco de dados NoSQL orientado a documentos, o que se adequa bem ao modelo de dados da aplicação.

As entidades do modelo de dados (`Aluno`, `Disciplina`, `Matricula`) foram mapeadas para coleções no MongoDB utilizando as anotações do Spring Data MongoDB, como `@Document`, `@Id`, e `@Indexed`.

## 4. Implementar testes em aplicações SpringBoot

Foram desenvolvidos testes unitários para todos os serviços e controladores da aplicação, utilizando o framework **JUnit 5** e **Mockito**. A cobertura de testes é superior a 80%, garantindo a qualidade e a confiabilidade do software.

A anotação `@SpringBootTest` foi utilizada para carregar o contexto completo da aplicação nos testes, e a anotação `@MockBean` foi utilizada para mockar as dependências e isolar as unidades de código sob teste.

## 5. Implementar medidas de segurança em aplicações Spring Boot

O **Spring Security** foi configurado para proteger a aplicação. Foi implementada a autenticação básica (Basic Auth) para garantir que apenas o professor (usuário autenticado) possa realizar as operações de cadastro, listagem e atribuição de notas.

## 6. Realizar o deploy em aplicações SpringBoot

A aplicação foi empacotada como um arquivo `.jar` executável. Esta abordagem foi escolhida em vez do deploy em uma plataforma de nuvem para evitar a necessidade de um cartão de crédito. O arquivo `.jar` pode ser executado em qualquer ambiente que tenha o Java instalado, utilizando o comando `java -jar nome-do-arquivo.jar`.
