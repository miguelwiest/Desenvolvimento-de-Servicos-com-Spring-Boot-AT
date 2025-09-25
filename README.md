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
