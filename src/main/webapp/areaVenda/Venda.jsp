<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.entity.CarrinhoItens"%>
<%@ page import="model.entity.Endereco"%>
<%@ page import="model.entity.Cupons"%>
<%@ page import="model.entity.CartaoDeCredito"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
//listagem de produtos 
ArrayList<CarrinhoItens> itens = (ArrayList<CarrinhoItens>) request.getAttribute("itens");

//Listagem de endereços
ArrayList<Endereco> listaEntrega = (ArrayList<Endereco>) request.getAttribute("listaEnderecosEntrega");

//listagem de cupons promocionais
//ArrayList<Cupons> listaCuponsPromocionais = (ArrayList<Cupons>) request.getAttribute("listaCuponsPromocional");

//listagem de cupons de troca
//ArrayList<Cupons> listaCuponsDeTroca= (ArrayList<Cupons>) request.getAttribute("listaCuponsPromocional");

//Listagem de cartoes 
//ArrayList<CartaoDeCredito> listaCartoesCredito= (ArrayList<CartaoDeCredito>) request.getAttribute("listaCartoes");

%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Venda</title>
<link rel="stylesheet" href="Styles/StyleFinalizaVenda.css">
<script src="scripts/telaVenda.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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

			<p  class="card-text">Endereço de Entrega</p>
			<div class="Endereco-Container1">
				<div class="card mt-3 styleCards">
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<p id="Nome"class="card-title "></p>
								<p class="card-text">
								<span id="Logradouro"></span>,
							    <span id="Numero"></span>,
							    <span id="Bairro"></span>,
							    <span id="Cidade"></span>,
							    <span id="Estado"></span> - 
							    <span id="Cep"></span>,
							    <span id="Pais"></span>
								
								</p>
								
							</div>
							<div class="col-md-3 text-end">
							    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalSelecaoEndereco">Selecionar Endereço</button>
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
					<div class="card mt-3 styleCards">
						<div class="card-body">
							<div class="row">
								<!-- Parte esquerda com a imagem do cupom -->
								<div class="col-md-1">
									<img src="imagens/assets/CupomFrete.png"
										class="img-fluid rounded-start"
										style="max-width: 70px; max-height: 70px;">
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
					<div class="col-md-9">Cupom de Troca</div>
					<div class="col-md-3 text-end ">
						<button class="btn btn-primary" onClick="addCupomTroca(1)">+</button>
					</div>


				</div>
				<hr>
				<div class="cupom-promocional-container" id="cupomContainer">

				</div>
				<!-- Fim do Card de Cupom de troca  -->

				<br>
				<p>Totais</p>
				<div class="Totais-container">
					<div class="card mt-3 styleCards">
						<div class="card-body">
							<div class="row">
								<div class="col-md-6">
									<p class="text-md-start">Total Produto</p>
									<p class="text-md-start">Total Frete</p>
									<p class="text-md-start">Total Desconto</p>
									<p class="text-md-start">Total Trocas</p>
									<p class="text-md-start">Total Pedido</p>
								</div>
								<div class="col-md-6">
									<p class="text-md-end">R$ 00,00</p>
									<p class="text-md-end">R$ 00,00</p>
									<p class="text-md-end">R$ 00,00</p>
									<p class="text-md-end">R$ 00,00</p>
									<p class="text-md-end">R$ 00,00</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="div-adicionar">
					<div class="col-md-9">Formas de Pagamento</div>
					<div class="col-md-3 text-end ">
						<button class="btn btn-success" onClick="addCartao(1)">Adicionar
							Forma de Pagamento</button>
					</div>
				</div>
				<hr>
				<div class="card mt-3 styleCards">
					<div class="card-body">
						<div class="row">
							<div class="col-md-2">
								<p class="text-md-start">Nome</p>
							</div>
							<div class="col-md-2">
								<p class="text-md-start">Cartão</p>
							</div>
							<div class="col-md-2">
								<p class="text-md-start">Bandeira</p>
							</div>
							<div class="col-md-2">
								<p class="text-md-start">Valor</p>
							</div>
						</div>
						<div class="formas-pagamento-container" id="pagamentoContainer">
						</div>
					</div>
				</div>
			</div>

		</div>
		<br>
		<button class="btn btn-success" onClick="">Finalizar Compra</button>
		
	
<div class="modal fade" id="modalSelecaoEndereco" tabindex="-1" aria-labelledby="modalSelecaoEnderecoLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalSelecaoEnderecoLabel">Seleção de Endereço</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
			<%
			for (int i = 0; i < listaEntrega.size(); i++) {
			Endereco endereco = listaEntrega.get(i); // Crie um novo objeto Endereco
			%>
			<div class="card mt-3">
				<div class="card-body">
					<div class="row">
						<div class="col-md-2 d-flex align-items-center justify-content-center"> <!-- Adicionando classes para alinhar vertical e horizontalmente -->
				   			<div class="custom-radio">
						        <!-- Adicione um ID exclusivo para cada radio button -->
						        <input type="radio" id="endereco<%=i%>" name="enderecoSelecionado" value="<%=endereco.getId()%>">
						        <!-- Adicione o rótulo associado ao radio button -->
						        <label for="endereco<%=i%>"></label>
						    </div>			            
				         </div>
					 	<div class="col-md-10">
							<h6 id="Nome<%=i%>class="card-title "><%=endereco.getNome()%> </h6>
						    <p class="card-text">
							    <span id="Logradouro<%=i%>"><%= endereco.getLogradouro() %></span>,
							    <span id="Numero<%=i%>"><%= endereco.getNumero() %></span>,
							    <span id="Bairro<%=i%>"><%= endereco.getBairro() %></span>,
							    <span id="Cidade<%=i%>"><%= endereco.getCidade() %></span>,
							    <span id="Estado<%=i%>"><%= endereco.getEstado() %></span> - 
							    <span id="Cep<%=i%>"><%= endereco.getCep() %></span>,
							    <span id="Pais<%=i%>"><%= endereco.getPais() %></span>
							</p>
			            </div>
			        </div>
	               </div>
	           </div>
			   <% } %>
			    <hr>
			    <p>Não encontrou o endereço desejado?</p>
			    <button type="button" class="btn btn-success" onclick="cadastrarNovoEndereco()">Cadastrar Novo Endereço</button>
			</div>
            <div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="salvarEndereco()">Salvar</button>
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