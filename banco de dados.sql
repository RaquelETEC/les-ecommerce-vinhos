create database ecommerce;
use ecommerce; 

create table cliente(
`cli_id` int NOT NULL AUTO_INCREMENT,
`cli_nome` varchar(100) NOT NULL ,
`cli_email` varchar(100) NOT NULL ,
`cli_senha` varchar(100) NOT NULL ,
`cli_cpf` varchar(12) NOT NULL ,
`cli_telefone_tipo` varchar(20) NOT NULL ,
`cli_telefone` varchar(20) NOT NULL ,
`cli_dt_nascimento` date NOT NULL ,
`cli_genero` varchar(15) NOT NULL ,
`cli_status` varchar(15) NOT NULL ,
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
`end_padrao` varchar(3) NOT NULL,
`end_observacoes` varchar(200)
);

create table cartao_de_credito(
`cart_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`cart_numero` varchar(20) NOT NULL  ,
`cart_nome` varchar(100) NOT NULL,
`cart_id_bandeira` INT NOT NULL,
`cart_cod_seguranca` integer NOT NULL,
FOREIGN KEY (`cart_id_bandeira`) REFERENCES bandeiras_cartao(`band_id`)
);

create table bandeiras_cartao(
`band_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`band_desc`  varchar(40) NOT NULL  ,
`band_img` varchar(100)
);

