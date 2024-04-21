<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Produtos"%>

<%@ page import="java.util.ArrayList"%>
<%
@ SuppressWarnings("unchecked")
ArrayList<Produtos> lista = (ArrayList<Produtos>) request.getAttribute("listaProdutos");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<title>Agenda de contatos - Home</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="Styles/styleIndexv5.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<script src="scripts/PagInicial.js"></script>

</head>

<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<img src="imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3">
			<div class="navbar-nav ms-auto">
				<a href="areaCliente/MinhasCompras.html" class="btn btn-light ms-2">MINHAS
					COMPRAS</a> <a href="ExibirCarrinho?id=20" class="btn btn-light ms-2">MEU
					CARRINHO</a> <a href="areaAdministrador/Login.html"
					class="btn btn-light ms-2">GERENCIAMENTO</a>
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
					<div class="list-group-item text-center">PreÃ§o</div>
					<label for="minPrice" class="form-label">MÃ­nimo:</label> <input
						type="number" class="form-control" id="minPrice" placeholder="R$">

					<label for="maxPrice" class="form-label">MÃ¡ximo:</label> <input
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
							class="form-check-label stretched-link" for="swtTipoRose">RosÃ©</label>
					</div>
					<hr>
					<div class="list-group-item text-center">PaÃ­s</div>
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
							class="form-check-label stretched-link" for="swtPaiItalia">ItÃ¡lia</label>
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
			<%for (int i = 0; i < lista.size(); i++) {%>	
				<div class="col-sm-4">
					<form name="productForm<%=i%>">
						<div class="card">
							<img src="<%=lista.get(i).getImg()%>" alt="Imagem do Produto"
							 class="card-img-top img-fluid rounded-start"
							 style="width: 50%;">

							<div class="box-input-form" style="display: none;">
								<div class="box-label">
									<label for="typeId">Id</label>
								</div>
								<input type="text" name="Id" id="Id" value=20>
								<input type="text" name="idProd" id="idProd"
									class="input-form input-width-1" value="<%=lista.get(i).getId()%>">
							</div>
							<div class="card-body">
								<h5 class="card-title text-center"><%=lista.get(i).getDesc()%></h5>
								<p class="price v">
									<span id="price"><%=lista.get(i).getPro_preco_venda()%></span>
								</p>
								<div class="quant-carrinho">
									<div
										class="d-flex justify-content-between align-items-center mb-3 ">
										<button class="btn btn-sm btn-outline-secondary"
											onclick="decrementarQuantidade(idProd)">-</button>
										<span id="quantity1" class="quantity">1</span>
										<button class="btn btn-sm btn-outline-secondary"
											onclick="incrementarQuantidade(idProd)">+</button>
									</div>
								</div>
								<button class="btn btn-lg add-to-cart-btn text-center add_carrinho" onclick="AdicionarAoCarrinho()">AdicionarAoCarrinho</button>
								
							
							</div>
						</div>
					</form>
					
				</div>
					<%}%>
			</div>
		</div>
	
		<!-- Chatbot container -->
		<div id="chatbot-container" class="collapsed">
			<div class="container-fluid">
				<div class="row">
					<div class="col">
						<button id="toggle-chatbot" class="btn btn-primary">Chatbot</button>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<!-- Chatbot content -->
						<div id="chatbot-content" style="display: none;">
							<div id="chat-messages"
								style="height: 200px; overflow-y: scroll;">
								<div id="chat-container"></div>
								<div class="input-group mt-3">
									<input type="text" class="form-control" id="user-input"
										placeholder="Precisa de ajuda?">
									<button class="btn btn-primary" id="send-button"
										onclick="sendMessage()">Enviar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>


	<footer class="p-4 text-light text-center footer">
		Desenvolvido por Caynan e Raquel </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>