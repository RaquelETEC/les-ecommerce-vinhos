create database ecommerce;
use ecommerce; 

CREATE TABLE `cliente` (
  `cli_id` int NOT NULL AUTO_INCREMENT,
  `cli_nome` varchar(100) NOT NULL,
  `cli_email` varchar(100) NOT NULL,
  `cli_senha` varchar(100) NOT NULL,
  `cli_cpf` varchar(12) NOT NULL,
  `cli_telefone_tipo` varchar(20) NOT NULL,
  `cli_telefone` varchar(20) NOT NULL,
  `cli_dt_nascimento` date NOT NULL,
  `cli_genero` varchar(15) NOT NULL,
  `cli_status` varchar(15) NOT NULL,		
  PRIMARY KEY (`cli_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `endereco` (
  `end_id` int NOT NULL AUTO_INCREMENT,
  `end_cli_id` int NOT NULL,
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
  `end_observacoes` varchar(200) DEFAULT NULL,
  `end_nome` varchar(100) NOT NULL,
  `end_tipo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`end_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table bandeiras_cartao(
`band_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`band_desc`  varchar(40) NOT NULL  ,
`band_img` varchar(100)
);

INSERT INTO bandeiras_cartao (band_desc , band_img) 
VALUES("MASTERCARD", "../imagens/assets/CartaoMaster.png");

INSERT INTO bandeiras_cartao (and_desc , band_img) 
VALUES ("VISA","../imagens/assets/CartaoVisa.png");

create table cartao_de_credito(
`cart_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`cart_id_cli` int NOT NULL ,
`cart_numero` varchar(20) NOT NULL  ,
`cart_nome` varchar(100) NOT NULL,
`cart_padrao` varchar(3) NOT NULL,
`cart_id_bandeira` INT NOT NULL,
`cart_cod_seguranca` integer NOT NULL,
`cart_in_perfil` boolean NOT NULL,
FOREIGN KEY (`cart_id_bandeira`) REFERENCES bandeiras_cartao(`band_id`),
FOREIGN KEY (`cart_id_cli`) REFERENCES cliente(`cli_id`)
);

CREATE TABLE `carrinho_de_compras` (
  `car_id` int NOT NULL AUTO_INCREMENT primary key, 
  `car_cli_id` int NOT NULL,
  `car_quant_itens` int,
  FOREIGN KEY (`car_cli_id`) REFERENCES cliente(`cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Insersão de um carrinho de compras */  
INSERT INTO `carrinho_de_compras` (`car_id`,`car_cli_id`,`car_quant_itens`) VALUES (1,20,1);

CREATE TABLE `carrinho_itens` (
  `car_itens_id` int NOT NULL AUTO_INCREMENT primary KEY,
  `car_itens_car_id` int NOT NULL,
  `car_itens_prod_id` int NOT NULL,
  `car_itens_prod_quant` int NOT NULL,
  `car_itens_removido` boolean NOT NULL,
  `car_itens_motivo` varchar(100),
 FOREIGN KEY (`car_itens_prod_id`) REFERENCES produto(`pro_id`)
);

/*ITENS DO CARRINHO*/
INSERT INTO `ecommerce`.`carrinho_itens` (`car_itens_car_id`, `car_itens_prod_id`, `car_itens_prod_quant`, `car_itens_removido`) 
VALUES 
('1', '1', '2', '0'),
('1', '2', '1', '0'),
('1', '3', '4', '1', 'Tempo de compra Expirado');

drop table cupons;

CREATE TABLE `cupons`(
  `cup_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `cup_codigo` varchar(10)  NOT NULL ,
  `cup_desc` varchar(100) NOT NULL,
  `cup_img`   varchar(50) NOT NULL,
  `cup_tipo` varchar(1) NOT NULL,
  `cup_valor` double NOT NULL,
  `cup_validade` date  NOT NULL, 
  `cup_usado` boolean NOT NULL
);
INSERT INTO cupons (cup_codigo, cup_desc, cup_img, cup_tipo, cup_valor, cup_validade, cup_usado) VALUES 
('10OFF', 'Desconto de R$10', '../imagens/assets/descontoCupom.png', 'P', 10.00, '2024-04-30',0),
('20OFF', 'Desconto de R$20', '../imagens/assets/descontoCupom.png', 'P', 20.00, '2024-04-30',0),
('FRETE50', 'Frete Grátis', '../imagens/assets/CupomFrete.png', 'P', 0.00, '2024-04-30',0),
('TROCA#2204', 'Desconto de R$10 na troca do pedido 1', '../imagens/assets/descontoCupom.png', 'T', 10.00, '2024-04-30',0),
('TROCA#2001', 'Desconto de R$200 na troca do pedido 2', '../imagens/assets/descontoCupom.png', 'T', 200.00, '2024-04-30',0);


create table harmonizacao(
`HAR_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`HAR_DESC` varchar(100) not null,
`HAR_TIPO_VINHO` varchar(100) not null
);  

 /*INSERSÃO DE categoria */
INSERT INTO harmonizacao (HAR_DESC, HAR_TIPO_VINHO) 
VALUES 
('Carnes vermelhas grelhadas ou assadas', 'Tinto'),
('Massas com molhos à base de tomate', 'Tinto'),
('Queijos maturados', 'Tinto'),
('Chocolates amargos', 'Tinto'),
('Peixes grelhados ou assados', 'Branco'),
('Frutos do mar', 'Branco'),
('Saladas verdes com vinagrete leve', 'Branco'),
('Queijos frescos', 'Branco'),
('Aperitivos leves', 'Branco'),
('Saladas com frutas frescas', 'Rosé'),
('Pratos leves e refrescantes', 'Rosé'),
('Queijos suaves', 'Rosé'),
('Culinária asiática', 'Rosé'),
('Frutos do mar', 'Espumante'),
('Canapés e petiscos', 'Espumante'),
('Pratos leves e delicados', 'Espumante'),
('Sobremesas frescas e frutadas', 'Espumante');
 
create table categoria(
`CAT_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`CAT_DES` varchar(30) not null,
`CAT_STATUS` varchar(10) not null
);  

/*INSERSÃO DE categoria */
INSERT INTO categoria (CAT_ID, CAT_DES, CAT_STATUS) 
VALUES 
(1, 'MIGRAÇÃO', 'ATIVO'),
(2, 'FORA DE MERCADO', 'INATIVO'),
(3, 'ROTAÇÃO DE ESTOQUE', 'INATIVO'),
(4, 'MUDANÇA DE SAFRA', 'INATIVO'),
(5, 'OBSOLESCÊNCIA', 'INATIVO'),
(6, 'DANOS NA EMBALAGEM', 'INATIVO'),
(7, 'ALTERAÇÃO DE PREÇO', 'INATIVO'),
(8, 'VALIDADE EXPIRADA', 'INATIVO'),
(9, 'SEM DEMANDA', 'INATIVO'),
(10, 'PRODUTO DESCONTINUADO', 'INATIVO'),
(11, 'PROBLEMAS DE QUALIDADE', 'INATIVO'),
(12, 'NOVA SAFRA', 'ATIVO'),
(13, 'PROMOÇÃO ESPECIAL', 'ATIVO'),
(14, 'REPOSIÇÃO DE ESTOQUE', 'ATIVO'),
(15, 'MELHORIA NA EMBALAGEM', 'ATIVO'),
(16, 'MUDANÇA DE PREÇO', 'ATIVO');


create table precificacao(
`GRUP_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`GRUP_DESC` varchar(30) not null,
`GRUP_MARGEM` double not null
);  

INSERT INTO precificacao (GRUP_ID, GRUP_DESC, GRUP_MARGEM) 
VALUES 
(1, 'Grupo Premium', 0.60),
(2, 'Grupo Intermediário', 0.50),
(3, 'Grupo Econômico', 0.45),
(4, 'Grupo de Promoção', 0.30),
(5, 'Grupo Exclusivo para Membros', 0.30);

create table produto(
`pro_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`pro_categoria_id` int NOT NULL,
`pro_precificacao_id` int NOT NULL,
`pro_preco_venda` double NOT NULL,
`pro_preco_compra` double NOT NULL,
`pro_justificativa` varchar(20) not null,
`pro_codigo_barra` varchar(10) not null,
`pro_vinicola`  varchar(50) ,
`pro_pais`  varchar(50) ,
`pro_regiao`  varchar(50),
`pro_safra`  varchar(50) ,
`pro_desc`  varchar(100) not null,
`pro_tipo`  varchar(20) not null,
`pro_uva`  varchar(20) ,
`pro_alcool`  varchar(20) ,
`pro_altura`  varchar(50) not null,
`pro_largura`  varchar(50) not null,
`pro_peso`  varchar(50) not null,
`pro_profundidade`  varchar(50) not null,
`pro_inf`  blob not null,
`pro_img`  varchar(100) not null,
FOREIGN KEY (`pro_categoria_id`) REFERENCES categoria(`CAT_ID`),
FOREIGN KEY (`pro_precificacao_id`) REFERENCES precificacao(`GRUP_ID`)
);

/*INSERSÃO DE PRODUTOS */
INSERT INTO produto (pro_id, pro_categoria_id, pro_precificacao_id, pro_preco_venda, pro_preco_compra, pro_justificativa, pro_codigo_barra, pro_vinicola, pro_pais, pro_regiao, pro_tipo, pro_safra, pro_desc, pro_uva, pro_alcool, pro_peso, pro_profundidade, pro_largura, pro_altura, pro_img, pro_inf)
VALUES 
(1, 1, 1, 73.5, 73.5, 'Migração', 'VTP06', 'MÁRCIO LOPES WINEMAKER', 'Portugal', 'Vinho verde', 'Tinto', '2021', 'PEQUENOS REBENTOS ATLÂNTICO D.O.C.', 'Cainho Tinto', '9%', '750mL', '30cm', '7', '30', '../imagens/produtos/VTP06', 'A Região dos Vinhos Verdes não só tem capacidade para criar alguns dos melhores vinhos brancos no mundo, como também é capaz de criar tintos frescos e elegantes, fruto da sua acidez natural, proveniente dos solos graníticos.'),
(2, 1, 2, 156, 156, 'Migração', 'VBI06', 'FATTORIA FIBBIANO', 'Itália', 'Toscana', 'Branco', '2021', 'VERMENTINO I.G.T', 'Vermentino', '13%', '750mL', '31cm', '9', '31', '../imagens/produtos/VBI06', 'Para se obter um vinho branco varietal de alta qualidade desta uva, que geralmente é utilizada em corte na região, os produtores fazem sua colheita nas horas mais frescas do dia, preservando assim seu nível de acidez e associando-o ao seu potencial de gerar um vinho gastronômico, de personalidade.'),
(3, 1, 3, 31.2, 31.2, 'Migração', 'VBB04', 'VALMARINO', 'Brasil', 'Pinto Bandeira', 'Branco', '2018', 'VALMARINO CHARDONNAY', 'Chardonnay', '12,50%', '750mL', '32cm', '8', '30', '../imagens/produtos/VBB04', 'A Valmarino é uma vinícola familiar fundada em 1997 por Orval Salton, quando este decidiu deixar a empresa parterna para começar um novo projeto a seus moldes. Seu filho, Marco Antônio Salton, é o enólogo responsável pelo empreendimento que tem como princípio produzir vinhos de acordo com a qualidade da safra. Não sendo boa, o vinho não é produzido e a uva é vendida para consumo. Este vinho é um mais um exemplar do padrão de qualidade Valmarino.'),
(4, 1, 4, 105.6, 105.6, 'Migração', 'VTA08', 'BODEGA BUDEGUER', 'Argentina', 'Mendoza - Maipú', 'Tinto', '2019', 'TUCUMEN TINTO RESERVA', 'Malbec', '13,60%', '750mL', '33cm', '7', '31', '../imagens/produtos/VTA08', 'Tucumen é a junção de duas palavras: Tucuman, cidade natal da família Budeguer, e Mendoza, berço da vinícola que dá origem a estes belos vinhos. Como o próprio rótulo afirma, um vinho untuoso & complejo.'),
(5, 1, 5, 220.32, 220.32, 'Migração', 'EBI03', 'CAMILUCCI', 'Itália', 'Franciacorta', 'Espumante', '2014', 'FRANCIACORTA EXTRA-BRUT ANTHOLOGIE BLANC D.O.C.G.', 'Chardonnay', '12,50%', '750mL', '30cm', '9', '30', '../imagens/produtos/EBI03', 'A palavra antologia, do grego que vem da palavra "eu colociono flores", denota uma Franciacorta nascida de seleções muito procuradas; na enologia, de fato, o termo flor está associado às primeiras frações do mosto, chamadas precisamente, mosto. Um nobre Millesimato, produzido apenas em safras de absoluta excelência com dosagem Extra Brut. Camilucci o defende como um Franciacorta fino, fresco, poderoso e elegante, com um espírito intenso e harmonioso. Vale notar que é safrado e que contém no contrarrótulo a data de sbocatura (dégorgement).'),
(6, 1, 1, 51.3, 51.3, 'Migração', 'EBB11', 'VALMARINO', 'Brasil', 'Pinto Bandeira', 'Espumante', '2018', 'ESPUMANTE VALMARINO SUR LIE NATURE', 'Pinot Noir', '12%', '750mL', '31cm', '8', '31', '../imagens/produtos/EBB11', 'Espumante engarrafado na mesma garrafa onde ocorreu sua fermentação, maturação e envelhecimento.'),
(7, 1, 2, 20.7, 20.7, 'Migração', 'VRC01', 'VIÑA LUIS FELIPE EDWARDS', 'Chile', 'Valle Central', 'Rosé', '2021', 'ORIGEN ROSÉ CABERNET SAUVIGNON E MERLOT', 'Cabernet Sauvignon', '12%', '750mL', '32cm', '7', '30', '../imagens/produtos/VRC01', 'Representante inconfundível do estilo de vinificação da vinícola. Vinho frutado, suculento, fácil de beber, e pode ser apreciado sozinho, como aperitivo, ou com vários tipos de refeições, demonstrando toda sua versatilidade à mesa.'),
(8, 1, 3, 39, 39, 'Migração', 'VRB02', 'VALMARINO', 'Brasil', 'Pinto Bandeira', 'Rosé', '2021', 'VALMARINO CABERNET FRANC ROSÉ', 'Cabernet Franc', '12,50%', '750mL', '33cm', '9', '31', '../imagens/produtos/VRB02', 'Este vinho é um mais um exemplar do padrão de qualidade Valmarino. A vinícola tem como princípio produzir vinhos de acordo com a qualidade da safra. Não sendo boa, o vinho não é produzido e a uva é vendida para consumo.'),
(9, 1, 4, 54.5, 54.5, 'Migração', 'VRP03', 'TEXTURA WINES', 'Portugal', 'Dão', 'Rosé', '2022', 'PRETEXTO ROSE', 'Jaen', '12,50%', '750mL', '30cm', '8', '30', '../imagens/produtos/VRP03', 'Pretexto Rosé nasce 100% da variedade Jaen, o que lhe concedeu típica cor casca de cebola com aroma discreto a fruta reduzida. Sensação de frescor no aroma que entra na boca com boa acidez e equilíbrio, tornando este rosé muito agradável e gastronômico.'),
(10, 1, 5, 66.05, 66.05, 'Migração', 'VTE07', 'BODEGAS RAÚL PÉREZ', 'Espanha', 'Bierzo', 'Tinto', '2017', 'LA CLAVE D.O.', 'Mencía', '14%', '750mL', '31cm', '7', '31', '../imagens/produtos/VTE07', 'O La Clave faz parte do projeto autoral de Raúl Perez chamado Proyectos de Autor, uma coleção de pequenas joias apreciadas por amantes do vinho de todo o mundo. É o objeto de desejo de muitos enófilos. Este vinho vem de vinhedos de solo argiloso e calcário localizados na cidade de Valtuille de Abajo. É feito principalmente com Mencía e uma pequena quantidade de uvas Syrah e Tempranillo, todas colhidas manualmente. Este vinho, na safra de 2013, recebeu 89/100 no prestigioso site de Robert Parker e isto por si só já é indicativo do padrão de qualidade do rótulo ao longo dos anos.'),
(11, 1, 1, 82.2, 82.2, 'Migração', 'VBE01', 'HERETAT OLLER DEL MAS', 'Espanha', 'Catalunha', 'Branco', '2019', 'BERNAT BLANC DE PICAPOLLS D.O.', 'Picapoll Tinto', '11,50%', '750mL', '32cm', '9', '30', '../imagens/produtos/VBE01', 'Este vinho é mais um exemplar de altíssima qualidade produzido pela vinícola Heretat Oller del Mas, localizada na D.O. Pla de Bages. Misturando uma casta branca e uma tinta de mesma família, o Bernat Oller de Picapolls é praticamente um blanc de noir, feito a partir de cultivo orgânico e vinificação vegana. Promete complexidade e expressa bem seu terroir.'),
(12, 1, 2, 56.7, 56.7, 'Migração', 'VTF05', 'MAISON VENTENAC', 'França', 'Cabardès - Languedoc-Rousillon', 'Tinto', '2021', 'LE PARIA', 'Grenache', '13%', '750mL', '33cm', '8', '31', '../imagens/produtos/VTF05', 'Le Paria - Era uma vez uma indesejável casta de uva plantada nas regiões frescas. Não é maduro o suficiente para alguns, fora do lugar para outros, simplesmente perfeito na opinião da Maison Ventenac. Esta uva é Grenache, e o resultado está nesta garrafa. Um vinho saboroso e suculento, para partilhar entre bebedores livres. Um vinho da linha Les Dissidents que expressa a alma da vinícola Maison Ventenac, sem qualquer tipo de constrangimento, cultivado nos melhores terrenos da forma mais natural possível, o que melhor classifica os solos da vinícola. Esta vinificação resultou em um vinho de qualidade fresca e frutada, que expressa o terroir de Cabardès. Um vinho descompromissado, Le Paria vai te acompanhar diariamente.'),
(13, 1, 3, 20.4, 20.4, 'Migração', 'EBB13', 'VALMARINO', 'Brasil', 'Pinto Bandeira', 'Espumante', '2019', 'ESPUMANTE VALMARINO MOSCATEL', 'Moscato Giallo', '12%', '750mL', '30cm', '7', '30', '../imagens/produtos/EBB13', 'Este espumante moscatel da Valmarino é elaborado a partir da produção própria da vinícola, em Pinto Bandeira. Sua vinificação é feita adicionando leveduras e com fermentação do mosto a 14º C por 40 dias em autoclave até obtenção do grau alcoólico desejado (8% vol.) e pressão de 6 atm. Produção limitada. Esta safra ganhou Medalha Duplo Ouro (93 pontos - campeão da categoria) na Grande Prova de Vinhos do Brasil 2020.'),
(14, 1, 4, 66, 66, 'Migração', 'EBB16', 'VALMARINO', 'Brasil', 'Pinto Bandeira', 'Branco', '2019', 'ESPUMANTE VALMARINO & CHURCHILL EXTRA-BRUT', 'Chardonnay', '12%', '750mL', '31cm', '9', '31', '../imagens/produtos/EBB16', 'Um belo espumante, elegante e estruturado. Suas uvas são cultivadas nos vinhedos próprios da Valmarino, em Pinto Bandeira. As batonagens e maturação são em barricas de carvalho por 12 meses. Premiado com Medalha Grand Gold (92 pontos) na Wines of Brazil Awards 2021.'),
(15, 1, 1, 219, 219, 'Migração', 'SRP01', NULL, NULL, NULL, 'Utensilios', NULL, 'SACA-ROLHAS PREMIUM', NULL, NULL,'60gr', '3cm', '5', '12', '../imagens/produtos/SRP01', 'O saca-rolhas premium apresenta um design sofisticado e elegante que combina forma e função de maneira harmoniosa. Sua estética é cuidadosamente elaborada para transmitir uma sensação de luxo e requinte, tornando-o não apenas uma ferramenta útil, mas também um objeto de desejo para qualquer apreciador de vinho.');

create table rel_harm_produto(
`REL_ID` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`REL_HAR_ID` int NOT NULL,
`REL_HAR_PROD` int NOT NULL,
FOREIGN KEY (`REL_HAR_ID`) REFERENCES harmonizacao(`HAR_ID`),
FOREIGN KEY (`REL_HAR_PROD`) REFERENCES produto(`pro_id`)
);  

CREATE TABLE `carrinho_itens` (
  `car_itens_id` int NOT NULL AUTO_INCREMENT primary KEY,
  `car_itens_car_id` int NOT NULL,
  `car_itens_prod_id` int NOT NULL,
  `car_itens_prod_quant` int NOT NULL,
  `car_itens_removido` boolean NOT NULL,
  `car_itens_motivo` varchar(100),
FOREIGN KEY (`car_itens_prod_id`) REFERENCES produto(`pro_id`)
);

CREATE TABLE `carrinho_de_compras` (
  `car_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `car_cli_id` int NOT NULL,
  `car_prod_id` int NOT NULL,
  `car_prod_quant` int NOT NULL,
  `car_removido` boolean NOT NULL,
  `car_motivo` varchar(100),
  `vendido` boolean NOT NULL,
  FOREIGN KEY (`car_cli_id`) REFERENCES cliente(`cli_id`),
  FOREIGN KEY (`car_prod_id`) REFERENCES produto(`pro_id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `cupons`(
  `cup_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `cup_codigo` varchar(10)  NOT NULL ,
  `cup_desc` varchar(100) NOT NULL,
  `cup_img`   varchar(100) NOT NULL,
  `cup_tipo` varchar(10) ,
  `cup_valor` double ,
  `cup_validade` date  NOT NULL,
  `cup_usado` boolean NOT NULL,	
  FOREIGN KEY (`cup_cli_id`) REFERENCES cliente(`cli_id`)
);

INSERT INTO `cupons` (`cup_id`,`cup_codigo`,`cup_desc`,`cup_img`,`cup_tipo`,`cup_valor`,`cup_validade`,`cup_usado`) VALUES
(1,'10OFF','Desconto de R$10','imagens/assets/descontoCupom.png','P',10,'2024-04-30',0),
(2,'20OFF','Desconto de R$20','imagens/assets/descontoCupom.png','P',20,'2024-04-30',0),
(3,'FRETE50','Frete Grátis','imagens/assets/CupomFrete.png','P',0,'2024-04-30',0),
(4,'TROCA#2204','Desconto de R$10 na troca do pedido 1','imagens/assets/descontoCupom.png','T',10,'2024-04-30',0),
(5,'TROCA#2001','Desconto de R$200 na troca do pedido 2','imagens/assets/descontoCupom.png','T',200,'2024-04-30',0);

CREATE TABLE `cliente_cupom_relacionamento`(
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `cupom_id` int NOT NULL,
  `cliente_id` int NOT NULL,
  FOREIGN KEY (`cliente_id`) REFERENCES cliente(`cli_id`),
  FOREIGN KEY (`cupom_id`) REFERENCES cupons(`cup_id`)
);

INSERT INTO `ecommerce`.`cliente_cupom_relacionamento` (`cupom_id`, `cliente_id`) VALUES ('1', '20');
INSERT INTO `ecommerce`.`cliente_cupom_relacionamento` (`cupom_id`, `cliente_id`) VALUES ('2', '20');
INSERT INTO `ecommerce`.`cliente_cupom_relacionamento` (`cupom_id`, `cliente_id`) VALUES ('3', '20');

create table pedido_venda(
    `ven_id` int NOT NULL AUTO_INCREMENT primary KEY,
    `vend_id_cliente` int NOT NULL,
    `ven_end_id` int NOT NULL,
    `ven_status` varchar(50)  NOT NULL,
    `ven_data` date NOT NULL,
    `ven_total_pedido` Double NOT NULL,
	`ven_total_desconto` Double NOT NULL,
	`ven_total_frete` Double NOT NULL,
	`ven_total_pagamento` Double NOT NULL,
	`ven_saldo` Double NOT NULL,
    FOREIGN KEY (`vend_id_cliente`) REFERENCES cliente(`cli_id`),
    FOREIGN KEY (`ven_end_id`) REFERENCES endereco(`end_id`)
);


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
 
create table pedido_cupons(
`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`cupom_id` int NOT NULL,
`pedido_id` int NOT NULL,
FOREIGN KEY (`cupom_id`) REFERENCES cupons(`cup_id`),
FOREIGN KEY (`pedido_id`) REFERENCES pedido_venda(`ven_id`)
 );
 
 create table pedido_cartoes(
`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`cartao_id` int NOT NULL,
`pedido_id` int NOT NULL,
`valor` Double NOT NULL,
FOREIGN KEY (`cartao_id`) REFERENCES cartao_de_credito(`cart_id`),
FOREIGN KEY (`pedido_id`) REFERENCES pedido_venda(`ven_id`)
 );
 

CREATE TABLE `ecommerce`.`notificacoes` (
  `id` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`id`));

