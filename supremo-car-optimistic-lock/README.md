# Supremo car - Sistema de reserva de carro

Supremo car é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes)

### Solução aplicada neste projeto é de Pessimist Lock
``` Anotação aplicada na Entity: @Version com um atributo do tipo int/Integer ```

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
1. Deve-se reservar um carro por sua chave de identificação (ID);
   1. O ID do carro informado deve ser obrigatório;
   
2. Por questões de performance é proibido utilizar o lock pessimista;

3. Para sua API REST:
   1. Se a reserva ocorrer com sucesso, deve-se retornar o status HTTP 200;
   2. Se o registro não existir no banco de dados, deve-se retornar o status HTTP 404;
   3. Se o carro já estiver reservado para outro cliente, deve-se retornar o status HTTP 422;