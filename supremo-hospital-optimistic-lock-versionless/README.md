# Supremo hospital - Sistema de reserva de leitos

Supremo hospital é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes)

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

O Hospital Saint Germain esta com um problema de reserva de leitos, no momento de reserva diversas vezes uma reserva esta sendo sobreescrita. Como o sistema foi desenvolvido a muito tempo, alterar o schema do banco de dados é algo que pode comprometer diversas areas. Então entende-se que é necessário implementar um mecanismo de detecção de conflitos para manter a produtividade das transações, porém, não é possível realizar alterações no dominio da aplicação.
Seu papel é implementar a funcionalidade de reserva de Leito, garantindo que o Status, a data e hora de reserva (atualizadoEm) sejam utilizados como estado de versão da entidade.

1. Restrições:
   1. Por questões e performance, é proibido o uso de lock pessimista;
   2. Não é permitido alterar o Schema da entidade Leito;
   3. Deve ser utilizado todos os campos da entidade como atributo de versão;
   4. O identificador do leito deve ser utilizado no path da requisição;
   5. Caso o leito não esteja livre é necessário retor um HTTP STATUS 422 (Unprocessable Entity);