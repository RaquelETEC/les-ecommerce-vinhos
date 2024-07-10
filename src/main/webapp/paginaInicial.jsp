<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Produtos"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<Produtos> lista = (ArrayList<Produtos>) request.getAttribute("listaProdutos");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<title>CR WINES</title>
<link rel="icon" href="imagens/favicon.png">

<link rel="stylesheet" href="Styles/styleIndexv5.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	
<script src="scripts/PagInicialv1.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body style="overflow-x: hidden !important;">
	<nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
	    <div class="container-fluid">
			<a href="/les-ecommerce-vinhos/paginaInical.html">
	            <img src="imagens/logo-vinho.PNG" alt="Logo Vinho" style="width: 200px;" class="mx-3">
	        </a>
	        <div class="navbar-nav ms-auto">
	            <a href="areaCliente/MinhasCompras.html?id=20" class="btn btn-light ms-2">
	                <i class="fas fa-shopping-bag"></i> MINHAS COMPRAS
	            </a>
	            <a href="ExibirCarrinho?id=20" class="btn btn-light ms-2">
	                <i class="fas fa-shopping-cart"></i> MEU CARRINHO
	            </a>
	            <a href="areaAdministrador/Login.html" class="btn btn-light ms-2">
	                <i class="fas fa-user-cog"></i> GERENCIAMENTO
	            </a>
	        </div>
	    </div>
	 </nav>
	<main class="main">
		<div class="col-2 m-5 filter">

			<form action="/EcomerceLivroLES/cli-itens" method="post">
				<div class="inputs">
					<input type="text" class="form-control" name="txtPesquisa"
						placeholder="Texto para Busca..." aria-label="Pesquisa"
						aria-describedby="basic-addon1">
					<hr>
					<div class="list-group-item text-center">Preço</div>
					<label for="minPrice" class="form-label">Mínimo:</label> <input
						type="number" class="form-control" id="minPrice" placeholder="R$">

					<label for="maxPrice" class="form-label">Máximo:</label> <input
						type="number" class="form-control" id="maxPrice" placeholder="R$">

					<hr>
					<div class="list-group-item text-center">Tipo vinho</div>
					<hr>
					<div class="list-group-item">
						<input class="form-check-input me-2" type="checkbox"
							name="swtTipoBranco" id="swtTipoBranco"> <label
							class="form-check-label stretched-link" for="swtTipoBranco">Branco</label>
					</div>
					<div class="list-group-item">
						<input class="form-check-input me-1" type="checkbox"
							name="swtTipoEspumante" id="swtTipoEspumante"> <label
							class="form-check-label stretched-link" for="swtTipoEspumante">Espumante</label>
					</div>
					<div class="list-group-item">
						<input class="form-check-input me-1" type="checkbox"
							name="swtTipoTinto" id="swtTipoTinto"> <label
							class="form-check-label stretched-link" for="swtTipoTinto">Tinto</label>
					</div>
					<div class="list-group-item">
						<input class="form-check-input me-1" type="checkbox"
							name="swtTipoRose" id="swtTipoRose"> <label
							class="form-check-label stretched-link" for="swtTipoRose">Rosé</label>
					</div>
					<hr>
					<div class="list-group-item text-center">Pais</div>
					<hr>
					<div class="list-group-item">
						<input class="form-check-input me-1" type="checkbox"
							name="swtPaisBrasil" id="swtPaisBrasil"> <label
							class="form-check-label stretched-link" for="swtPaisBrasil">Brasil</label>
						<br> <input class="form-check-input me-1" type="checkbox"
							name="swtPaisChile" id="swtPaisChile"> <label
							class="form-check-label stretched-link" for="swtPaisChile">Chile</label>
						<br> <input class="form-check-input me-1" type="checkbox"
							name="swtPaisEspanha" id="swtPaisEspanha"> <label
							class="form-check-label stretched-link" for="swtPaisEspanha">Espanha</label>
						<br> <input class="form-check-input me-1" type="checkbox"
							name="swtPaiItalia" id="swtPaiItalia"> <label
							class="form-check-label stretched-link" for="swtPaiItalia">Itália</label>
						<br> <input class="form-check-input me-1" type="checkbox"
							name="swtPaisPortugaal" id="swtPaisPortugaal"> <label
							class="form-check-label stretched-link" for="swtPaisPortugaal">Portugal</label>
						<br> <input class="form-check-input me-1" type="checkbox"
							name="swtPaisUruguai" id="swtPaisUruguai"> <label
							class="form-check-label stretched-link" for="swtPaisUruguai">Uruguai</label>
						<br>
					</div>

					<div class="list-group-item">
						<input class="form-check-input me-1" type="checkbox"
							name="swtIsbn" id="swtIsbn"> <label
							class="form-check-label stretched-link" for="swtIsbn">ISBN</label>
					</div>

					<div class="list-group-item">
						<input class="form-check-input me-1" type="checkbox"
							name="swtEditora" id="swtEditora"> <label
							class="form-check-label stretched-link" for="swtEditora">Editora</label>

					</div>
					<hr>
					<div class="text-center">

						<button type="submit" class="btn btn-success" name="operacao"
							value="Consultar">Consultar</button>

					</div>
				</div>
			</form>
		</div>


		<div class="col-8 m-5 ">
			<div class="row">
				<div class="col-sm-3">
					<h2>VINHOS</h2>
					<BR>
				</div>
			</div>


			<div class="row">
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<div class="col-sm-4">
					<form name="productForm<%=i%>">
						<div class="card">
						 <div class="image-container" style="background-color: #f2f2f2;">
						
							<img src="imagens/produtos/<%=lista.get(i).getCodigo_barra()%>.png" alt="Imagem do Produto"
								class="card-img-top img-fluid rounded-start"
								style="width: 50%; display: block; margin: 0 auto;">
            			</div>

							<div class="box-input-form" style="display: none;">
								<div class="box-label">
									<label for="typeId">Id</label>
								</div>
								<input type="text" name="Id" id="Id" value=20> <input
									type="text" name="idProd" id="idProd"
									class="input-form input-width-1"
									value="<%=lista.get(i).getId()%>">
							</div>
							<div class="card-body">
								<h6 class="card-title text-center" style="height: 5vh;"><%=lista.get(i).getDesc()%></h6>
								<p class="price v">
									<span id="price">R$ <%=lista.get(i).getPro_preco_venda()%></span>
								</p>
								<div class="quant-carrinho">
									<div
										class="d-flex justify-content-between align-items-center mb-3">
										<button id="btn-decrementar-<%=lista.get(i).getId()%>" type="button" class="btn btn-sm btn-outline-secondary"
											onclick="decrementarQuant(<%=i%>)">-</button>
										<span id="quantity<%=i%>" class="quantity">1</span>
										<button id="btn-incrementar-<%=lista.get(i).getId()%>" type="button" class="btn btn-sm btn-outline-secondary"
											onclick="incrementarQuant(<%=i%>)">+</button>
									</div>
								</div>
								<button type="button"
									id="btn-add-carrinho-<%=lista.get(i).getId()%>"
									class="btn btn-lg add-to-cart-btn text-center add_carrinho"
									onclick="AdicionarAoCarrinho(20, <%=lista.get(i).getId()%>, document.getElementById('quantity<%=i%>').textContent)">
									Adicionar ao carrinho</button>
							</div>
						</div>
					</form>

				</div>
				<%
				}
				%>
			</div>
		</div>
		<!-- Chatbot container -->
	<div id="chatbot-container" class="card">
	  <div id="chatbot-header" class="card-header">
	        <button id="toggle-chatbot" class="btn btn-dark btn-block">Precisa de ajuda 🤖👋?</button>
	    </div>
	    <div id="chatbot-content" class="card-body">
	        <div id="chat-response-container"></div>
	        <div class="chat-bot-options" id="chat-options">
	            <textarea type="text" id="user-input" class="form-control" placeholder="Digite sua resposta aqui"></textarea>
	            <button id="send-message" class="btn btn-secondary btn-send-message-chat">Enviar</button>
	        </div>
	    </div>
    </div>
	
	
	<!-- 	<div id="chatbot-header" class="card-header">
			<button id="toggle-chatbot" class="btn btn-dark btn-block">Precisa de ajuda 🤖👋?  </button>
		</div>
		<div id="chatbot-content" class="card-body">
			<p id="text-assistent">Olá, sou Roberto 🤖 seu assistente virtual, como posso ajudar?</p>
			<button id="assist-button" class="btn btn-secondary">Ajuda na escolha do vinho/espumante</button>
			<form id="info-form" style="display:none;" class="mt-3">
				<div class="form-group">
					<label for="wine-type">Tipo de vinho preferido:</label>
					<select id="wine-type" name="wine-type" class="form-control">
						<option value=""></option>
						<option value="nao-possuo">Não possuo</option>
						<option value="tinto">Tinto</option>
						<option value="branco">Branco</option>
						<option value="rose">Rosé</option>
						<option value="espumante">Espumante</option>
					</select>
				</div>
				<div class="form-group">
				    <label for="grape-variety">Variedade de uva preferida:</label>
				    <select id="grape-variety" name="grape-variety" class="form-control">
				        <option value=""></option>
				        <option value="nao-possuo">Não possuo</option>
				        <option value="cainho-tinto">Cainho Tinto</option>
				        <option value="vermentino">Vermentino</option>
				        <option value="chardonnay">Chardonnay</option>
				        <option value="malbec">Malbec</option>
				        <option value="pinot-noir">Pinot Noir</option>
				        <option value="cabernet-sauvignon">Cabernet Sauvignon</option>
				        <option value="cabernet-franc">Cabernet Franc</option>
				        <option value="jaen">Jaen</option>
				        <option value="mencia">Mencía</option>
				        <option value="picapoll-tinto">Picapoll Tinto</option>
				        <option value="grenache">Grenache</option>
				        <option value="moscato-giallo">Moscato Giallo</option>
				        <option value="outra">Outra</option>
				    </select>
				    <input type="text" id="other-grape-variety" name="other-grape-variety" class="form-control" style="display: none;" placeholder="Especifique outra variedade">
				</div>
				<div class="form-group">
					<label for="region">Região preferida:</label>
				    <select id="region" name="region" class="form-control">
				        <option value=""></option>
				        <option value="nao-possuo">Não possuo</option>
				        <option value="vinho-verde-portugal">Vinho Verde - Portugal</option>
				        <option value="toscana-italia">Toscana - Itália</option>
				        <option value="pinto-bandeira-brasil">Pinto Bandeira - Brasil</option>
				        <option value="mendoza-maipu-argentina">Mendoza - Maipú - Argentina</option>
				        <option value="franciacorta-italia">Franciacorta - Itália</option>
				        <option value="valle-central-chile">Valle Central - Chile</option>
				        <option value="dao-portugal">Dão - Portugal</option>
				        <option value="bierzo-espanha">Bierzo - Espanha</option>
				        <option value="catalunha-espanha">Catalunha - Espanha</option>
				        <option value="cabardes-languedoc-roussillon-franca">Cabardès - Languedoc-Rousillon - França</option>
				    </select>
				</div>
				<div class="form-group">
					<label for="price-range">Faixa de preço: *</label>
       				<input type="number" id="budget" name="budget" class="form-control" required>

				</div>
				<div class="form-group">
					<label for="sweetness-level">Nível de doçura desejado:</label>
					<select id="sweetness-level" name="sweetness-level" class="form-control">
						<option value=""></option>
						<option value="nao-possuo">Não possuo</option>
						<option value="seco">Seco</option>
						<option value="meio-seco">Meio seco</option>
						<option value="doce">Doce</option>
					</select>
				</div>
				<div class="form-group">
					<label for="occasion">Ocasião: *</label>
					<select id="occasion" name="occasion" class="form-control" required>
						<option value=""></option>
						<option value="jantar">Jantar</option>
						<option value="presente">Presente</option>
						<option value="celebracao">Celebração especial</option>
						<option value="consumo-diario">Consumo diário</option>
					</select>
				</div>
				<div class="form-group">
					<label for="food-pairing">Combinação com alimentos:</label>
					<select id="food-pairing" name="food-pairing" class="form-control">
					    <option value=""></option>
				        <option value="nao-se-aplica">Não se aplica</option>
				        <option value="carne-vermelha">Carne vermelha</option>
				        <option value="aves">Aves</option>
				        <option value="peixes">Peixes</option>
				        <option value="queijos">Queijos</option>
				        <option value="sobremesas">Sobremesas</option>
				        <option value="massas">Massas</option>
				        <option value="saladas">Saladas</option>
				        <option value="frutos-do-mar">Frutos do mar</option>
				        <option value="legumes">Legumes</option>
				        <option value="aperitivos">Aperitivos</option>
					</select>
				</div>
				<div class="form-group">
					<label for="experience-level">Nível de experiência: *</label>
					<select id="experience-level" name="experience-level" class="form-control" required>
						<option value=""></option>
						<option value="iniciante">Iniciante</option>
						<option value="intermediario">Intermediário</option>
						<option value="conhecedor">Conhecedor</option>
					</select>
				</div>
				<div class="form-group">
					<label for="body-preference">Preferência por corpo:</label>
					<select id="body-preference" name="body-preference" class="form-control">
						<option value=""></option>
						<option value="nao-possuo">Não possuo</option>
						<option value="leve">Leve</option>
						<option value="medio">Médio</option>
						<option value="encorpado">Encorpado</option>
					</select>
				</div>
				<div class="form-group">
					<label for="flavor-notes">Notas de sabor preferidas:</label>
					<input type="text" id="flavor-notes" name="flavor-notes" class="form-control" placeholder="Ex: frutado, floral, herbáceo, etc.">
				</div>
				<div class="form-group">
					<label for="obs-notes">Infomações complementares: </label>
					<textarea id="obs-notes" name="obs-notes" class="form-control" placeholder="Ex: local ou clima onde será o consumo ou solicitar uma explicação detalhada."></textarea>
				</div>
				<button id="submit-btn" type="submit" class="btn btn-dark btn-block">Enviar</button>
			</form>
			<div id="chat-response-container"></div>
			
		</div> -->
		
	</main>
	<footer class="p-4 text-light text-center footer">
		Desenvolvido por Caynan e Raquel </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>

</html>