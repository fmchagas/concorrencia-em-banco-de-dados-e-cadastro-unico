# Supremo cad-unico-bilhete
### Cadastrando bilhetes de loteria

Chegou a hora de você pensar em como implementar um cadastro de bilhetes em um sistema de loteria. A ideia é que uma pessoa faça uma aposta informando um "número da sorte" que será registrado no sistema. Portanto, siga as restrições descritas abaixo:

1. Um bilhete tem o nome e telefone celular do jogador, sorteio, número da sorte e uma data de registro;
2. Todos os campos são obrigatórios;
3. Um sorteio tem descrição do prêmio e data do sorteio:
   1. todos os campos do sorteio são obrigatórios;
   2. a descrição tem tamanho máximo de 150 caracteres;
   3. a data do sorteio deve estar no futuro;
4. O número da sorte deve estar no intervalo de 1 a 9999;
5. O telefone celular do jogador possui o formato "^\\+[1-9][0-9]\\d{1,14}" (ex: "+5511987654321");
6. O telefone **celular** do jogador **não pode ser armazenado aberto** no sistema;
7. O telefone **celular** do jogador, **número da sorte** e **sorteio** devem ser **únicos**;

8. Para sua API REST:
   1. Em caso de sucesso, deve-se retornar o Status HTTP 201 (Created);
   2. Em caso de erro de validação, deve-se retornar o Status HTTP 400 (Bad Request);
   3. Em caso de produto duplicado, deve-se retornar o Status HTTP 422 (Unprocessable Entity);

### Texto apoio - Hash
1. https://reflectoring.io/creating-hashes-in-java
2. https://www.baeldung.com/sha-256-hashing-java

### Texto apoio - Data masking
1. https://satoricyber.com/data-masking/data-masking-8-techniques-and-how-to-implement-them-successfully

### Consideração
