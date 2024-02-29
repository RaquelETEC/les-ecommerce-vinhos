<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
	<%@ page import="model.entity.CarrinhoItem" %>
		<%@ page import="java.util.ArrayList" %>
			<!DOCTYPE html>
			<html lang="pt-br">

			<head>
				<meta charset="utf-8">
				<title>Carrinho</title>
				<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
				<link rel="stylesheet" href="Styles/StyleCarrinho.css">
			</head>

			<body>
				<nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
					<div class="container-fluid">
						<img src="imagens/logo-vinho.PNG" alt="Logo Vinho" style="width: 200px;" class="mx-3">
						<div class="navbar-nav ms-auto">
							<a href="login.html" class="btn btn-light ms-2">LOGIN</a>
							<a href="gerenciamento.html" class="btn btn-light ms-2">GERENCIAMENTO</a>
						</div>
					</div>
				</nav>
				<div class="page-body">
					<div class="row">
						<div class="col-8 m-4" >
							<div class="col-12 m-4" style="margin-bottom: 1rem!important;">
								<h1>Carrinho de Compras 🛒</h1>
								<hr>
							</div>
								<div class="row" style="margin-left: 30px;">
									<div class="card mb-3">
										<div class="row g-0">
											<div class="col-md-2">
												<img src="imagens/produtos/001.jpg" class="img-fluid rounded-start"
													alt="...">
											</div>
											<div class="col-md-8">
												<div class="card-body">
													<h5 class="card-title">Espumante 1</h5>
													<p class="card-text">
													<div class="d-flex justify-content-between align-items-center mb-3 ">
														<button class="btn btn-sm btn-outline-secondary"
															onclick="decrementarQuantidade('quantity1', 'price1')">-</button>
														<span id="quantity1" class="quantity">1</span>
														<button class="btn btn-sm btn-outline-secondary"
															onclick="incrementarQuantidade('quantity1', 'price1')">+</button>
													</div>
												</div>
												</p>
											</div>
										</div>
									</div>
								</div>
								<div class="row" style="margin-left: 30px;">
									<div class="card mb-3">
										<div class="row g-0">
											<div class="col-md-2">
												<img src="imagens/produtos/001.jpg" class="img-fluid rounded-start"
													alt="...">
											</div>
											<div class="col-md-8">
												<div class="card-body">
													<h5 class="card-title">Espumante 2</h5>
													<p class="card-text"><small class="text-body-secondary"> - 1 + </small>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-3 m-4 subtotal" >
								<div class="Content">
									<h2>Sub Total</h2>
									<br>
									R$ 1499
									<br>
									<button>Fazer Pedido</button>
								</div>
							</div>
									
						</div>						
					
					</div>
					
					<div class="row">
						<div class="col-8 m-4" >
							<div class="col-12 m-4" style="margin-bottom: 1rem!important;">
								<h3>Items Removidos do Carrinho 🛒🗑️</h3>
								<hr>
							<div class="col-12">
								<div class="row">
									<div class="card mb-3">
										<div class="row g-0">
											<div class="col-md-2">
												<img src="imagens/produtos/001.jpg" class="img-fluid rounded-start"
													alt="...">
											</div>
											<div class="col-md-8">
												<div class="card-body">
													<h5 class="card-title">Espumante 1</h5>
													<p class="card-text">
													<div class="d-flex justify-content-between align-items-center mb-3 ">
														<button class="btn btn-sm btn-outline-secondary"
															onclick="decrementarQuantidade('quantity1', 'price1')">-</button>
														<span id="quantity1" class="quantity">1</span>
														<button class="btn btn-sm btn-outline-secondary"
															onclick="incrementarQuantidade('quantity1', 'price1')">+</button>
													</div>
												</div>
												</p>
											</div>
										</div>
									</div>
								</div>
								<div class="row" >
									<div class="card mb-3">
										<div class="row g-0">
											<div class="col-md-2">
												<img src="imagens/produtos/001.jpg" class="img-fluid rounded-start"
													alt="...">
											</div>
											<div class="col-md-8">
												<div class="card-body">
													<h5 class="card-title">Espumante 2</h5>
													<p class="card-text"><small class="text-body-secondary"> - 1 + </small>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>		
					</div>
				</div>
				<footer class="p-4 text-light text-center " style="background: black;">
					Desenvolvido por Caynan e Raquel
				</footer>

				<script
					src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

			</body>

			</html>