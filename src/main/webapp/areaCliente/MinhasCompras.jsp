<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Cliente"%>
<%@ page import="model.entity.PedidoVenda"%>
<%@ page import="model.entity.TiposStatusItensPedido "%>
<%@ page import="model.entity.PedidoItens"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>

<%
Cliente cliente = (Cliente) request.getAttribute("cliente");
@SuppressWarnings("unchecked")
ArrayList<PedidoVenda> listaPedidos = (ArrayList<PedidoVenda>) request.getAttribute("listaPedidos");
%>
<%
Gson gson = new GsonBuilder().create();
String pedidosJson = gson.toJson(listaPedidos);
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Minhas Compras</title>
<link rel="icon" href="../imagens/favicon.png">
<link rel="stylesheet" href="../Styles/StyleMinhasCompras.css">
<script src="../scripts/minhasCompras.js" defer></script>
<script>
  var pedidos = <%= pedidosJson %>;
</script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>

	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<a
				href="http://localhost:8080/les-ecommerce-vinhos/paginaInical.html"><img
				src="../imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3"></a>

			<div class="navbar-nav ms-auto"></div>
		</div>
	</nav>
	<main>
		<div class="container-main">

			<!-- conteudo do perfil da direita -->
			<div class="container-perfil">
				<div class="box-name-perfil">
					<img src="../imagens/assets/icon-box-image-name.png" alt=""
						class="img-name-perfil">
					<div class="box-name-e-nivel">
						<p class="text-name">Usuário</p>
						<p class="text-nivel">NIVEL: 1</p>
					</div>
				</div>

				<div class="box-dados-perfil">
					<button class="button-dados-perfil">
						<img src="../imagens/assets/icons-left-perfil-1.png" alt=""
							class="img-icons-perfil"> <span>Minha Conta</span>
					</button>
					<div class="box-exibir-opcoes">
						<button
							onclick="window.location.href='Perfil.html?id=<%=cliente.getId()%>';">
							Perfil</button>
						<button class="cartoes"
							onclick="window.location.href='MeusCartoes.html?id=<%=cliente.getId()%>';">Cartões</button>
						<button class="enderecos"
							onclick="window.location.href='MeusEnderecos.html?id=<%=cliente.getId()%>';">Endereços</button>
						<button
							onclick="window.location.href='/les-ecommerce-vinhos/areaCliente/TrocarSenha.html?id=<%=cliente.getId()%>';">Trocar
							Senha</button>
					</div>

					<button class="button-dados-perfil">
						<img src="../imagens/assets/icons-left-perfil-2.png" alt=""
							class="img-icons-perfil"> <span class="ativo"
							onclick="window.location.href='/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=<%=cliente.getId()%>';">Minhas
							Compras</span>
					</button>


					<button class="button-dados-perfil">
						<img src="../imagens/assets/icons-left-perfil-3.png" alt=""
							class="img-icons-perfil"> <span
							onclick="window.location.href='Notificações.jsp';">Notificacoes</span>
					</button>


					<button class="button-dados-perfil">
						<img src="../imagens/assets/icons-left-perfil-4.png" alt=""
							class="img-icons-perfil"> <span
							onclick="window.location.href='MeusCupons.html?id=<%=cliente.getId()%>';">Meus
							Cupons</span>
					</button>

				</div>

			</div>
			<!-- acaba aqui  -->

			<div class="col-md-9">
				<div class="box-top-register">
					<!-- titulo do cabecalho -->
					<h1 class="text-perfil">Minhas compras</h1>
				</div>
				<div class="custom-container  data-status=tabs">
					<div class="row tabs-content mt-2">
						<div class="col-md-12">
							<ul class="nav nav-pills nav-justified" id="minhasAbas"
								role="tablist">
								<li class="nav-item" role="presentation">
									<button onclick="filterStatus('Tudo')" class="nav-link active"
										id="abaTudo-tab" data-bs-toggle="tab"
										data-bs-target="#abaTudo" type="button" role="tab"
										aria-controls="abaTudo" aria-selected="true">Tudo</button>
								</li>
								<li class="nav-item" role="presentation">
									<button onclick="filterStatus('EM PROCESSAMENTO')"
										class="nav-link" id="abaEmProcessamento-tab"
										data-bs-toggle="tab" data-bs-target="#abaEmProcessamento"
										type="button" role="tab" aria-controls="abaEmProcessamento"
										aria-selected="false">Em processamento</button>
								</li>
								<li class="nav-item" role="presentation">
									<button onclick="filterStatus('EM TRANSPORTE')"
										class="nav-link" id="abaTransporte-tab" data-bs-toggle="tab"
										data-bs-target="#abaTransporte" type="button" role="tab"
										aria-controls="abaTransporte" aria-selected="false">Em
										transporte</button>
								</li>
								<li class="nav-item" role="presentation">
									<button onclick="filterStatus('ENTREGUE')" class="nav-link"
										id="abaEntregue-tab" data-bs-toggle="tab"
										data-bs-target="#abaEntregue" type="button" role="tab"
										aria-controls="abaEntregue" aria-selected="false">Entregue</button>
								</li>
								<li class="nav-item" role="presentation">
									<button onclick="filterStatus('TROCA')" class="nav-link"
										id="abaDevolucao-tab" data-bs-toggle="tab"
										data-bs-target="#abaDevolucao" type="button" role="tab"
										aria-controls="abaDevolucao" aria-selected="false">Trocas</button>
								</li>
								<li class="nav-item" role="presentation">
									<button onclick="filterStatus('CANCELA')" class="nav-link"
										id="abaDevolucao-tab" data-bs-toggle="tab"
										data-bs-target="#abaDevolucao" type="button" role="tab"
										aria-controls="abaDevolucao" aria-selected="false">Cancelado</button>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="content-tab">
					<div class="tab-content mt-12" id="minhasAbasConteudo">
						<%
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						for (PedidoVenda pedido : listaPedidos) {
							String data = dateFormat.format(pedido.getData());
						%>
						<div class="custom-container data-status=<%=pedido.getStatus()%>">
							<div class="row border-botton">
								<div class="col-4">
									<p><%=data%></p>
								</div>
								<div class="col-5 text-end">
									<h5>
										<a class="visualizarPedido" href="#">Pedido N°: <%=pedido.getId()%></a>
									</h5>
								</div>
								<div class="col-3 text-end">
									<p class="text-success"><%=pedido.getStatus()%></p>
								</div>
							</div>
							<%
							if (pedido.getStatus().equals("ENTREGUE")) {
							%>
							<!-- Inicio dos itens -->
							<%
							}
							if (pedido.getPedidoItens() != null) {
							for (PedidoItens item : pedido.getPedidoItens()) {
							%>
							<div class="row mt-3 " Style="padding-right: 0 !important;">
								<div class="col-md-1">
									<div class="image-container">
										<img
											src="../imagens/produtos/<%=item.getProduto().getCodigo_barra()%>.png"
											class="card-img-top img-fluid rounded-start"
											alt="Imagem do Produto">
									</div>
								</div>
								<div class="col-md-11 row" Style="padding-right: 0 !important;"">
									<div class="col-md-8 col-sm-12">
										<div class="card-body">
											<h6 class="card-title">
												<%=item.getDescricao()%>
											</h6>
											<div class="card-options">
												<div class="card-text">
													<p>
														<%=item.getQuantidade()%>
														x R$<%=item.getPreco()%>
													</p>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-4 col-sm-12 text-end" Style="padding-right: 0 !important;">
										<div
											class="d-flex align-items-center justify-content-end bd-highlight">
											<div class="p-2 bd-highlight ">
												<p class="card-text text-end fs-5">
													R$
													<%=item.getTotalProduto()%>
												</p>
											</div>
										</div>
									</div>
									<div class="col-md-12 col-sm-12 text-end">
										<div>
											<%
											TiposStatusItensPedido status = item.getTipos();
											switch (status) {
											case TROCA_NAO_SOLICITADA:
												break;
											case TROCA_SOLICITADA:
												out.println("Troca solicitada para "+item.getQuantidadeTrocada() + " itens");
												break;
											case TROCA_NAO_ACEITA:
												out.println("Troca não aceita");
												break;
											case ENVIADO_PARA_TROCA:
												out.println("Enviado para troca");
												break;
											case TROCADO:
												out.println("Trocado");
												break;
											}
											%>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12 col-sm-12 text-end">
								<%
								if (status == TiposStatusItensPedido.TROCA_ACEITA) {
								%>
								<button class="btn btn-outline-secondary mt-2"
									onclick="enviarItems(<%=item.getId()%>, <%=pedido.getId()%>, 'EM TROCA', 'ENVIADO_PARA_TROCA')">Enviar
									item para troca</button>
								<%
								}
								%>
							</div>
							<%
							}
							} else {
							out.println("<p>Não há itens</p>");
							}
							%>

							<div class="row mt-2 border-top">
								<div class="col-12 text-end">
									<p class="align-item">
										Total Pedido: <span class="valor-pedido">R$ <%=pedido.getTotalPedido()%></span>
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col-12 text-end">
									<%
									if (pedido.getStatus().equals("ENTREGUE") || pedido.getStatus().contains("TROCA")) {
									%>
									<button class="btn btn-dark mt-2" onclick="abrirModalTroca(<%=pedido.getId()%>)">Solicitar Troca</button>

									<button class="btn btn-dark mt-2"
										onclick="cancelarPedido(<%=pedido.getId()%>)">Cancelar</button>
									<%
									}
									%>
								</div>
							</div>
						</div>
						<%
						}
						%>
					</div>
				</div>

			</div>
		</div>
	</main>
	
	<!-- Modal -->
<div class="modal fade" id="trocaModal" tabindex="-1" aria-labelledby="trocaModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="trocaModalLabel">Solicitar Troca</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="trocaForm">
          <!-- Itens do pedido para troca serão inseridos aqui dinamicamente -->
        </form>
      </div>
      <div class="modal-footer">
        <button id="enviarTrocaBtn" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" onclick="enviarTroca()">Enviar Troca</button>
      </div>
    </div>
  </div>
</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>