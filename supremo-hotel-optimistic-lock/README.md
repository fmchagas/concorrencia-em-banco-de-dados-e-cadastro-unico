# Supremo hotel - Sistema de reserva de quartos

Supremo hotel é projeto para resolver o problema de concorrência em banco de dado SQL(Atualizações Concorrentes)

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
    1. executar ``` docker-compose up -d``` para subir o container do banco de dados Postgres


### Reservando um Quarto de Hotel

Supremo Hotel é especialista em alocação para famílias no país, por ser um dos hotels mais tradicionais é muito comum que a concorrência por um quarto seja altíssima. Pensando nisso o Supremo Hotel contratou você para implementar o sistema de reservas de quartos que seja, capaz de lidar o cenário de concorrência.

### Especificação:

1. Na entidade Quarto tem os seguintes atributos:
   1. Descrição com no máximo 200 caracteres.
   2. Valor da diária.
   3. Cama, que podem ser de solteiro ou casal.
   4. Flag, dizendo se a reserva esta ativa ou não.

2. Na entidade Reserva:
   1. Quarto que será reservado.
   2. Data check-in
   3. Data check-out
   4. Data e Hora do instante da reserva.

3. Para sua API REST:
   1. Id do quarto deve ser informado no caminho da requisição.
   2. Caso o quarto não esteja cadastrado deve ser retornado junta a resposta um Status HTTP 404 (Not Found).
   3. Caso o quarto esteja com reserva ativa, deverá ser retornado junto a resposta um Status HTTP 422 (Unprocessable Entity).
   4. O sistema deve conseguir lidar com situações de conflito de reserva concorrente.
   5. Em caso de sucesso, deverá ser criado uma reserva para este quarto, e a flag que determina se o quarto está reservado ou não deverá ser atualizada.
   6. Caso a reserva seja feito deve ser retornado junto a resposta um Status HTTP 201 (Created) e o cabeçalho de location com URI do recurso criado.