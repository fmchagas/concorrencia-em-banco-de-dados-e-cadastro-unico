# Supremo cad-unico-contato
### Cadastrando contatos únicos em uma empresa

Chegou a hora de você pensar em como implementar um cadastro de contatos em uma empresa seguindo as restrições descritas abaixo:

1. Todo contato cadastrado tem um número de telefone, nome do funcionário responsável, data de cadastro;

2. Um contato deve pertencer a um departamento da empresa;

3. Um departamento tem nome e sigla;
   1. o nome é obrigatório e tem tamanho máximo de 120 caracteres;
   2. a sigla é obrigatória e tem tamanho máximo de 3 caracteres;
   3. a sigla possui somente letras maiúsculas;
   4. a sigla deve ser única no sistema;

4. Todos os atributos do contato são obrigatórios;
5. A data de cadastro do contato deve estar no passado;
6. O número do telefone possui o formato "^\\+[1-9][0-9]\\d{1,14}" (ex: "+5511987654321");
7. O número do telefone e departamento do contato devem ser únicos no sistema;


8. Para sua API REST:

   1. Em caso de sucesso, deve-se retornar o Status HTTP 201 (Created);

   2. Em caso de erro de validação, deve-se retornar o Status HTTP 400 (Bad Request);

   3. Em caso de produto duplicado, deve-se retornar o Status HTTP 422 (Unprocessable Entity);

### Consideração
