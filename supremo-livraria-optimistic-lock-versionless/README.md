# Supremo livraria - Sistema de reservas de livros

Supremo livraria-reserva de livros é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes)

### Solução aplicada neste projeto é de Optimistic Lock - versionless(sem versão)
``` Anotação aplicada a nivel de classe na Entity: @DynamicUpdate @OptimisticLocking(type = OptimisticLockType.ALL) ```

### Links para aprofundamento
* https://vladmihalcea.com/how-to-prevent-optimisticlockexception-using-hibernate-versionless-optimistic-locking/

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

Corrigir um bug no Supremo livraria que implementa uma reserva de livros.


Você foi designado para implementar um mecanismo para controlar essa simultaneidade de requisições nas reservas de livros.

1. Para realizar esta atividade:
   1. Simule o problema localmente na sua máquina;
   2. Implemente a correção do bug existente na reserva de livros;
   
2. Restrições:
   1. Por questões e desempenho, é proibido o uso de lock pessimista;
   2. Não é permitido alterar o Schema da entidade(não alterar o schema do banco de dados);
   3. Deve ser utilizado todos os campos da entidade para formar o atributo de versão;

3. Para sua API REST:
   1. Se a reserva ocorrer com sucesso, deve-se retornar o status HTTP 204;
   2. Se o livro não existir no banco de dados, deve-se retornar o status HTTP 404;
   3. Se não for possível fazer a reserva, deve-se retornar o status HTTP 422;