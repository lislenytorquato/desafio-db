# Sobre o projeto
Desafio de CRUD para seleção do programa de capacitação - exclusivo para PCD da empresa DB  

# Desafio
Crie um projeto Java usando Spring Boot que implemente um CRUD para as
entidades Pessoa e Endereço, que têm um relacionamento de um-para-muitos. A
entidade Pessoa deve ter os seguintes campos:

**A entidade Pessoa deve ter os seguintes campos:**
- ID
- Nome - Obrigatório
- Data Nascimento
- CPF - Obrigatório, não pode haver dois iguais na base de dados

**A entidade Endereço deve ter os seguintes campos:**
- ID
- Rua
- Número
- Bairro
- Cidade
- Estado
- CEP

**Requisitos:**
- Listar todas as pessoas e seus respectivos endereços
- Criar uma nova pessoa com um ou mais endereços
- Atualizar os dados de uma pessoa e/ou seu(s) endereço(s)
- Excluir uma pessoa e todos os seus endereços
- Mostrar a Idade da Pessoa

**Obs:** 
* Lembre-se de implementar validações básicas para os campos obrigatórios e
tratamento de exceções.
* Banco de dados H2
* Todas as respostas da API devem ser em JSON

**Será um diferencial se:**
- Testes de integração
- Swagger
- Poder informar qual endereço é o principal da pessoa
- Fizer Paginação ao listar todas as Pessoas
