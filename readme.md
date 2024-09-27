# Projeto de Gerenciamento de Livros

Este projeto é uma aplicação web que permite gerenciar informações sobre livros, incluindo a capacidade de realizar buscas na API do Google Books. O sistema oferece funcionalidades para listar, adicionar, excluir e recomendar livros com base em categorias.

## Tecnologias Utilizadas

- **Backend**: Java com Spring Boot
- **Banco de Dados**: PostgreSQL
- **APIs**: Google Books API para busca de livros
- **Versionamento de banco de dados**: Flyway

## Funcionalidades

- **Gerenciamento de Livros**: Adicionar, listar, excluir livros e suas informações.
- **Gerenciamento de Usuários**: Adicionar, listar, excluir usuários e suas informações.
- **Gerenciamento de Empréstimos**: Adicionar, listar, excluir empréstimos e suas informações.
- **Recomendação**: Recomendação de livros com base em categorias.
- **Disponibilidade**: Verificação de disponibilidade de livros.
- **Integração com Google Books**: Pesquisa de livros usando a API do Google Books.
- **TDD**: Testes  nas regras de negócios do projeto.

## Como executar o projeto

Necessário uma instancia do PostgreSQL executando na máquina.

Localizar o arquivo `application.yam` e ajustar a conexão do banco de dados de acordo com o banco local.

Abrir terminal na pasta raiz do projeto e executar o seguinte comando `.\gradlew bootRun`.

Também foi deixado na pasta raiz um arquivo chamado `biblioteca.postman_collection` esse ja contem todos os end-points da api mapeados, somente importar o arquivo no postman.

# Arquitetura do CRUD

A arquitetura do CRUD foi projetada de maneira simples e objetiva para atender aos requisitos do projeto. Ao utilizar o processo padrão de CRUD, é necessário seguir alguns passos para garantir a correta implementação e funcionamento da aplicação.

## Estrutura do CRUD

1. **Controller (Controle):**  
   Cada entidade da aplicação deve possuir um controlador, responsável por gerenciar os endpoints da API. Esse controlador deve estender a classe `CrudController`, tipado com a entidade e seu identificador (por exemplo, `CrudController<Entidade, Long>`). O controlador então delega as operações para o serviço correspondente.

2. **Service (Serviço):**  
   Para cada entidade, é necessário criar uma classe de serviço que herde de `CrudService`, também tipada com a entidade e seu identificador. O `CrudService` é responsável por obter a instância do `JpaRepository` da entidade, permitindo que as operações de persistência no banco de dados sejam realizadas de forma transparente.

3. **Repository (Repositório):**  
   Cada entidade deve ter um `JpaRepository` correspondente, definido dentro do contexto da aplicação. O repositório é usado pelo `CrudService` para as operações de persistência, como criação, leitura, atualização e exclusão.

## Processamento de Eventos e Validações

Um ponto essencial desta arquitetura é o sistema de processamento de **eventos** e **validações** associado às entidades. Para cada entidade, é possível definir a anotação `@CrudListener`, que possibilita o seguinte:

- **Validações:**  
  O parâmetro `validacoesBeans` da anotação `@CrudListener` aceita uma lista de classes que implementam a interface `Validacao`. No momento da persistência da entidade, todas as classes de validação são carregadas a partir do contexto e executadas automaticamente, garantindo que as regras de negócios sejam aplicadas.

- **Eventos:**  
  O parâmetro `eventosBeans` aceita uma lista de classes que implementam a interface `Evento`. Durante o ciclo de vida da persistência da entidade, esses eventos são disparados, permitindo que ações adicionais sejam executadas em resposta a mudanças na entidade.

## Considerações Finais

Essa abordagem centralizada para controle, validação e disparo de eventos simplifica o desenvolvimento e a manutenção do sistema, ao mesmo tempo que promove o reaproveitamento de código.

Vale ressaltar que **não foi implementado um controle específico de eventos e validações para persistência de entidades filhas**. O requisito do projeto não demandava esse comportamento, portanto, a arquitetura atual lida apenas com as entidades principais.

