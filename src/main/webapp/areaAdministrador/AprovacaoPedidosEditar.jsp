<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.entity.PedidoVenda"%>

<%
PedidoVenda pedidoVenda = (PedidoVenda) request.getAttribute("pedidoVenda");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Ãrea da AdministraÃ§Ã£o</title>
<link rel="stylesheet" href="../Styles/styleAdm.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<a href="index.html"><img src="../imagens/logo-vinho.PNG"
				alt="Logo Vinho" style="width: 200px;" class="mx-3"></a>
			<div class="navbar-nav ms-auto"></div>
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
							onclick="window.location.href='AprovacaoPedidos.html';">
								Pedidos Aguardando AprovaÃ§Ã£o </a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='AndamentoPedidos.html';">
								Pedidos em Andamento </a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='TrocaPedidos.html';"> Pedidos
								Aguardando Troca </a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='Produtos.html';"> Produtos </a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='Estoque.html';"> Estoque </a></li>
					</ul>
				</div>
			</nav>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<!-- Conteudo principal aqui -->
				<div class="container mt-4">

					<!-- Tela para alterar pedido -->
					<form name="frmEditarPedido" action="EditarPedido">
						<div class="row justify-content-center">
							<div class="col-md-6 border p-4">
								<h3 class="text-center mb-4">Pedido Aguardando
									ConfirmaÃ§Ã£o de Pagamento</h3>

								<div class="mb-3">
									<label for="id" class="form-label">Numero do Pedido:</label> <input
										type="text" class="form-control" name="id" id="id" readonly
										value="<%=pedidoVenda.getId()%>">
								</div>


								<div class="mb-3">
									<label for="PedidoValor" class="form-label">Valor do
										Pedido:</label> <input type="text" class="form-control"
										id="PedidoValor" readonly value="<%=pedidoVenda.getValor()%>">
								</div>

								<div class="mb-3">
									<label for="PedidoStatus" class="form-label">Status:</label> <select
										class="form-select" id="PedidoStatus" name="PedidoStatus">
										<option value="" disabled></option>
										<%
										if ("EM-PROCESSAMENTO".equals(pedidoVenda.getStatus())) {
										%>
										<option value="PAGAMENTO REALIZADO"
											<%="PAGAMENTO REALIZADO".equals(pedidoVenda.getStatus()) ? "selected" : ""%>>PAGAMENTO
											REALIZADO</option>
										<option value="PAGAMENTO RECUSADO"
											<%="PAGAMENTO RECUSADO".equals(pedidoVenda.getStatus()) ? "selected" : ""%>>PAGAMENTO
											RECUSADO</option>
										<option value="PEDIDO CANCELADO"
											<%="PEDIDO CANCELADO".equals(pedidoVenda.getStatus()) ? "selected" : ""%>>PEDIDO
											CANCELADO</option>
										<%
										} else if ("PAGAMENTO REALIZADO".equals(pedidoVenda.getStatus())) {
										%>
										<option value="EM TRANSPORTE"
											<%="EM TRANSPORTE".equals(pedidoVenda.getStatus()) ? "selected" : ""%>>EM
											TRANSPORTE</option>
										<%
										} else if ("EM TRANSPORTE".equals(pedidoVenda.getStatus())) {
										%>
										<option value="ENTREGUE"
											<%="ENTREGUE".equals(pedidoVenda.getStatus()) ? "selected" : ""%>>ENTREGUE</option>
										<%
										}
										%>
									</select>
								</div>

								<div class="text-center mb-4">
									<input class="btn btn-primary" type="submit" value="Atualizar">
								</div>
							</div>
						</div>
					</form>
					<!-- Fim da tela para alterar pedido -->
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