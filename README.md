# Desafio Fábrica de Software - Back-end

## Descrição
O objetivo do desafio é desenvolver uma aplicação de fluxo de aprovação para compra de material de escritório. Para tal, foram utilizadas as tecnologias, conforme sugerido:
* Angular 11.0.9
* Java (Spring Boot 2.4.5)
* MYSQL 8.0.23

### Configuração de Banco de Dados:
Primeiramente deve-se configurar o banco de dados no arquivo 'application.properties' do projeto. Modifique os seguintes parâmetros conforme sua estrutura:
* spring.datasource.url: URL do banco de dados (Exemplo: jdbc:mysql://localhost:3306/desafio_fabrica?useSSL=false)
* spring.datasource.username: Usuário de acesso ao MYSQL
* spring.datasource.password: Senha do usuário.

Crie o banco de dados fornecido no url com o comando:
```CREATE TABLE desafio_fabrica```

Obs.: Ao iniciar a aplicação, automaticamente será efetuado a criação das tabelas no banco de dados.
