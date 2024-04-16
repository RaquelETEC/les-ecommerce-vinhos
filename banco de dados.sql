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
`HAR_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`HAR_DESC` varchar(100) not null,
`HAR_TIPO_VINHO` varchar(100) not null
);  
 
drop table categoria;

create table categoria(
`CAT_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`CAT_DES` varchar(30) not null,
`CAT_STATUS` varchar(10) not null
);  
 
create table precificacao(
`GRUP_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`GRUP_DESC` varchar(30) not null,
`GRUP_MARGEM` double not null
);  

ALTER TABLE produto MODIFY pro_inf blob; 

create table produto(
`pro_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`pro_categoria_id` int NOT NULL,
`pro_precificacao_id` int NOT NULL,
`pro_preco_venda` double NOT NULL,
`pro_preco_compra` double NOT NULL,
`pro_justificativa` varchar(20) not null,
`pro_codigo_barra` varchar(10) not null,
`pro_vinicola`  varchar(50) not null,
`pro_pais`  varchar(50) not null,
`pro_regiao`  varchar(50) not null,
`pro_safra`  varchar(50) not null,
`pro_desc`  varchar(100) not null,
`pro_tipo`  varchar(20) not null,
`pro_uva`  varchar(20) not null,
`pro_alcool`  varchar(20) not null,
`pro_altura`  varchar(50) not null,
`pro_largura`  varchar(50) not null,
`pro_peso`  varchar(50) not null,
`pro_profundidade`  varchar(50) not null,
`pro_inf`  blob not null,
`pro_img`  varchar(100) not null,
FOREIGN KEY (`pro_categoria_id`) REFERENCES categoria(`CAT_ID`),
FOREIGN KEY (`pro_precificacao_id`) REFERENCES precificacao(`GRUP_ID`)
);
 
create table rel_harm_produto(
`REL_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`REL_HAR_ID` int NOT NULL,
`REL_HAR_PROD` int NOT NULL,
FOREIGN KEY (`REL_HAR_ID`) REFERENCES harmonizacao(`HAR_ID`),
FOREIGN KEY (`REL_HAR_PROD`) REFERENCES produto(`pro_id`)
);  

CREATE TABLE `carrinho_de_compras` (
  `car_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `car_cli_id` int NOT NULL,
  `car_prod_id` int NOT NULL,
  `car_prod_quant` int NOT NULL,
  `car_removido` boolean NOT NULL,
  `car_motivo` varchar(100),
  FOREIGN KEY (`car_cli_id`) REFERENCES cliente(`cli_id`),
  FOREIGN KEY (`car_prod_id`) REFERENCES produto(`pro_id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cupons`(
  `cup_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `cup_codigo` varchar(10)  NOT NULL ,
  `cup_desc` varchar(10) NOT NULL,
  `cup_img`   varchar(25) NOT NULL,
  `cup_tipo` varchar(10) ,
  `cup_valor` double ,
  `cup_validade` date  NOT NULL,
  FOREIGN KEY (`cup_id`) REFERENCES cliente(`cli_id`)
);

CREATE TABLE `rel_cup_cli`(
  `rcc_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `rcc_cup_id` int NOT NULL,
  `rcc_cli_id` int NOT NULL,
  FOREIGN KEY (`rcc_cli_id`) REFERENCES cliente(`cli_id`),
  FOREIGN KEY (`rcc_cup_id`) REFERENCES cupons(`cup_id`)
);




INSERT INTO produto (pro_id, pro_categoria_id, pro_precificacao_id, pro_preco_venda, pro_preco_compra, pro_justificativa, pro_codigo_barra, pro_vinicola, pro_pais, pro_regiao, pro_tipo, pro_safra, pro_desc, pro_uva, pro_alcool, pro_peso, pro_profundidade, pro_largura, pro_altura, pro_img, pro_inf)
VALUES 
(15, 1, 1, 219, 219, 'Migração', NULL, NULL, NULL, NULL, NULL, 'SACA-ROLHAS PREMIUM', NULL, NULL, '60gr', '3cm', '5', '12', '../imagens/produtos/SRP01', 'O saca-rolhas premium apresenta um design sofisticado e elegante que combina forma e função de maneira harmoniosa. Sua estética é cuidadosamente elaborada para transmitir uma sensação de luxo e requinte, tornando-o não apenas uma ferramenta útil, mas também um objeto de desejo para qualquer apreciador de vinho.');

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