<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.entity.CarrinhoItens"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<CarrinhoItens> itens = (ArrayList<CarrinhoItens>) request.getAttribute("itensCarrinho");
ArrayList<CarrinhoItens> itensRemovidos = (ArrayList<CarrinhoItens>) request.getAttribute("itensRemovidosCarrinho");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<title>Carrinho de compras</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="Styles/StyleCarrinhoV3.css">
<script src="scripts/produtosv4.js"></script>

</head>

<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
		<a href='http://localhost:8080/les-ecommerce-vinhos/paginaInical.html'>	<img src="imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3"></a>
			<div class="navbar-nav ms-auto">
				<p style="color: white;">
					Cliente atual:
					<%=request.getParameter("id")%></p>
			</div>
		</div>
	</nav>
	<div class="page-body">

		<div class="row content-carrinho">
			<div class="col-8 m-4">
				<div class="col-12 m-4" style="margin-bottom: 1rem !important;">
					<h3>Items adicionados ao Carrinho 🛒</h3>
					<hr>
					<%
					double total = 0.0;
					for (int i = 0; i < itens.size(); i++) {
						total += itens.get(i).getProduto().getPro_preco_venda() * itens.get(i).getQuantProd();
						int id = itens.get(i).getId();
					%>

					<!-- Inicio do produto no carrinho -->
					<div class="col-12">
						<div class="row" style="margin-left: 30px;">
							<div class="card mb-3">
								<div class="row g-0">
									<div class="col-md-2">
									 <div class="image-container" style="background-color: #f2f2f2;">
										<img src="imagens/produtos/<%=itens.get(i).getProduto().getCodigo_barra()%>.png"
											alt="Imagem do Produto" class="img-fluid rounded-start">
									</div>
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h6 class="card-title"><%=itens.get(i).getProduto().getDesc()%></h6>
											<div class="card-options">
												<div class="card-text">
													<div class="d-flex justify-content-between align-items-center mb-3">
													    <button  id='btn-decrementar-<%=i%>' class="btn btn-sm btn-outline-secondary"
													        onclick="decrementarQuantidade(<%=id%>,<%=i%>)">-</button>
													    <span id="quantity<%=i%>" class="quantity">x <%=itens.get(i).getQuantProd()%></span>
													    <button id='btn-incrementar-<%=i%>' class="btn btn-sm btn-outline-secondary"
													        onclick="incrementarQuantidade(<%=id%>,<%=i%>)">+</button>
													</div>
												</div>
												<!-- Botão Remover -->
												<div class="d-grid">
													<button class="btn btn-sm btn-outline-danger"
														onclick="alterarStatusItem(<%=itens.get(i).getCarrinho().getId()%>, <%=itens.get(i).getProduto().getId()%>, <%=itens.get(i).getQuantProd()%>,'Removido pelo usuario',1)">Remover</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="card-body text-end">
											<p class="card-text fs-4">
												R$
												<%=itens.get(i).getProduto().getPro_preco_venda()%></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- fim do produto no carrinho -->
					<%
					}
					%>
				</div>
			</div>

			<div class="col-3 m-4 subtotal">
				<div class="Content">
					<h2>Sub Total</h2>
					<br> <span id="totalCarriho" style="font-size: 24px; color: orange;"><strong>R$
							<%=total%></strong></span> <br> 
					<button 
						id="btn-finalizar-compra" 
						class="btn btn-primary" 
						onclick="redirecionarParaFinalizarCompra(<%=request.getParameter("id")%>)">
							Finalizar Compra
					</button>

				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-8 m-4">
			<div class="col-12 m-4" style="margin-bottom: 1rem !important;">
				<h3>Items Removidos do Carrinho 🛒🗑️</h3>
				<hr>
				<%
				for (int i = 0; i < itensRemovidos.size(); i++) {
				%>

				<!-- Inicio do produto no carrinho -->
				<div class="col-12">
					<div class="row" style="margin-left: 30px;">
						<div class="card mb-3">
							<div class="row g-0">
								<div class="col-md-2">
								 <div class="image-container" style="background-color: #f2f2f2;">
									<img src="imagens/produtos/<%=itensRemovidos.get(i).getProduto().getCodigo_barra()%>.png"
										alt="Imagem do Produto" class="img-fluid rounded-start">
								</div>
								</div>
								<div class="col-md-8">
									<div class="card-body">
										<h6 class="card-title"><%=itensRemovidos.get(i).getProduto().getDesc()%></h6>
										<div class="card-options">
											<div class="card-text">
												<div
													class="d-flex justify-content-between align-items-center mb-3 ">
													<span id="quantity1" class="quantity"> x <%=itensRemovidos.get(i).getQuantProd()%></span>
												</div>
												<div
													class="d-flex justify-content-between align-items-center mb-3 ">
													<span id="quantity1" class="quantity"><%=itensRemovidos.get(i).getMotivoRemocao()%></span>
												</div>
											</div>
											<!-- Botão Remover -->
											<div class="d-grid">
												<button class="btn btn-sm btn-outline-primary"
													onclick="alterarStatusItem(<%=itensRemovidos.get(i).getCarrinho().getId()%>, <%=itensRemovidos.get(i).getProduto().getId()%>, <%=itensRemovidos.get(i).getQuantProd()%>,' ',0)">
													Voltar ao Carrinho
												</button>
													
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="card-body text-end">
										<p class="card-text fs-4">
											R$
											<%=itensRemovidos.get(i).getProduto().getPro_preco_venda()%></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- fim do produto no carrinho -->
				<%
				}
				%>
			</div>
		</div>
	</div>
  <footer class="p-4 text-light text-center footer fixed-bottom" style="background: black;">
			Desenvolvido por Caynan e Raquel 
		</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>