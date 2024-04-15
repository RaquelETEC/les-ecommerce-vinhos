drop database ecommerce;
create database ecommerce;
use ecommerce;

ALTER TABLE ecommerce.endereco 
ADD COLUMN end_tipo VARCHAR(12) NULL AFTER end_observacoes;
 
ALTER TABLE ecommerce.endereco 
ADD COLUMN end_nome VARCHAR(100) NOT NULL AFTER end_observacoes;

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
 
create table bandeiras_cartao(
`band_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`band_desc`  varchar(40) NOT NULL  ,
`band_img` varchar(100)
);
 
INSERT INTO bandeiras_cartao (band_desc , band_img) 
VALUES("MASTERCARD", "../imagens/assets/CartaoMaster.png");
 
INSERT INTO bandeiras_cartao (band_desc , band_img) 
VALUES ("VISA","../imagens/assets/CartaoVisa.PNG");
 
select * from bandeiras_cartao;
 
create table cartao_de_credito(
`cart_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`cart_id_cli` int NOT NULL ,
`cart_numero` varchar(20) NOT NULL  ,
`cart_nome` varchar(100) NOT NULL,
`cart_padrao` varchar(3) NOT NULL,
`cart_id_bandeira` INT NOT NULL,
`cart_cod_seguranca` integer NOT NULL,
FOREIGN KEY (`cart_id_bandeira`) REFERENCES bandeiras_cartao(`band_id`),
FOREIGN KEY (`cart_id_cli`) REFERENCES cliente(`cli_id`)
);
 
 select * from cartao_de_credito;

 
create table harmonizacao(
`har_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`har_tipo` varchar(30) not null
);  
 
 
create table vinhos(
`vin_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`vin_cli_id` int NOT NULL,
`vin_har_id` int NOT NULL,
`vin_valor` int NOT NULL,
`vin_vinicola`  varchar(50) not null,
`vin_pais`  varchar(50) not null,
`vin_regiao`  varchar(50) not null,
`vin_safra`  varchar(50) not null,
`vin_desc`  varchar(100) not null,
`vin_tipo`  varchar(20) not null,
`vin_uva`  varchar(20) not null,
`vin_altura`  varchar(50) not null,
`vin_largura`  varchar(50) not null,
`vin_peso`  varchar(50) not null,
`vin_profundidade`  varchar(50) not null,
`vin_grupo`  varchar(50) not null,
`vin_img`  varchar(100) not null,
`vin_status`  varchar(100) not null,
FOREIGN KEY (`vin_har_id`) REFERENCES harmonizacao(`har_id`)
);
 
 
 
SELECT cli_id FROM cliente where cli_nome = 'tuou' order by cli_id desc LIMIT 1;
 
select * from cliente;
 
 
select * from endereco where cli_email = ? and cli_senha = ? and cli_cpf = ? ;
 
delete from cliente 
where cli_id <> 0;

delete from endereco 
where end_id <> 0;

ALTER TABLE cliente AUTO_INCREMENT = 1;


select cli_id, cli_nome,cli_telefone,cli_email,cli_cpf,cli_telefone_tipo,cli_dt_nascimento,cli_genero  from cliente order by cli_nome;

select * from endereco;
select * from cliente;
delete from cliente where cli_id =11;
select cli_nome,cli_email,cli_cpf,cli_dt_nascimento,cli_telefone,cli_genero from cliente where cli_id = ?