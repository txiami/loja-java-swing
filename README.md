# Projeto Java Swing + JDBC

Este é um projeto Java que demonstra a integração entre uma aplicação Java Swing e um banco de dados utilizando JDBC (Java Database Connectivity).

## Pré-requisitos
Antes de começar, certifique-se de ter os seguintes requisitos instalados:

Java Development Kit (JDK) - Versão 11 ou superior
IntelliJ IDEA ou Eclipse IDE - Você pode escolher entre essas IDEs para executar o projeto.
Configuração do Projeto
Clone o repositório para a sua máquina local:

```sh
git clone https://github.com/txiami/loja-java-swing.git
```
Abra o projeto no IntelliJ IDEA ou no Eclipse.

Configuração do Banco de Dados
Antes de executar o projeto, é necessário configurar o banco de dados. Você pode usar o MySQL, PostgreSQL ou outro banco de dados compatível com JDBC.
```
CREATE DATABASE loja;

USE loja;

CREATE TABLE Cliente (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    dtCadastroCliente DATETIME NOT NULL
);

CREATE TABLE Produto (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Descricao VARCHAR(255) NOT NULL,
    Preco DECIMAL(10, 2) NOT NULL
);



CREATE TABLE Item (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    PedidoId INT NOT NULL,
    ProdutoId INT NOT NULL,
    Preco DECIMAL(10, 2) NOT NULL,
    Quantidade INT NOT NULL,
    FOREIGN KEY (PedidoId) REFERENCES Pedido(Id),
    FOREIGN KEY (ProdutoId) REFERENCES Produto(Id)
);



CREATE TABLE Pedido (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    dtCadastroPedido DATETIME NOT NULL,
    ClienteId INT NOT NULL,
    FOREIGN KEY (ClienteId) REFERENCES Cliente(Id)
);


```

Configure as credenciais de acesso ao banco de dados no seu código Java.
Adicionando Manualmente o Driver JDBC
Se você não estiver usando Maven ou Gradle para gerenciar as dependências do projeto, pode adicionar manualmente o driver JDBC.

## No IntelliJ IDEA:

Baixe o driver JDBC para o seu banco de dados.
No IntelliJ IDEA, clique com o botão direito do mouse no projeto e selecione Open Module Settings.
Na janela do projeto, vá para Libraries.
Clique no botão + e adicione o arquivo JAR do driver JDBC baixado.
## No Eclipse:

Baixe o driver JDBC para o seu banco de dados.
No Eclipse, clique com o botão direito do mouse no projeto e selecione Build Path > Configure Build Path.
Na guia Libraries, clique em Add External JARs e adicione o arquivo JAR do driver JDBC baixado.
Executando o Projeto no IntelliJ IDEA
Abra o IntelliJ IDEA e abra o projeto.
Certifique-se de ter configurado corretamente o driver JDBC no projeto.
Execute a aplicação Java.
Executando o Projeto no Eclipse
Abra o Eclipse e importe o projeto.
Certifique-se de ter adicionado corretamente o driver JDBC ao projeto.
Execute a aplicação Java.



Espero que este README atualizado seja útil para os usuários entenderem como usar o seu projeto Java Swing + JDBC!
