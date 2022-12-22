# Supremo aplicativo - Sistema de download de app

Supremo app é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes) com a atualização de atributos distintos de uma entidade usando a estratégia de adicionar uma nova entidade versionada

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

A loja de aplicativo Supremo app disponibiliza aplicativos educativos desenvolvidos pela equipe para outros funcionários do Supremo.

Existe a necessidade que esta loja seja criada suportando a atualização simultânea em alguns atributos como, quantidade de like e quantidade de downloads.
Dado a esta especificação nesta atividade você deve criar uma API REST que permita realizar o download de um aplicativo.

Para sua API REST:

1. O id do aplicativo deve ser informado;
2. Caso o aplicativo não possua cadastro devera ser retornado junto a resposta um Status HTTP 404 (Not Found);
3. Caso o processamento seja feito com sucesso, deve ser incrementado a quantidade de downloads, também deve ser retornado junto a resposta um Status HTTP 200 (OK) e no corpo deve conter o link do aplicativo.

### Especificação, Um aplicativo hoje é formado pelos seguintes atributos:

1. Na entidade Musica:
   1. Nome, Descrição, Link de armazenamento do aplicativo, quantidade de downloads e quantidade de likes 
   2. @Verison int version
   3. Atributos quantidades de download(baixado) e quantidades de likes — criar entidades com relacionamento um para um

2. Nas novas entidades criadas:
   1. Ter o atributo para quantidade
   2. Ter o @Verison int version
   3. Ter mapeamento um para um