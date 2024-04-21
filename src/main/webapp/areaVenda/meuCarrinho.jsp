<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.entity.CarrinhoItens"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<CarrinhoItens> produtos = (ArrayList<CarrinhoItens>) request.getAttribute("itemsCarrinho");
ArrayList<CarrinhoItens> produtosRemovidos = (ArrayList<CarrinhoItens>) request.getAttribute("itemsRemovidosCarrinho");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<title>Carrinho</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="Styles/StyleCarrinhoV1.css">
<link rel="javascript" href="script/produtos.js">
</head>

<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<img src="imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3">
			<div class="navbar-nav ms-auto">
				<p style="color: white;">Cliente atual: 20</p>
			</div>
		</div>
	</nav>
	<div class="page-body">

		<div class="row">
			<div class="col-8 m-4">
				<div class="col-12 m-4" style="margin-bottom: 1rem !important;">
					<h3>Items adicionados ao Carrinho 🛒</h3>
					<hr>
					<%
					double total = 0.0;
					for (int i = 0; i < produtos.size(); i++) {
						total += produtos.get(i).getProduto().getPro_preco_venda() * produtos.get(i).getQuantProd();
					%>

					<!-- Inicio do produto no carrinho -->
					<div class="col-12">
						<div class="row" style="margin-left: 30px;">
							<div class="card mb-3">
								<div class="row g-0">
									<div class="col-md-2">
										<img src="<%=produtos.get(i).getProduto().getImg()%>"
											alt="Imagem do Produto" class="img-fluid rounded-start">
									</div>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-title"><%=produtos.get(i).getProduto().getDesc()%></h5>
											<div class="card-options">
												<p class="card-text">
												<div
													class="d-flex justify-content-between align-items-center mb-3 ">
													<button class="btn btn-sm btn-outline-secondary"
        												onclick="decrementarQuantidade('<%=produtos.get(i).getProduto().getId() %>', '<%=produtos.get(i).getCarrinho().getId() %>')">-</button>
														<span id="quantity1" class="quantity"><%=produtos.get(i).getQuantProd()%></span>
													<button class="btn btn-sm btn-outline-secondary"
														onclick="incrementarQuantidade('quantity1', 'price1')">+</button>
												</div>
												</p>
												<!-- Botão Remover -->
												<div class="d-grid">
													<button class="btn btn-sm btn-outline-danger"
														onclick="removerProduto('product1')">Remover</button>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="card-body text-end">
											<p class="card-text fs-4">
												R$
												<%=produtos.get(i).getProduto().getPro_preco_venda()%></p>
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
					<br> <span style="font-size: 24px; color: orange;"><strong>R$
							<%=total%></strong></span> <br> <a
						href="/les-ecommerce-vinhos/areaVenda/Venda.html">
						<button class="btn btn-primary">Fazer Pedido</button>
					</a>
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
				for (int i = 0; i < produtosRemovidos.size(); i++) {
				%>

				<!-- Inicio do produto no carrinho -->
				<div class="col-12">
					<div class="row" style="margin-left: 30px;">
						<div class="card mb-3">
							<div class="row g-0">
								<div class="col-md-2">
									<img src="<%=produtosRemovidos.get(i).getProduto().getImg()%>"
										alt="Imagem do Produto" class="img-fluid rounded-start">
								</div>
								<div class="col-md-4">
									<div class="card-body">
										<h5 class="card-title"><%=produtosRemovidos.get(i).getProduto().getDesc()%></h5>
										<p class="card-text">
										<div
											class="d-flex justify-content-between align-items-center mb-3 ">
											<button class="btn btn-sm btn-outline-secondary"
												onclick="decrementarQuantidade('quantity1', 'price1')">-</button>
											<span id="quantity1" class="quantity"><%=produtosRemovidos.get(i).getQuantProd()%></span>
											<button class="btn btn-sm btn-outline-secondary"
												onclick="incrementarQuantidade('quantity1', 'price1')">+</button>
										</div>
										</p>
										<!-- Botão Remover -->
										<div class="d-grid">
											<button class="btn btn-sm btn-outline-primary"
												onclick="removerProduto('product1')">Voltar ao
												Carrinho</button>
										</div>
									</div>
								</div>
								<div class="col-md-3"></div>
								<div class="col-md-3">
									<div class="card-body text-end">
										<p class="card-text fs-4">
											R$
											<%=produtosRemovidos.get(i).getProduto().getPro_preco_venda()%></p>
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
	<footer class="p-4 text-light text-center " style="background: black;">
		Desenvolvido por Caynan e Raquel </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>