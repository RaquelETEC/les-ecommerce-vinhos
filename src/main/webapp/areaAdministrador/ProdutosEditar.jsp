<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.entity.Produtos"%>
<%@ page import="model.entity.Categoria"%>
<%@ page import="model.entity.Precificacao"%>

<%
Produtos produto = (Produtos) request.getAttribute("produtos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Produtos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="../Styles/styleAdm.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<script src="../scripts/statusPedidos.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<a
				href='/les-ecommerce-vinhos/paginaInical.html'>
				<img src="../imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3">
			</a>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebar"
				class="col-md-3 col-lg-2 d-md-block bg-dark sidebar">
				<div class="position-sticky">
					<ul class="nav flex-column">
						<li class="nav-Title">Menu</li>
						<li class="nav-item"><a class="nav-link active"
							onClick= "window.location.href='PagInicial.jsp'" > Dashboard
						</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='Clientes.html';"> Clientes </a></li>
						<li class="nav-item"><a class="nav-link"
							href="/les-ecommerce-vinhos/areaAdministrador/AprovacaoPedidos.html">
								Alterar Status de Pedido </a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='TrocaPedidos.html';"> Pedidos
								Aguardando Troca </a></li>
						<li class="nav-item"><a class="nav-link bg-white text-dark"
							href="/les-ecommerce-vinhos/areaAdministrador/Produtos.html">
								Produtos </a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='Estoque.html';"> Estoque </a></li>
					</ul>
				</div>
			</nav>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<!-- Conteudo principal aqui -->
				<div class="container mt-4">
					<h2 class="text-center mb-4">Produtos</h2>
					<!-- FormulÃ¡rio de filtro -->
					<form class="mb-4">

						<div class="row">
							<div class="col-md-3">
								<label for="codigoBarras" class="form-label">Codigo de
									Barras:</label> <input type="text" class="form-control"
									id="codigoBarras" name="codigoBarras"
									value=<%=produto.getCodigo_barra()%>>
							</div>
							<div class="col-md-3">
								<label for="descricao" class="form-label">Descrição:</label> <input
									type="text" class="form-control" id="descricao"
									value="<%=produto.getDesc()%>" name="descricao">
							</div>
							<div class="col-md-3">
								<label for="preco" class="form-label">Preço em R$</label> <input
									type="text" class="form-control" id="preco"
									value="<%=produto.getPro_preco_venda()%>" name="preco">
							</div>
							<div class="col-md-3">
								<label for="vinicola" class="form-label">Vinicola:</label> <input
									type="text" class="form-control" id="vinicola" name="vinicola"
									value="<%=produto.getVinicola()%>">
							</div>
							<div class="col-md-3">
								<label for="pais" class="form-label">Pais:</label> <input
									type="text" class="form-control" id="pais" name="pais"
									value="<%=produto.getPais()%>">
							</div>
							<div class="col-md-3">
								<label for="regiao" class="form-label">Região:</label> <input
									type="text" class="form-control" id="regiao" name="regiao"
									value="<%=produto.getRegiao()%>">
							</div>
							<div class="col-md-3">
								<label for="safra" class="form-label">Safra:</label> <input
									type="text" class="form-control" id="safra" name="safra"
									value="<%=produto.getSafra()%>">
							</div>

							<div class="col-md-3">
								<label for="tipo" class="form-label">Tipo:</label> <input
									type="text" class="form-control" id="tipo" name="tipo"
									value="<%=produto.getTipo()%>">
							</div>

							<div class="col-md-3">
								<label for="uva" class="form-label">Uva:</label> <input
									type="text" class="form-control" id="uva" name="uva"
									value="<%=produto.getUva()%>">
							</div>
							<div class="col-md-3">
								<label for="Altura" class="form-label">Altura:</label> <input
									type="text" class="form-control" id="Altura" name="Altura"
									value="<%=produto.getAltura()%>">
							</div>
							<div class="col-md-3">
								<label for="Largura" class="form-label">Largura:</label> <input
									type="text" class="form-control" id="Largura" name="Largura"
									value="<%=produto.getLargura()%>">
							</div>
							<div class="col-md-3">
								<label for="Peso" class="form-label">Peso:</label> <input
									type="text" class="form-control" id="Peso" name="Peso"
									value="<%=produto.getPeso()%>">
							</div>
							<div class="col-md-3">
								<label for="Profundidade" class="form-label">Profundidade:</label>
								<input type="text" class="form-control" id="Profundidade"
									name="Profundidade" value="<%=produto.getProfundidade()%>">
							</div>
							<div class="col-md-3">
								<label for="alcool" class="form-label">Teor alcoólico em
									%</label> <input type="text" class="form-control" id="alcool"
									name="alcool" value="<%=produto.getAlcool()%>">
							</div>
							<div class="col-md-3">
								<label for="grupoPrecificacao" class="form-label">Grupo
									de Precificação:</label> <select class="form-select"
									id="grupoPrecificacao" name="grupoPrecificacao">
									<option value="Grupo Premium"
										<%="Grupo Premium".equals(request.getAttribute("PrecificacaoDesc")) ? "selected" : ""%>>Grupo
										Premium</option>
									<option value="Grupo Intermediï¿½rio"
										<%="Grupo Intermediï¿½rio".equals(request.getAttribute("PrecificacaoDesc")) ? "selected" : ""%>>Grupo
										Intermediï¿½rio</option>
									<option value="Grupo Econï¿½mico"
										<%="Grupo Econï¿½mico".equals(request.getAttribute("PrecificacaoDesc")) ? "selected" : ""%>>Grupo
										Econï¿½mico</option>
									<option value="Grupo de Promoï¿½ï¿½o"
										<%="Grupo de Promoï¿½ï¿½o".equals(request.getAttribute("PrecificacaoDesc")) ? "selected" : ""%>>Grupo
										de Promoï¿½ï¿½o</option>
									<option value="Grupo Exclusivo para Membros"
										<%="Grupo Exclusivo para Membros".equals(request.getAttribute("PrecificacaoDesc")) ? "selected" : ""%>>Grupo
										Exclusivo para Membros</option>
								</select>
							</div>


							<div class="col-md-3">
								<label for="Status" class="form-label">Status</label> <select
									class="form-select" id="statusProduto">
									<option value="ATIVO"
										<%="ATIVO".equals(request.getAttribute("CategoriaStatus")) ? "selected" : ""%>>ATIVO</option>
									<option value="INATIVO"
										<%="INATIVO".equals(request.getAttribute("CategoriaStatus")) ? "selected" : ""%>>INATIVO</option>
								</select>
							</div>
						</div>

					</form>
				</div>

				<!-- Adicionar harmonizaÃ§Ãµes -->
				<div class="row justify-content-left mt-5"
					style="margin-left: 125px;">
					<div class="col-md-8">
						<h3 class=" mb-3">Adicionar Harmonização</h3>
						<table class="table">
							<thead>
								<tr>
									<th>Harmonização</th>
									<th>Opções</th>
								</tr>
							</thead>
							<tbody id="tabelaHarmonizacoes">
								<!-- Linhas da tabela de harmonizaÃ§Ãµes serÃ£o adicionadas aqui -->
							</tbody>
						</table>
						<button class="btn btn-primary" onclick="adicionarHarmonizacao()">Inserir
							Harmonização</button>
					</div>
				</div>

				<!-- Tabela de resultados -->
				<div class="text-center mt-4">
					<table id="tabela" class="mx-auto">
						<!-- ... (sua tabela existente) ... -->
					</table>
				</div>

				<div class="text-center">
					<div class="row mt-3">
						<div class="col-md-12">
							<a onclick="window.location.href='Produtos.html';">
								<button class="btn btn-primary">Salvar</button>
							</a>
						</div>
					</div>
				</div>

			</main>

		</div>
	</div>

	<footer class="p-4 text-light text-center" style="background: black;">
		Desenvolvido por Caynan e Raquel </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>