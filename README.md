<h1> Introdução </h1>

A aplicação SGRHU tem como finalidade o controle e a distribuição de refeições dentro do refeitório do Hospital Universitário de Sergipe. Por meio dela, é possível distribuir tickets de refeições para pacientes, acompanhantes e servidores da localidade além de disponibilizar dados estatísticos do consumo destas refeições.

<h1> Desenvolvedores </h1>

* Adam Araújo **@adamaraujo**
* Bárbara Santana **@barbaramsantana**

<h1> Orientadora </h1>

* Adicinéia Aparecida de Oliveira

<h1> Backend </h1>

O backend da aplicação está disponível no Heroku, plataforma na nuvem responsável pelo deploy, através do link https://sgrhubackend.herokuapp.com/

<h2> Acesso ao banco de dados da aplicação </h2>

Caso necessite acessar o banco de dados da aplicação, basta criar a conexão no seu software de gerenciamento de bancos desejado (ex.: pgAdmin), utilizando as seguintes credenciais:

```
Host: ec2-18-215-99-63.compute-1.amazonaws.com
Database: d4662a56318bcn
User: iqtbmrbpkdpmfb
Port: 5432
Password: 108fb533e589b0f461692a3997dfda2523172ea737971183b5d2bacb5d04e67a
```

<h2> Mapeamento das rotas para requisições </h2>

<h3>Usuários</h3>

```
/usuarios (GET) - retorna todos os usuários cadastrados no software
/usuarios/new (POST) - adiciona um novo usuário
/usuarios/{id} (GET) - retorna um usuário específico por meio do seu id
/usuario/{id} (PUT) - altera dados de um usuário específico por meio do seu id
/usuario/{id} (DELETE) - remove um usuário específico por meio do seu id
```

<h3>Autorizações</h3>

```
/autorizacoes (GET) - retorna todas as autorizações cadastradas
/autorizacoes/new (POST) - adiciona uma nova autorização
/autorizacoes/{id} (GET) - retorna uma autorização específica por meio do seu id
/autorizacoes/{id} (PUT) - altera dados de uma autorização específica por meio do seu id
/autorizacoes/{id} (DELETE) - remove uma autorização específica por meio do seu id
```

<h3>Tickets</h3>

```
/tickets (GET) - retorna todos os tickets cadastrados
/tickets/consulta/paciente/{cpf} (GET) - retorna o direito ao ticket de um paciente
/tickets/consulta/autorizacao/{cpf} (GET) - retorna o direito ao ticket de um cliente por autorização
/tickets/consulta/colaborador/{cpf} (GET) - retorna o direito ao ticket de um colaborador
/tickets/consulta/acompanhante/{cpf} - retorna o direito ao ticket de um acompanhante
```
