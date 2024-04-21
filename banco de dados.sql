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

select * from cliente;

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
 
 select * from endereco;
 
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
select * from categoria;
 
create table precificacao(
`GRUP_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`GRUP_DESC` varchar(30) not null,
`GRUP_MARGEM` double not null
);  

select * from precificacao;


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

INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (1,1,1,73.5,73.5,'Migração','VTP06','MÁRCIO LOPES WINEMAKER','Portugal','Vinho verde','2021','PEQUENOS REBENTOS ATLÂNTICO D.O.C.','Tinto','Cainho Tinto','9%','30','7','750mL','30cm','A Região dos Vinhos Verdes não só tem capacidade para criar alguns dos melhores vinhos brancos no mundo, como também é capaz de criar tintos frescos e elegantes, fruto da sua acidez natural, proveniente dos solos graníticos. ','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VTP06.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (2,1,2,156,156,'Migração','VBI06','FATTORIA FIBBIANO','Itália','Toscana','2021','VERMENTINO I.G.T','Branco','Vermentino','13%','31','9','750mL','31cm','Para se obter um vinho branco varietal de alta qualidade desta uva, que geralmente é utilizada em corte na região, os produtores fazem sua colheita nas horas mais frescas do dia, preservando assim seu nível de acidez e associando-o ao seu potencial de gerar um vinho gastronômico, de personalidade.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VBI06.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (3,1,3,31.2,31.2,'Migração','VBB04','VALMARINO','Brasil','Pinto Bandeira','2018','VALMARINO CHARDONNAY','Branco','Chardonnay','12,50%','30','8','750mL','32cm','A Valmarino é uma vinícola familiar fundada em 1997 por Orval Salton, quando este decidiu deixar a empresa parterna para começar um novo projeto a seus moldes. Seu filho, Marco Antônio Salton, é o enólogo responsável pelo empreendimento que tem como princípio produzir vinhos de acordo com a qualidade da safra. Não sendo boa, o vinho não é produzido e a uva é vendida para consumo. Este vinho é um mais um exemplar do padrão de qualidade Valmarino.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VBB04.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (4,1,4,105.6,105.6,'Migração','VTA08','BODEGA BUDEGUER','Argentina','Mendoza - Maipú','2019','TUCUMEN TINTO RESERVA','Tinto','Malbec','13,60%','31','7','750mL','33cm','Tucumen é a junção de duas palavras: Tucuman, cidade natal da família Budeguer, e Mendoza, berço da vinícola que dá origem a estes belos vinhos. Como o próprio rótulo afirma, um vinho untuoso & complejo.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VTA08.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (5,1,5,220.32,220.32,'Migração','EBI03','CAMILUCCI','Itália','Franciacorta','2014','FRANCIACORTA EXTRA-BRUT ANTHOLOGIE BLANC D.O.C.G.','Espumante','Chardonnay','12,50%','30','9','750mL','30cm','A palavra antologia, do grego que vem da palavra "eu colociono flores", denota uma Franciacorta nascida de seleções muito procuradas; na enologia, de fato, o termo flor está associado às primeiras frações do mosto, chamadas precisamente, mosto. Um nobre Millesimato, produzido apenas em safras de absoluta excelência com dosagem Extra Brut. Camilucci o defende como um Franciacorta fino, fresco, poderoso e elegante, com um espírito intenso e harmonioso. Vale notar que é safrado e que contém no contrarrótulo a data de sbocatura (dégorgement).','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/EBI03.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (6,1,1,51.3,51.3,'Migração','EBB11','VALMARINO','Brasil','Pinto Bandeira','2018','ESPUMANTE VALMARINO SUR LIE NATURE','Espumante','Pinot Noir','12%','31','8','750mL','31cm','Espumante engarrafado na mesma garrafa onde ocorreu sua fermentação, maturação e envelhecimento.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/EBB11.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (7,1,2,20.7,20.7,'Migração','VRC01','VIÑA LUIS FELIPE EDWARDS','Chile','Valle Central','2021','ORIGEN ROSÉ CABERNET SAUVIGNON E MERLOT','Rosé','Cabernet Sauvignon','12%','30','7','750mL','32cm','Representante inconfundível do estilo de vinificação da vinícola. Vinho frutado, suculento, fácil de beber, e pode ser apreciado sozinho, como aperitivo, ou com vários tipos de refeições, demonstrando toda sua versatilidade à mesa. ','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VRC01.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (8,1,3,39,39,'Migração','VRB02','VALMARINO','Brasil','Pinto Bandeira','2021','VALMARINO CABERNET FRANC ROSÉ','Rosé','Cabernet Franc','12,50%','31','9','750mL','33cm','Este vinho é um mais um exemplar do padrão de qualidade Valmarino. A vinícola tem como princípio produzir vinhos de acordo com a qualidade da safra. Não sendo boa, o vinho não é produzido e a uva é vendida para consumo.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/003.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (9,1,4,54.5,54.5,'Migração','VRP03','TEXTURA WINES','Portugal','Dão','2022','PRETEXTO ROSE','Rosé','Jaen','12,50%','30','8','750mL','30cm','Pretexto Rosé nasce 100% da variedade Jaen, o que lhe concedeu típica cor casca de cebola com aroma discreto a fruta reduzida. Sensação de frescor no aroma que entra na boca com boa acidez e equilíbrio, tornando este rosé muito agradável e gastronômico.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VRP03.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (10,1,5,66.05,66.05,'Migração','VTE07','BODEGAS RAÚL PÉREZ','Espanha','Bierzo','2017','LA CLAVE D.O.','Tinto','Mencía','14%','31','7','750mL','31cm','O La Clave faz parte do projeto autoral de Raúl Perez chamado Proyectos de Autor, uma coleção de pequenas joias apreciadas por amantes do vinho de todo o mundo. É o objeto de desejo de muitos enófilos. Este vinho vem de vinhedos de solo argiloso e calcário localizados na cidade de Valtuille de Abajo. É feito principalmente com Mencía e uma pequena quantidade de uvas Syrah e Tempranillo, todas colhidas manualmente. Este vinho, na safra de 2013, recebeu 89/100 no prestigioso site de Robert Parker e isto por si só já é indicativo do padrão de qualidade do rótulo ao longo dos anos.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VTE07.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (11,1,1,82.2,82.2,'Migração','VBE01','HERETAT OLLER DEL MAS','Espanha','Catalunha','2019','BERNAT BLANC DE PICAPOLLS D.O.','Branco','Picapoll Tinto','11,50%','30','9','750mL','32cm','Este vinho é mais um exemplar de altíssima qualidade produzido pela vinícola Heretat Oller del Mas, localizada na D.O. Pla de Bages. Misturando uma casta branca e uma tinta de mesma família, o Bernat Oller de Picapolls é praticamente um blanc de noir, feito a partir de cultivo orgânico e vinificação vegana. Promete complexidade e expressa bem seu terroir. ','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VBE01.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (12,1,2,56.7,56.7,'Migração','VTF05','MAISON VENTENAC','França','Cabardès - Languedoc-Rousillon','2021','LE PARIA','Tinto','Grenache','13%','31','8','750mL','33cm','Le Paria - Era uma vez uma indesejável casta de uva plantada nas regiões frescas. Não é maduro o suficiente para alguns, fora do lugar para outros, simplesmente perfeito na opinião da Maison Ventenac. Esta uva é Grenache, e o resultado está nesta garrafa. Um vinho saboroso e suculento, para partilhar entre bebedores livres. Um vinho da linha Les Dissidents que expressa a alma da vinícola Maison Ventenac, sem qualquer tipo de constrangimento, cultivado nos melhores terrenos da forma mais natural possível, o que melhor classifica os solos da vinícola. Esta vinificação resultou em um vinho de qualidade fresca e frutada, que expressa o terroir de Cabardès. Um vinho descompromissado, Le Paria vai te acompanhar diariamente.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/VTF05.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (13,1,3,20.4,20.4,'Migração','EBB13','VALMARINO','Brasil','Pinto Bandeira','2019','ESPUMANTE VALMARINO MOSCATEL','Espumante','Moscato Giallo','12%','30','7','750mL','30cm','Este espumante moscatel da Valmarino é elaborado a partir da produção própria da vinícola, em Pinto Bandeira. Sua vinificação é feita adicionando leveduras e com fermentação do mosto a 14º C por 40 dias em autoclave até obtenção do grau alcoólico desejado (8% vol.) e pressão de 6 atm. Produção limitada. Esta safra ganhou Medalha Duplo Ouro (93 pontos - campeão da categoria) na Grande Prova de Vinhos do Brasil 2020. ','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/EBB13.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (14,1,4,66,66,'Migração','EBB16','VALMARINO','Brasil','Pinto Bandeira','2019','ESPUMANTE VALMARINO & CHURCHILL EXTRA-BRUT','Branco','Chardonnay','12%','31','9','750mL','31cm','Um belo espumante, elegante e estruturado. Suas uvas são cultivadas nos vinhedos próprios da Valmarino, em Pinto Bandeira. As batonagens e maturação são em barricas de carvalho por 12 meses. Premiado com Medalha Grand Gold (92 pontos) na Wines of Brazil Awards 2021. ','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/EBB16.png');
INSERT INTO produto (`pro_id`,`pro_categoria_id`,`pro_precificacao_id`,`pro_preco_venda`,`pro_preco_compra`,`pro_justificativa`,`pro_codigo_barra`,`pro_vinicola`,`pro_pais`,`pro_regiao`,`pro_safra`,`pro_desc`,`pro_tipo`,`pro_uva`,`pro_alcool`,`pro_altura`,`pro_largura`,`pro_peso`,`pro_profundidade`,`pro_inf`,`pro_img`) VALUES (15,1,1,219,219,'Migração','SRP01','Brasil','Brasil','Brasil','Brasil','SACA-ROLHAS PREMIUM','Outros','Brasil','Brasil','12','3','60gr','5','O saca-rolhas premium apresenta um design sofisticado e elegante que combina forma e função de maneira harmoniosa. Sua estética é cuidadosamente elaborada para transmitir uma sensação de luxo e requinte, tornando-o não apenas uma ferramenta útil, mas também um objeto de desejo para qualquer apreciador de vinho.','http://localhost:8080/les-ecommerce-vinhos/imagens/produtos/SRP01.png');



select pro_img from produto;
select * from produto;

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
  `cup_cli_id` int NOT NULL,
  `cup_codigo` varchar(10)  NOT NULL ,
  `cup_desc` varchar(10) NOT NULL,
  `cup_img`   varchar(25) NOT NULL,
  `cup_tipo` varchar(10) ,
  `cup_valor` double ,
  `cup_validade` date  NOT NULL,
  FOREIGN KEY (`cup_cli_id`) REFERENCES cliente(`cli_id`)
);

drop table `cupons`;
select * from cupons;

CREATE TABLE `rel_cup_cli`(
  `rcc_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `rcc_cup_id` int NOT NULL,
  `rcc_cli_id` int NOT NULL,
  FOREIGN KEY (`rcc_cli_id`) REFERENCES cliente(`cli_id`),
  FOREIGN KEY (`rcc_cup_id`) REFERENCES cupons(`cup_id`)
);


