# Supremo cad-unico-reclamacoes
### Cadastrando reclamações dos usuários

Chegou a hora de você pensar em como implementar um cadastro de reclamações em um sistema de ouvidoria seguindo as restrições descritas abaixo:

1. Uma reclamação tem nome e e-mail do usuário, telefone celular, texto da reclamação e uma data de registro;
2. Todos os campos são obrigatórios;
3. O e-mail deve ser válido;
4. O texto da reclamação deve ter tamanho máximo de 4000 caracteres;
5. O telefone possui o formato "^\\+[1-9][0-9]\\d{1,14}" (ex: "+5511987654321");
6. O **telefone não pode ser armazenado aberto** no sistema;
7. O **telefone e texto da mensagem** devem ser **únicos** no sistema;

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
Embora possamos utilizar o campo texto diretamente na chave composta de unicidade, é uma prática desencorajada.
Como precisamos somente compara-lo por igualdade, é mais interessante gerar um hash deste campo texto, armazena-lo numa nova coluna na tabela e, por fim, utiliza-lo como uma das chaves da constraint de unicidade.