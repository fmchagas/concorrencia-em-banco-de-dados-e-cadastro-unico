# Supremo cad-unico

Supremo cad-unico é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes) utilizando constraints para validação de dados únicos

### Solução aplicada neste projeto é de constraints
1. https://www.baeldung.com/jpa-unique-constraints
2. https://www.tutorialspoint.com/postgresql/postgresql_constraints.htm
3. https://docs.oracle.com/javaee/7/api/javax/persistence/UniqueConstraint.html
4. https://vladmihalcea.com/a-beginners-guide-to-jpahibernate-flush-strategies/
5. https://vladmihalcea.com/how-do-jpa-and-hibernate-define-the-auto-flush-mode/

### Tecnologias

* Java 17
* Spring Boot
* Bean Validation
* JPA/Hibernate

### Como rodar o projeto:
1. Ter instalado o Docker e Docker Compose;
2. Baixar ou clonar este projeto em sua maquina;
3. Utilizar o terminal na pasta central do projeto e executar o comando:
    1. executar ``` docker-compose up -d``` para subir o container do banco de dados Postgres


### Especificação:

Crie uma API REST para realizar um cadastro de produtos com as restrições abaixo

1. Um produto possui os atributos nome, código e preço(valor);
2. Todos os atributos do produto são obrigatórios;
3. O código do produto possui tamanho máximo de 6 caracteres;
4. O código do produto deve ser único no sistema;

5. Para sua API REST:
   1. Em caso de sucesso, deve-se retornar o Status HTTP 201 (Created);
   2. Em caso de erro de validação, deve-se retornar o Status HTTP 400 (Bad Request);
   3. Em caso de produto duplicado, deve-se retornar o Status HTTP 422 (Unprocessable Entity);

### Consideração

Implementar uma API REST com validação de unicidade não é coisa de outro mundo, basta ficar atento nas validações da entidade, como as lógicas no controller/service(useCase) e principalmente as constraints no banco de dados.

Implementar validação unicidade ao nível de aplicação e principalmente banco de dados costuma ser a solução mais segura e confiável para garantir a consistência dos dados evitando duplicidade.

Capturar e tratar a exceção ConstraintViolationException lançada pelo Hibernate é uma boa prática. E com Spring Boot podemos fazer isto implementando um Controller Advice local ou global

No caso de erros de unicidade, entendemos que a família de Status 4xx seja a mais apropriada, em especial os Status 422 (Unprocessable Entity), 400 (Bad Request) ou 409 (Conflict).


### Unicidade com chave composta
Implementar uma API REST para uma entidade com validação de unicidade com chave composta é muito semelhante a uma chave simples, basta ficar atento nas validações da entidade, como as lógicas no controller e principalmente as constraints no banco de dados.