create table pedido_venda(
    `ven_id` int NOT NULL AUTO_INCREMENT primary KEY,
    `vend_id_cliente` int NOT NULL,
    `ven_end_id` int NOT NULL,
    `ven_cupom_promocional_id` int NOT NULL,
    `ven_status` varchar(20),
    `ven_data` date,
    `ven_valor` Double,
    FOREIGN KEY (`ven_cupom_promocional_id`) REFERENCES cupons(`cup_id`),
    FOREIGN KEY (`vend_id_cliente`) REFERENCES cliente(`cli_id`),
    FOREIGN KEY (`ven_end_id`) REFERENCES endereco(`end_id`)
);

select * from pedido_venda;

create table itens_venda(
`ped_item_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`ped_item_prod_id` int not null, 
`ped_item_ven_id` int not null, 
`ped_item_prod_desc` varchar(220) not null, 
`ped_item_prod_valor` Double not null, 
`ped_item_prod_valor_total` Double not null, 
`ped_item_prod_quantidade` int not null, 
FOREIGN KEY (`ped_item_prod_id`) REFERENCES produto(`pro_id`),
FOREIGN KEY (`ped_item_ven_id`) REFERENCES pedido_venda(`ven_id`)
);

 
create table rel_cupom_venda(
`REL_CUPVE_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`REL_CUP_ID` int NOT NULL,
`REL_VEN_ID` int NOT NULL,
FOREIGN KEY (`REL_CUP_ID`) REFERENCES cupons(`cup_id`),
FOREIGN KEY (`REL_VEN_ID`) REFERENCES pedido_venda(`ven_id`)
 );
 
 create table rel_cartao_venda(
`REL_CARVE_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`REL_CAR_ID` int NOT NULL,
`REL_VEN_ID` int NOT NULL,
FOREIGN KEY (`REL_CAR_ID`) REFERENCES cartao_de_credito(`cart_id`),
FOREIGN KEY (`REL_VEN_ID`) REFERENCES pedido_venda(`ven_id`)
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