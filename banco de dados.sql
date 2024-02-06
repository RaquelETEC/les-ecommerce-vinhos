create database ecommerce;
use ecommerce; 

create table cliente(
`cli_id` int NOT NULL AUTO_INCREMENT,
`cli_nome` varchar(100) NOT NULL ,
`cli_email` varchar(100) NOT NULL ,
`cli_senha` varchar(100) NOT NULL ,
`cli_cpf` integer NOT NULL ,
`cli_telefone_tipo` varchar(20) NOT NULL ,
`cli_telefone` varchar(20) NOT NULL ,
`cli_dt_nascimento` date NOT NULL ,
`cli_genero` varchar(15) NOT NULL ,
`cli_obervacoes` varchar(200),
 PRIMARY KEY (`cli_id`)
  );

create table endereco(
`end_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`end_cli_id` int NOT NULL  ,
`end_tipo_residencia` varchar(100) NOT NULL,
`end_tipo_logradouro` varchar(100) NOT NULL,
`end_logradouro` varchar(100) NOT NULL,
`end_numero` varchar(100) NOT NULL,
`end_bairro` varchar(100) NOT NULL,
`end_cep` varchar(100) NOT NULL,
`end_cidade` varchar(100) NOT NULL,
`end_estado` varchar(100) NOT NULL,
`end_pais` varchar(100) NOT NULL,
`end_padrao` varchar(3) NOT NULL
);





