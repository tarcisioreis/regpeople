# API RegPeople - Cadastro de pessoas

Projeto de API para cadastro de dados pessoas como CPF, Nome, Data de Nascimento, E-mail e etc...

Descrição:

Api desenvolvida em Spring Boot persistindo dados com JPA Hibernate em banco de dados MySQL, estrutura baseada em 3 camadas: Controller, Service e Repository, sendo a camada de banco Entity não sendo acessada diretamente mas, usando acesso via DTO(Data Transfer Object) pela camada Service.

Métodos implementados:

PessoaController:

Operações Básicas:

list - via GET - Listagem de Todas as pessoas cadastradas - mostra id, nome, cpf, data de nascimento, nacionalidade, naturalidade, e-mail e sexo(0 - Masculino e 1 - Feminino);

create - via POST - parâmetro objeto DTO pessoaDTO - inclui nome(Obrigatório), cpf(Obrigatório), data de nascimento(Obrigatório), nacionalidade(Não Obrigatório), naturalidade(Não Obrigatório), e-mail(Não Obrigatório) e sexo(0 - Masculino e 1 - Feminino) - validação dos campos obrigatórios, no caso do email se informado válida, formata CPF, verifica duplicidade de CPF e valida a validade do CPF(usamos o site https://www.4devs.com.br/gerador_de_cpf para gerar cpfs de testes);

update - via PUT - parâmetro objeto DTO pessoaDTO - inclui id(Chave Obrigatória), nome(Obrigatório), cpf(Obrigatório), data de nascimento(Obrigatório), nacionalidade(Não Obrigatório), naturalidade(Não Obrigatório), e-mail(Não Obrigatório) e sexo(0 - Masculino e 1 - Feminino) - validação dos campos obrigatórios, no caso do email se informado válida, formata CPF, verifica duplicidade de CPF e valida a validade do CPF(usamos o site https://www.4devs.com.br/gerador_de_cpf para gerar cpfs de testes);

delete - via DELETE - parâmetro id do cadastro da pessoa - verifica se existe o dado informado e após isso faz DELETE nos dados(exclusão fisica);

Buscas:

findByCpf - via POST - parâmetro cpf - formata CPF, verifica a sua existência, retorno os dados da pessoa encontrada;

findByName - via POST - parâmetro nome - verifica a sua existência, retorno os dados da(s) pessoa(s) encontrada(s);

findByNatural - via POST - parâmetro naturalidade - verifica a sua existência, retorno os dados da(s) pessoa(s) encontrada(s);

findByNationality - via POST - parâmetro nacionalidade - verifica a sua existência, retorno os dados da(s) pessoa(s) encontrada(s);

findByEmail - via POST - parâmetro nacionalidade - verifica a sua existência, retorno os dados da pessoa encontrada;

findByBirthDate - via POST - parâmetro dtNascimento(formato yyy-MM-dd) - verifica a sua existência, retorno os dados da pessoa encontrada;

Requisitos para funcionamento:

Ter o Eclipse/Intellij como IDE com Maven para isso somente importar como Projeto Maven;

Ter um banco MySQL local ou remoto, configurar no diretório src/main/resources/application.properties os dados para aceso ao banco.

Para ver os Web Services instalei o SwaggerUI para interagir com a API.

Projeto pode ser testado online no endereço: https://apiregpeople.herokuapp.com/swagger-ui.html

Contato: Tarcisio Machado dos Reis - e-mail: tarcisio.reis.ti@gmail.com ou whatsapp 051 9 8490-4355.
