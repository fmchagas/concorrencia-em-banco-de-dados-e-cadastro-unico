# Supremo rede social - Sistema de rede social

Supremo rede social é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes) com a atualização de atributos distintos de uma entidade usando a estratégia de adicionar uma nova entidade versionada

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


### Reservando um Quarto de Hotel

Aprenda a evoluir o modelo de domínio para atualizar atributos distintos de uma entidade usando Lock Otimista, passos mínimos necessários para evolução do modelo de domínio

### Especificação:

1. Na entidade Perfil:
   1. @Verison int versao
   2. private int quantidadeDeFan — criar entidade com relacionamento um para um(QuantidadeFanPerfil, QuantidadeDeFan)

2. Na entidade QuantidadeDeFan(ou QuantidadeFanPerfil):
   1. ter o atributo long quantidade
   2. ter o @Verison int versao