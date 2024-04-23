<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.PedidoVenda"%>
<%@ page import="model.entity.Cliente"%>

<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<PedidoVenda> lista = (ArrayList<PedidoVenda>) request.getAttribute("listaPedidos");
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
						<li class="nav-item"><a class="nav-link "
							href="/les-ecommerce-vinhos/areaAdministrador/PagInicial.html">
								Dashboard </a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="window.location.href='Clientes.html';"> Clientes </a></li>
						<li class="nav-item"><a class="nav-link bg-white text-dark"
							href="/les-ecommerce-vinhos/areaAdministrador/AprovacaoPedidos.html">
								Alterar Status de Pedido </a></li>
						<li class="nav-item"><a class="nav-link"
							href="/les-ecommerce-vinhos/areaAdministrador/TrocaPedidos.html">
								Pedidos Aguardando Troca </a></li>
						<li class="nav-item"><a class="nav-link"
							href="/les-ecommerce-vinhos/areaAdministrador/Produtos.html">
								Produtos </a></li>
						<li class="nav-item"><a class="nav-link"
							href="/les-ecommerce-vinhos/areaAdministrador/Estoque.html">
								Estoque </a></li>
					</ul>
				</div>
			</nav>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<!-- Conteudo principal aqui -->
				<div class="container mt-4">
					<h2 class="text-center mb-4">Aprovações de Pedidos</h2>

					<!-- FormulÃ¡rio de filtro -->
					<form class="mb-4">
						<div class="row">
							<div class="col-md-2">
								<label for="cliente" class="form-label">Cliente</label> <input
									type="text" class="form-control" id="cliente" name="cliente">
							</div>
							<div class="col-md-3">
								<label for="ID" class="form-label">ID Pedido</label> <input
									type="text" class="form-control" id="ID" name="ID">
							</div>
							<div class="col-md-3">
								<label for="dataPedido" class="form-label">Data Pedido</label> <input
									type="date" class="form-control" id="dataPedido"
									name="dataPedido">
							</div>
							<div class="col-md-3">
								<label for="valor" class="form-label">Valor Pedido</label> <input
									type="text" class="form-control" id="valor" name="valor">
							</div>
						</div>
						<div class="text-center">

							<div class="row mt-3">
								<div class="col-md-12">
									<button type="submit" class="btn btn-primary">Buscar</button>
								</div>
							</div>
						</div>
					</form>

					<table id="tabela" class="mx-auto">
						<thead>
							<tr>
								<th>Id Pedido</th>
								<th>Cliente ID</th>
								<th>Cliente Nome</th>
								<th>Data Pedido</th>
								<th>Valor Pedido</th>
								<th>Status</th>
								<th>Opções</th>
							</tr>
						</thead>
						<tbody>
							<%
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							for (int i = 0; i < lista.size(); i++) {
								PedidoVenda pedido = lista.get(i);
								String data = dateFormat.format(lista.get(i).getData());
							%>
							<tr>
								<td><%=lista.get(i).getId()%></td>
								<td><%=lista.get(i).getCliente().getId()%></td>
								<td><%=lista.get(i).getCliente().getNome()%></td>
								<td><%=data%></td>
								<td><%=lista.get(i).getValor()%></td>
								<td>
									<div class="mb-3">
										<label for="PedidoStatus" class="form-label"></label> <select
											class="form-select" id="status<%=i%>" name="PedidoStatus">
											<option value="" disabled></option>

											<%
											if ("EM-PROCESSAMENTO".equals(lista.get(i).getStatus())) {
											%>
											<option value="EM-PROCESSAMENTO"
												<%="EM-PROCESSAMENTO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>EM-PROCESSAMENTO</option>
											<option value="PAGAMENTO REALIZADO"
												<%="PAGAMENTO REALIZADO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>PAGAMENTO
												REALIZADO</option>
											<option value="PAGAMENTO RECUSADO"
												<%="PAGAMENTO RECUSADO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>PAGAMENTO
												RECUSADO</option>
											<option value="PEDIDO CANCELADO"
												<%="PEDIDO CANCELADO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>PEDIDO
												CANCELADO</option>
											<%
											} else if ("PAGAMENTO REALIZADO".equals(lista.get(i).getStatus())) {
											%>
											<option value="PAGAMENTO REALIZADO"
												<%="PAGAMENTO REALIZADO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>PAGAMENTO
												REALIZADO</option>
											<option value="EM TRANSPORTE"
												<%="EM TRANSPORTE".equals(lista.get(i).getStatus()) ? "selected" : ""%>>EM
												TRANSPORTE</option>
											<%
											} else if ("EM TRANSPORTE".equals(lista.get(i).getStatus())) {
											%>
											<option value="EM TRANSPORTE"
												<%="EM TRANSPORTE".equals(lista.get(i).getStatus()) ? "selected" : ""%>>EM
												TRANSPORTE</option>
											<option value="ENTREGUE"
												<%="ENTREGUE".equals(lista.get(i).getStatus()) ? "selected" : ""%>>ENTREGUE</option>
											<%
											} else {
											%>
											<option value="EM-PROCESSAMENTO"
												<%="EM-PROCESSAMENTO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>EM-PROCESSAMENTO</option>
											<option value="PAGAMENTO RECUSADO"
												<%="PAGAMENTO RECUSADO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>PAGAMENTO
												RECUSADO</option>
											<option value="PEDIDO CANCELADO"
												<%="PEDIDO CANCELADO".equals(lista.get(i).getStatus()) ? "selected" : ""%>>PEDIDO
												CANCELADO</option>
											<option value="EM TRANSPORTE"
												<%="EM TRANSPORTE".equals(lista.get(i).getStatus()) ? "selected" : ""%>>EM
												TRANSPORTE</option>
											<option value="ENTREGUE"
												<%="ENTREGUE".equals(lista.get(i).getStatus()) ? "selected" : ""%>>ENTREGUE</option>
											<%
											}
											%>

										</select>
									</div>

								</td>
								<td>
									<div class="option-button">
										<a class="Botao1"
											href="javascript: confirmarProduto(<%=lista.get(i).getId()%>,<%=i%>)">Editar</a>
									</div>

								</td>
							</tr>

						</tbody>
						<%
						}
						%>
					</table>
				</div>
			</main>

		</div>
	</div>

	<footer class="p-4 text-light text-center" style="background: black;">
		Desenvolvido por Caynan e Raquel </footer>
	<script src="../scripts/confirmador.js"></script>
	<script src="scripts/confirmador.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>