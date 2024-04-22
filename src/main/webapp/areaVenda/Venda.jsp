<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.entity.CarrinhoItens"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<CarrinhoItens> itens = (ArrayList<CarrinhoItens>) request.getAttribute("itens");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Venda</title>
<link rel="stylesheet" href="Styles/StyleFinalizaVenda.css">
<script src="scripts/telaVenda.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
</head>

<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<img src="imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3">
			<div class="navbar-nav ms-auto"></div>
		</div>
	</nav>

	<main class="main">
		<div class="col-8 m-3 ">
			<div class="row">
				<h2>Finalizar Pedido</h2>
				<hr>
				<BR>
			</div>

			<p>Endereço de Entrega</p>
			<div class="Endereco-Container1">
				<div class="card mt-3 styleCards">
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<p class="card-title ">
									Raquel <span style="background-color: #56b07a; color: white;"
										class="badge rounded-pill">Padrão</span>

								</p>
								<p class="card-text">teste , teste, teste, teste,</p>
							</div>
							<div class="col-md-3 text-end ">
								<a
									href="/les-ecommerce-vinhos/areaVenda/VendaTrocaEndereco.html"
									class="btn btn-success">Selecionar Endereço
								</a>

							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Fim do Card de Endereço  -->
			<br>
			<p>Produtos</p>
			<div class="Produtos-Container1">
				<div class="col-12">
					<hr>
					<%
					double total = 0.0;
					for (int i = 0; i < itens.size(); i++) {
						total += itens.get(i).getProduto().getPro_preco_venda() * itens.get(i).getQuantProd();
						int id = itens.get(i).getId();
					%>

					<!-- Inicio do produto no carrinho -->
					<div class="card mt-3"
						style="background-color: #F0F0F0; width: 100%">
						<div class="row g-0">
							<div class="col-md-1">
								<div class="image-container" style="background-color: #f2f2f2;">
									<img src="<%=itens.get(i).getProduto().getImg()%>"
										alt="Imagem do Produto" class="img-fluid rounded-start"
										style="max-width: 100px; max-height: 100px;">
								</div>
							</div>
							<div class="col-md-9">
								<div class="card-body">
									<h6 class="card-title"><%=itens.get(i).getProduto().getDesc()%></h6>
									<div class="card-options">
										<div class="card-text">
											<div
												class="d-flex justify-content-between align-items-center mb-3">
												<span id="quantity<%=i%>" class="quantity">x<%=itens.get(i).getQuantProd()%></span>
											</div>
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
						
				<%
				}
				%>
				</div>
			<!-- fim do produto no carrinho -->
			<br>
			<p>Cupom Promocional</p>
			<hr>
			<div class="cupom-promocional-container">
				<div class="card mt-3 styleCards" >
					 <div class="card-body">
				            <div class="row">
				                <!-- Parte esquerda com a imagem do cupom -->
				                <div class="col-md-1">
				                    <img src="imagens/assets/CupomFrete.png" alt="Imagem do Cupom" class="img-fluid">
				                </div>
				                <!-- Parte direita com o nome do cupom e a data de vencimento -->
				                <div class="col-md-8">
				                    <h5 class="card-title">Nome do Cupom 1</h5>
				                    <p class="card-text">Data de vencimento: 20/03/2024</p>
				                </div>
				                <div class="col-md-3 text-end ">
								<a
									href="/les-ecommerce-vinhos/areaVenda/VendaTrocaEndereco.html"
									class="btn btn-success">Selecionar Cupom</a>
				                </div>
				            </div>
				        </div>
				</div>
			</div>
			<!-- Fim do Card de Cupom promocional  -->
			<br>
			
			<div class="div-adicionar">
			    <div class="col-md-9"> Cupom de Troca</div>
			     <div class="col-md-3 text-end ">
			     	<button  
			     	class="btn btn-primary" 
			        onClick="addCupomTroca(1)">+</button>
			     </div>
			   
			    	
			</div>
			<hr>	
			<div class="cupom-promocional-container"  id="cupomContainer">
				
			</div>
			<!-- Fim do Card de Cupom de troca  -->
			
			
			
			
			
			</div>
		</div>
	</main>


	<footer class="p-4 text-light text-center footer">
		Desenvolvido por Caynan e Raquel </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>