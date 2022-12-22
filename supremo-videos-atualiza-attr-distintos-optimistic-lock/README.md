# Supremo video - Sistema de rede social

Supremo video é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes) com a atualização de atributos distintos de uma entidade usando a estratégia de adicionar uma nova entidade versionada

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


### Supremo video

Diversos usuários criam comentários e atribuem, gostei, não gostei nos videos no Youtube. Implementar um mecanismo de controle de concorrência com um único atributo de versão, pode fazer que em diversas vezes atualizações conflitem ao alterar atributos não sobrepostos.
Dado esta estratégia não é a mais indicada para um cenário como este, seu dever é adaptar o domínio do nosso sistema do Youtube para permitir atualizar a quantidade de visualização de um determinado vídeo paralelo a um atualização de qualquer outro atributo.
Por fim implemente o endpoint de incrementar a quantidade de like (gostei).

### Especificação:

1. Na entidade Video:
   1. @Verison int version
   2. Atributos gostei e naoGostei — criar entidades com relacionamento um para um

2. Nas novas entidades criadas:
   1. Ter o atributo para quantidade
   2. Ter o @Verison int version
   3. Ter mapeamento um para um com mappedBy = "attrMapeado" - @OneToOne(mappedBy = "attrMapeado")
