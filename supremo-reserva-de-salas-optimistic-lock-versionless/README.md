# Supremo reserva sala - Sistema de reservas de salas

Supremo reserva sala é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes)

### Solução aplicada neste projeto é de Optimistic Lock - versionless(sem versão)
``` Anotação aplicada a nivel de classe na Entity: @DynamicUpdate @OptimisticLocking(type = OptimisticLockType.DIRTY) ```

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

A Fmchagas conta com diversos escritórios ao redor do Brasil, em Jataí-GO é onde tem o maior número de funcionários visitantes, então existe uma grande disputa por salas. A Fmchagas conta com um sistema legado para realizar a reserva destas salas, porém, dado ao alto número de requisições disputando por salas a integridade dos dados esta sendo comprometida.


Você foi designado para implementar um mecanismo para controlar essa simultaneidade de requisições pelas entidades referentes as salas.

1. Para realizar esta atividade:
   1. Simule o problema localmente na sua máquina;
   2. Implemente a correção do bug existente na reserva de salas;
   
2. Restrições:
   1. Por questões e desempenho, é proibido o uso de lock pessimista;
   2. Não é permitido alterar o Schema da entidade Sala(ou seja, adicionar um atributo para controlar versão);
   3. Deve utilizados como atributo de versão da entidade apenas os campos atualizados;
   4. Caso a Sala esteja OCUPADA retornar um HTTP Status 422 (Unprocessable Entity) junto a Resposta.