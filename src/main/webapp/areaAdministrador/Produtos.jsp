<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Produtos"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.Comparator"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>

<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<Produtos> lista = (ArrayList<Produtos>) request.getAttribute("listaProdutosADM");
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
</head>
<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<a
				href='http://localhost:8080/les-ecommerce-vinhos/paginaInical.html'>
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
							onclick="window.location.href='PagInicial.html';"> Dashboard
						</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='Clientes.html';"> Clientes </a></li>
						<li class="nav-item"><a class="nav-link"
							href="/les-ecommerce-vinhos/areaAdministrador/PedidoVenda.html">
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
					<!-- Formulário de filtro -->
					<form class="mb-4">
						<div class="row">
							<div class="col-md-6 d-flex">
								<label for="Pesquisar" class="form-label me-2"></label> <input
									type="text" class="form-control" id="Pesquisar"
									name="Pesquisar">
							</div>
							<div class="col-md-3">
								<button type="submit" class="btn btn-primary">Buscar</button>
							</div>
						</div>
					</form>
					<!-- Tabela de resultados -->
					<div class="text-center">
						<table id="tabela" class="mx-auto">
							<thead>
								<tr>
									<th>Id</th>
									<th>Código de Barras</th>
									<th>Produto</th>
									<th>Descrição</th>
									<th>Tipo</th>
									<th>Safra</th>
									<th>Valor</th>
									<th>Grupo de Precificação</th>
									<th>Status</th>
									<th>Opções</th>
								</tr>
							</thead>
							<tbody>
								<%
								Collections.sort(lista, new Comparator<Produtos>() {
									@Override
									public int compare(Produtos p1, Produtos p2) {
										return Integer.compare(p1.getId(), p2.getId());
									}
								});
								%>

								<%
								for (Produtos produto : lista) {
								%>
								<tr>
									<td><%=produto.getId()%></td>
									<td><%=produto.getCodigo_barra()%></td>
									<td><img
										src="../imagens/produtos/<%=produto.getCodigo_barra()%>.png"
										class="card-img-top img-fluid-trocas rounded-start"
										alt="Imagem do Produto"></td>
									<td><%=produto.getDesc()%></td>
									<td><%=produto.getTipo()%></td>
									<td><%=produto.getSafra()%></td>
									<td><%=produto.getPro_preco_compra()%></td>
									<td><%=produto.getPrecificacao().getDesc()%></td>
									<td><%=produto.getCategoria().getStatus()%></td>
									<td><a href="ProdutosEditar.html" class="Botao1">Editar</a></td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
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