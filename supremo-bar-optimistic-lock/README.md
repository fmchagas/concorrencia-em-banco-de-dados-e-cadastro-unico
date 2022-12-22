# Supremo bar - Sistema de reserva de mesas

Supremo bar é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes)

### Solução aplicada neste projeto é de Optimistic Lock
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
    1. executar ``` docker-compose up -d ``` para subir o container do banco de dados Postgres