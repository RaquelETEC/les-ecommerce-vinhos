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
ArrayList<Cupons> listaCuponsPromocionais = (ArrayList<Cupons>) request.getAttribute("listaCuponsPromocional");

//listagem de cupons de troca
ArrayList<Cupons> listaCuponsDeTroca = (ArrayList<Cupons>) request.getAttribute("listaCuponsTroca");

//Listagem de cartoes 
ArrayList<CartaoDeCredito> listaCartoesCredito = (ArrayList<CartaoDeCredito>) request.getAttribute("listaCartoes");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pedido de venda</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="Styles/StyleFinalizaVenda.css">
<script src="scripts/telaVenda.js"></script>
<link rel ="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
		<a href='http://localhost:8080/les-ecommerce-vinhos/paginaInical.html'>	<img src="imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3"></a>
			<div class="navbar-nav ms-auto"></div>
		</div>
	</nav>

	<main class="main">
		<div class="col-9 m-3 ">
			<div class="row">
				<h2>Finalizar Pedido</h2>
				<hr>
				<BR>
			</div>
			<div class="content-tab">
			<p class="card-text">Endereço de Entrega</p>
			<div class="Endereco-Container1">
				<div class="card mt-3 styleCards">
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<p id="Nome" class="card-title ">
									<strong></strong>
								</p>
								<p class="card-text">
									<span id="Logradouro"></span>, <span id="Numero"></span>, <span
										id="Bairro"></span>, <span id="Cidade"></span>, <span
										id="Estado"></span> - <span id="Cep"></span>, <span id="Pais"></span>

								</p>

							</div>
							<div class="col-md-3 text-end">
								<button type="button" class="btn btn-success"
									data-toggle="modal" data-target="#modalSelecaoEndereco">Selecionar
									Endereço</button>
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
									<img src="imagens/produtos/<%=itens.get(i).getProduto().getCodigo_barra() %>.png"
										alt="Imagem do Produto" class="img-fluid rounded-start"
										style="max-width: 70px; max-height: 70px;">
								</div>
							</div>
							<div class="col-md-9">
								<div class="card-body">
									<h6 class="card-title"><%=itens.get(i).getProduto().getDesc()%></h6>
									<div class="card-options">
										<div class="card-text">
											<div
												class="d-flex justify-content-between align-items-center">
												<span id="quantity<%=i%>" class="quantity">x<%=itens.get(i).getQuantProd()%></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="card-body text-end">
									<p class="card-text fs-5">
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
									<img id="idCupomSrc" src="" class="img-fluid rounded-start"
										style="max-width: 70px; max-height: 70px;">
								</div>
								<!-- Parte direita com o nome do cupom e a data de vencimento -->
								<div class="col-md-8">
									<p id="idCupomDesc" class="card-title">
										<strong></strong>
									</p>
									<p id="idCupomValor" class="card-text"></p>
								</div>
								<div class="col-md-3 text-end ">
									<button type="button" class="btn btn-success"
										data-toggle="modal" data-target="#modalCupomP">Selecionar
										Cupom</button>
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
						<button class="btn btn-primary" onClick="addCupomTroca()">+</button>
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
									<p class="text-md-start">Total Pagamento</p>
									<p class="text-md-start">À pagar</p>
									
								</div>
								<div class="col-md-6">
									<p id="idTotalProduto"  class="text-md-end">R$ <%=total %></p>
									<p id="idTotalFrete" class="text-md-end">R$ 00,00</p>
									<p id="idTotalCupomP" class="text-md-end">R$ 00,00</p>
									<p id="idTotalCupomT" class="text-md-end">R$ 00,00</p>
									<p id="idTotalPedido" class="text-md-end">R$ <%=total %></p>
									<p id="idTotalPagamento" class="text-md-end">R$ 00,00</p>
									<p id="idTotalApagar" class="text-md-end">R$ 00,00</p>
									
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="div-adicionar">
					<div class="col-md-9">Formas de Pagamento</div>
						<div class="col-md-3 text-end ">
							<button type="button" 
							class="btn btn-success" 
							data-toggle="modal" 
							data-target="#modalCartoes">
							Selecionar Cartão de crédito
							</button>
						</div>
					</div>
				<hr>
				<div class="card mt-3 styleCards CartoesSection">
					<div class="card-body">
						<div class="row">
							<div class="col-2">
								<p id="cartao" class="text-md-start">Nome do Cartão</p>
							</div>
							<div class="col-2">
								<p id="cartao" class="text-md-start">Numero</p>
							</div>
							<div class="col-2">
								<p class="text-md-start">Bandeira</p>
							</div>
							<div class="col-1">
								<p class="text-md-start">Código</p>
							</div>
							<div class="col-2">
								<p class="text-center">Valor</p>
							</div>
							<div class="col-3">
								<p class="text-md-start">Opções</p>
							</div>
						</div>
						<div class="formas-pagamento-container" id="pagamentoContainer">
						</div>
					</div>
				</div>

			</div>
			<br>
			<button class="btn btn-success" onClick="validarPedido(<%=request.getParameter("idCliente")%>)">Finalizar Compra</button>

			<!-- ------------------------------------- Fim da tela  ---------------------------------- -->
			<!-- Sessão de modals -->
			
				<div class="modal fade" id="modalSelecaoEndereco" tabindex="-1"
					aria-labelledby="modalSelecaoEnderecoLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="modalSelecaoEnderecoLabel">Seleção
									de Endereço</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Fechar">
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
											<div
												class="col-md-2 d-flex align-items-center justify-content-center">
												<!-- Adicionando classes para alinhar vertical e horizontalmente -->
												<div class="custom-radio">
													<!-- Adicione um ID exclusivo para cada radio button -->
													<input type="radio" id="endereco<%=i%>"
														name="enderecoSelecionado" value="<%=endereco.getId()%>">
													<!-- Adicione o rótulo associado ao radio button -->
													<label for="endereco<%=i%>"></label>
												</div>
											</div>
											<div class="col-md-10">
												<p id="Nome<%=endereco.getId()%>" class="card-title ">
													<strong><%=endereco.getNome()%> </strong>
												</p>
												<p class="card-text">
													<span id="Logradouro<%=endereco.getId()%>"><%=endereco.getLogradouro()%></span>,
													<span id="Numero<%=endereco.getId()%>"><%=endereco.getNumero()%></span>,
													<span id="Bairro<%=endereco.getId()%>"><%=endereco.getBairro()%></span>,
													<span id="Cidade<%=endereco.getId()%>"><%=endereco.getCidade()%></span>,
													<span id="Estado<%=endereco.getId()%>"><%=endereco.getEstado()%></span>
													- <span id="Cep<%=endereco.getId()%>"><%=endereco.getCep()%></span>,
													<span id="Pais<%=endereco.getId()%>"><%=endereco.getPais()%></span>
												</p>
											</div>
										</div>
									</div>
								</div>
								<%
								}
								%>
								<hr>
								<p>Não encontrou o endereço desejado?</p>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" data-toggle="modal"
									data-target="#modalCadastroEndereco">Adicionar endereço</button>
	
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" data-toggle="modal" 
									onclick="salvarEndereco()">Salvar</button>
							</div>
						</div>
					</div>
				</div>
			<!-- cadastrar endereco -->

			<div class="modal fade" id="modalCadastroEndereco" tabindex="-1"
				aria-labelledby="modalCadastroEndereco" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalSelecaoEnderecoLabel">Seleção
								de Endereço</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Fechar">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body"
							style="max-height: 70vh; overflow-y: auto;">
							<!-- Seu formulário de cadastro aqui -->
							<form name="frmcliente">
								<fieldset>
									<input type="text" name="id" id="id" style="display: none" />
									<div class="row">
										<div class="col-md-12 mb-4">
											<label class="form-label" for="nome">Nome*</label> <input
												type="text" name="nome" id="nome"
												class="form-control form-control-lg" required />

										</div>
										<div class="col-md-6 mb-4">
											<label class="form-label" for="tipoResidencia">Tipo
												de Residência *</label> <select class="form-select form-select-lg"
												name="typeTipoResidencia" id="typeTipoResidencia" required>
												<option value="" disabled selected></option>
												<option value="casa">Casa</option>
												<option value="apartamento">Apartamento</option>
												<option value="condominio">Condomínio</option>
											</select>
										</div>
										<div class="col-md-6 mb-4">
											<label class="form-label" for="tipoLogradouro">Tipo
												Logradouro*</label> <select class="form-select form-select-lg"
												name="typeTipoLogradouro" id="typeTipoLogradouro" required>
												<option value="" disabled selected></option>
												<option value="Rua">Rua</option>
												<option value="Estrada">Estrada</option>
												<option value="Avenida">Avenida</option>
												<option value="Praça">Praça</option>
												<option value="Corredor">Corredor</option>
												<option value="Alameda">Alameda</option>
												<option value="Distrito">Distrito</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-4">
											<label class="form-label" for="logradouro">Logradouro
												*</label> <input type="text" name="typeLogradouro"
												id="typeLogradouro" class="form-control form-control-lg"
												required />
										</div>
										<div class="col-md-6 mb-4">
											<label class="form-label" for="numero">N° *</label> <input
												type="text" name="typeNumero" id="typeNumero"
												class="form-control form-control-lg" required />
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-4">
											<label class="form-label" for="typeBairro">Bairro*</label> <input
												type="text" name="typeBairro" id="typeBairro"
												class="form-control form-control-lg" required />
										</div>
										<div class="col-md-6 mb-4">
											<label class="form-label" for="typeCidade">Cidade*</label> <input
												type="text" name="typeCidade" id="typeCidade"
												class="form-control form-control-lg" required />
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-4">
											<label class="form-label" for="typeEstado">Estado*</label> <input
												type="text" name="typeEstado" id="typeEstado"
												class="form-control form-control-lg" required />
										</div>
										<div class="col-md-6 mb-4">
											<label class="form-label" for="typeCep">CEP*</label> <input
												type="text" name="typeCep" id="typeCep"
												class="form-control form-control-lg" required />
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-4">
											<label class="form-label" for="typePais">País*</label> <input
												type="text" name="typePais" id="typePais"
												class="form-control form-control-lg" required />
										</div>
										<div class="col-md-6 mb-4">
											<label class="form-label" for="observacoes">Observações</label>
											<input type="text" name="observacoes" id="observacoes"
												class="form-control form-control-lg" />
										</div>
									</div>
									<label class="form-label" for="cadastrarEndNopPerfil">Incluir
										endereço ao perfil?</label> 
										<select class="form-select form-select-lg"
										name="cadastrarEndNopPerfil" id="cadastrarEndNopPerfil"
										required>
										<option value="Sim">Sim</option>
										<option value="Não">Não</option>
									</select> <input type="hidden" name="tipoEndereco" value="ENTREGA">

									<br>
								</fieldset>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-primary btn-lg btn-block"
								id="BotaoCadastrar" type="button"
								onClick="AdicionarNovoEndereco(<%=request.getParameter("idCliente")%>)">Cadastrar</button>
						</div>
					</div>
				</div>
			</div>


			<!-- Modal Cupom -->
			<div class="modal fade" id="modalCupomP" tabindex="-1"
				aria-labelledby="modalCupomP" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalSelecaoEnderecoLabel">Seleção de cupom promocional</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Fechar">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<%
							for (int i = 0; i < listaCuponsPromocionais.size(); i++) {
								Cupons cupom = listaCuponsPromocionais.get(i); // Crie um novo objeto Endereco
							%>
							<div class="card mt-3">
								<div class="card-body">
									<div class="row">
										<div
											class="col-md-2 d-flex align-items-center justify-content-center">
											<!-- Adicionando classes para alinhar vertical e horizontalmente -->
											<div class="custom-radio">
												<input type="radio" id="cupomselect<%=cupom.getId()%>"
													name="cupomsSelecionadoP" value="<%=cupom.getId()%>">
												<label for="cupomselect<%=cupom.getId()%>"></label>
											</div>
										</div>
										<div
											class="col-md-2 d-flex align-items-center justify-content-center">
											<img id="imagemCupom<%=cupom.getId()%>"
												src="imagens/assets/<%=cupom.getImg()%>" alt="Imagem do Cupom"
												class="img-fluid">
										</div>
										<div class="col-md-8">
											<p id="NomeCupom<%=cupom.getId()%>" class="card-title ">
												<strong><%=cupom.getDesc()%> </strong>
											</p>
											<p class="card-text">
												<span id="ValorCupom<%=cupom.getId()%>"><%=cupom.getValor()%></span>
											</p>
										</div>
									</div>
								</div>
							</div>
							<%
							}
							%>
							<hr>
							<div class="insiraCupom">
								<p>Insira um Cupom de desconto</p>
								<input type="text" name="nome" id="nome"
									class="form-control form-control-lg" required />
							</div>
							<hr>
							<button type="button" class="btn btn-success"
								data-dismiss="modal" data-toggle="modal"
								data-target="#modalCadastroEndereco">Adicionar</button>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								onclick="salvarCupom('cupomsSelecionadoP','modalCupomP')">Salvar</button>
						</div>
					</div>
				</div>
			</div>


			<!-- Modal Cupom -->
			<div class="modal fade" id="modalCupomT" tabindex="-1"
				aria-labelledby="modalCupomT" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalSelecaoCupomLabel">Seleção
								de Cupom</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Fechar">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<%
							for (int i = 0; i < listaCuponsDeTroca.size(); i++) {
								Cupons cupom = listaCuponsDeTroca.get(i); // Crie um novo objeto Endereco
							%>
							<div class="card mt-3">
								<div class="card-body">
									<div class="row">
										<div
											class="col-md-2 d-flex align-items-center justify-content-center">
											<!-- Adicionando classes para alinhar vertical e horizontalmente -->
											<div class="custom-radio">
												<input type="radio" id="cupomTselect<%=cupom.getId()%>"
													name="cupomsSelecionadoT" value="<%=cupom.getId()%>">
												<label for="cupomTselect<%=cupom.getId()%>"></label>
											</div>
										</div>
										<div
											class="col-md-2 d-flex align-items-center justify-content-center">
											<img id="imagemCupomT<%=cupom.getId()%>"
												src="<%=cupom.getImg()%>" alt="Imagem do Cupom"
												class="img-fluid">
										</div>
										<div class="col-md-8">
											<p id="NomeCupomT<%=cupom.getId()%>" class="card-title ">
												<strong><%=cupom.getDesc()%> </strong>
											</p>
											<p class="card-text">
												<span id="ValorCupomT<%=cupom.getId()%>"><%=cupom.getValor()%></span>
											</p>
										</div>
									</div>
								</div>
							</div>
							<%
							}
							%>
							<hr>
							<hr>
							<input type="text" >Adicionar</input>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								onclick="salvarCupom('cupomsSelecionadoT', 'modalCupomT')">Salvar</button>
						</div>
					</div>
				</div>
			</div>



			<!-- Modal cartõess -->
			<div class="modal fade" id="modalCartoes" tabindex="-1"
				aria-labelledby="modalCartoes" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalSelecaoCartaoLabel">Seleção
								de Cartão de Crédito</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Fechar">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<%
							for (int i = 0; i < listaCartoesCredito.size(); i++) {
								CartaoDeCredito cartao = listaCartoesCredito.get(i); // Crie um novo objeto Endereco
							%>
							<div class="card mt-3">
								<div class="card-body">
									<div class="row">
										<div
											class=" col-md-2 d-flex align-items-center justify-content-center">
											<!-- Adicionando classes para alinhar vertical e horizontalmente -->
											<div class="custom-radio">
												<input type="radio" id="cartaoselect<%=cartao.getId()%>"
													name="cartaoSelecionado" value="<%=cartao.getId()%>">
												<label for="cartaoselect<%=cartao.getId()%>"></label>
											</div>
										</div>
										<div
											class="col-md-2 d-flex align-items-center justify-content-center">
											<img id="imagemBandeira<%=cartao.getId()%>"
												src="<%=cartao.getBandeira().getImg()%>"
												alt="Imagem da Bandeira" class="img-fluid">
										</div>
										<div class="col-md-8">
											<p id="nomeCartao<%=cartao.getId()%>" class="card-title ">
												<strong><%=cartao.getNome()%> </strong>
											</p>
											<p class="card-text">
												<span id="numeroCartao<%=cartao.getId()%>"><%=cartao.getNumero()%></span>
												<span id="codSeguranca<%=cartao.getId()%>"><%=cartao.getCodigoSeguranca()%></span>
											</p>
										</div>
									</div>
								</div>
							</div>
							<%
							}
							%>
							<hr>
							<hr>
							<label class="form-label text-center" for="nome">Valor</label> 
							<input type="number" name="valorCartao" id="valorCartao"class="form-control form-control-lg" pattern="^\d*(\.\d{0,2})?$" placeholder="0.00" required>
						</div>
						<div class="modal-footer" style="display: flex;">
						    <div class="row w-100">
						        <div class="col-6">
							        <button onclick="CadastrarCartao() type="button" class="btn btn-success"
									data-dismiss="modal" data-toggle="modal"
									data-target="#modalCadastroCartao">Adicionar Cartão</button>
						        </div>
						        <div class="col-6 text-end">
						            <button onClick="addCartaoCard()" type="button" class="btn btn-primary">Salvar</button>
						        </div>
						    </div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- cadastrar cartao -->
			
			<div class="modal fade" id="modalCadastroCartao" tabindex="-1"
				aria-labelledby="modalCadastroCartao" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalSelecaoCartaoLabel">Cadastrar cartão de Crédito</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Fechar">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body"
							style="max-height: 70vh; overflow-y: auto;">
							<!-- Seu formulário de cadastro aqui -->
							<form name="frmCartao">
								<fieldset>
								<div class="box-input-form" style="display: none;">
									<div class="box-label">
										<label for="typeId">Id</label>
									</div>
									<input type="text" name="typeIdCartao" id="typeId"
										class="input-form input-width-1"
										value="<%=request.getAttribute("idCliente")%>">

								</div>
								<legend>Cartão</legend>
								<div class="row">
									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoNome">Nome
											impresso do cartão</label> <input type="text" name="CartaoNome"
											id="CartaoNome" class="form-control form-control-lg" required />
									</div>

									<div class="col-md-6 mb-4">
										<label class="form-label" for="tipoBandeira">Bandeira</label>
										<select class="form-select form-select-lg" name="tipoBandeira"
											id="tipoBandeira" required>
											<option value="" disabled selected></option>
											<option value="1">Mastercard</option>
											<option value="2">Visa</option>
										</select>
									</div>
								</div>
								<div class="row">

									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoNumero">N° *</label> <input
											type="text" name="CartaoNumero" id="CartaoNumero"
											class="form-control form-control-lg" required />
									</div>

									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoCodigo">Codigo de
											Segurança</label> <input type="text" name="CartaoCodigo"
											id="CartaoCodigo" class="form-control form-control-lg"
											required />
									</div>
								</div>
								<div class="row">
								    <div class="col-6 mb-4">
								        <label class="form-label" for="cadastrarCartNoPerfil">Salvar no perfil?</label>
								        <select class="form-select " name="cadastrarCartNpPerfil" id="cadastrarCartNoPerfil" required>
								            <option value="Sim">Sim</option>
								            <option value="Não">Não</option>
								        </select>
								    </div>
								    <div class="col-6 mb-4">
								        <label class="form-label text-center" for="nome">Valor</label> 
										<input type="number" name="valorCartaoCad" id="valorCartaoCad"class="form-control form-control-lg" pattern="^\d*(\.\d{0,2})?$" placeholder="0.00" required>
								    </div>
								</div>
							</fieldset>
							</form>
						</div>
						<div class="modal-footer">
							<button class="btn btn-primary btn-lg btn-block"
								id="BotaoCadastrar" type="button"
								onClick="AdicionarNovoCartao(<%=request.getParameter("idCliente")%>)">Cadastrar</button>
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