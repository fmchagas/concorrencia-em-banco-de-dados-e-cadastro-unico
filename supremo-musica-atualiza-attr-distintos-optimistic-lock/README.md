# Supremo música - Sistema de payer de música

Supremo música é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes) com a atualização de atributos distintos de uma entidade usando a estratégia de adicionar uma nova entidade versionada

### Solução aplicada neste projeto é de Optimistic Lock

``` Anotação aplicada na Entity: @Version com um atributo do tipo int/Integer, para cada atributo que deve ser atualizado concorentemente criado uma nova Entidade com mapeamento one to one ```

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


### Supremo musica

No 'player' de música Supremo Deezer atualmente tem-se o problema de atualização de informações de uma Música. A música conta com número de ouvintes e quantidade de likes, sempre que uma música é ouvida e a quantidade de likes tenta ser incrementada em simultâneo, causa a perca de uma destas atualizações. A equipe do projeto precisa que essas informações sejam alteradas paralelamente sem que uma interfira na outra, e você foi determinado a resolver este problema.

### Especificação:

1. Na entidade Musica:
   1. @Verison int version
   2. Atributos quantidadeOuvinte e quantidadeLike — criar entidades com relacionamento um para um

2. Nas novas entidades criadas:
   1. Ter o atributo para quantidade
   2. Ter o @Verison int version
   3. Ter mapeamento um para um
