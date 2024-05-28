<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Cliente"%>
<%@ page import="model.entity.PedidoVenda"%>
<%@ page import="model.entity.PedidoItens"%>
<%@ page import="model.entity.TiposStatusItensPedido "%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<PedidoVenda> listaPedidos = (ArrayList<PedidoVenda>) request.getAttribute("listaPedidos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Trocas</title>
    <link rel="icon" href="imagens/favicon.png">
    <link rel="stylesheet" href="../Styles/styleAdm.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
	<script src="../scripts/confirmador.js"></script>

</head>
<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
		<div class="container-fluid">
		<a href='http://localhost:8080/les-ecommerce-vinhos/paginaInical.html'>	<img src="../imagens/logo-vinho.PNG" alt="Logo Vinho"
				style="width: 200px;" class="mx-3"></a>				
		</div>
	</nav>
    
    <div class="container-fluid">
        <div class="row">
            <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-dark sidebar">
                <div class="position-sticky">
                    <ul class="nav flex-column">
                     <li class="nav-Title">
                            Menu
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" onclick="window.location.href='PagInicial.html';">
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/les-ecommerce-vinhos/areaAdministrador/Clientes.html">
                                Clientes
                            </a>
                        </li>
                          <li class="nav-item">
                            <a class="nav-link" href="/les-ecommerce-vinhos/areaAdministrador/PedidoVenda.html">
                                Alterar Status de Pedido
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link bg-white text-dark" onclick="window.location.href='TrocaPedidos.html';">
                                Pedidos Aguardando Troca
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="window.location.href='Produtos.html';">
                                Produtos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="window.location.href='Estoque.html';">
                                Estoque
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <!-- Conteudo principal aqui -->
				    <div class="container mt-4">
				        <h2 class="text-center mb-4">Itens solicitando Troca</h2>
				        
				          <!-- FormulÃ¡rio de filtro -->
      						<form class="mb-4">
						<div class="row">
							<div class="col-md-2">
								<label for="cliente" class="form-label">Cliente</label> <input
									type="text" class="form-control" id="cliente" name="cliente">
							</div>
							<div class="col-md-3">
								<label for="ID" class="form-label">Pedido</label> <input
									type="text" class="form-control" id="ID" name="ID">
							</div>
							<div class="col-md-3">
								<label for="dataPedido" class="form-label">Data</label> <input
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
								<th>Pedido</th>
								<th>Cliente</th>
								<th>Data</th>
								<th></th>
								<th>Produto</th>
								<th>Solicitada</th>
								<th>Total</th>
								<th>Status</th>
								<th>Opções</th>
							</tr>
						</thead>
						<tbody>
					        <% 
					        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					        for (PedidoVenda pedido : listaPedidos) {
					            String data = dateFormat.format(pedido.getData());
					            for (PedidoItens item : pedido.getPedidoItens()) {
					            	
					        %>
					        <tr>
					            <td><%= pedido.getId() %></td>
					            <td><%= pedido.getCliente().getNome() %></td>
					            <td><%= data %></td>
					            <td>
					            	<img src="../imagens/produtos/<%=item.getProduto().getCodigo_barra() %>.png" 
						            class="card-img-top img-fluid-trocas rounded-start" 
						            alt="Imagem do Produto">
					            </td>
					            <td><%= item.getDescricao() %></td>
					            <td><%= item.getTroca().getQuantidadeSolicitada() %></td>
					            <td>R$<%= item.getTotalProduto() %></td>		
					            <td>
						            <% TiposStatusItensPedido status = item.getTroca().getStatus();
										switch (status) {
							            case TROCA_NAO_SOLICITADA:
							                break;
							            case TROCA_SOLICITADA:
							                out.println("Troca solicitada");
							                break;
							            case TROCA_ACEITA:
							                out.println("Troca aceita");
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
								</td>					            
					            			            
					            <td>
						    		 <div class="option-button">
								    <% if (status == TiposStatusItensPedido.TROCA_SOLICITADA) { %>
								        <button type="button" class="btn btn-secondary" onClick="confirmarTroca(<%= item.getId() %>, <%= item.getTroca().getId() %> , <%= item.getTroca().getQuantidadeSolicitada() %>,<%= item.getQuantidadeTrocada() %>, <%= pedido.getId() %>, 'EM TROCA', 'TROCA_ACEITA')">Aceitar</button>
								        <button type="button" class="btn btn-secondary" onClick="confirmarTroca(<%= item.getId() %>, <%= item.getTroca().getId() %> , <%= 0 %>,<%= item.getQuantidadeTrocada() %>, <%= pedido.getId() %>, 'EM TROCA', 'TROCA_NAO_ACEITA')">Recusar</button>
								    <% } else if (status == TiposStatusItensPedido.ENVIADO_PARA_TROCA) { %>
										<button type="button" class="btn btn-secondary" id="confirmarRecebimentoBtn" 
										    data-item-id="<%= item.getId() %>" 
										    data-item-troca-id="<%= item.getTroca().getId() %>" 
										    data-prod-id="<%= item.getProduto().getId() %>"
										    data-prod-valor="<%= item.getTotalProduto() %>"
										    data-pedido-id="<%= pedido.getId() %>"
										    data-cliente-id="<%= pedido.getCliente().getId() %>"
										    data-quant-item="<%= item.getTroca().getQuantidadeSolicitada() %>"
										    data-novo-status="TROCADO"
										    data-novo-status-item="TROCADO"
										    data-bs-toggle="modal" 
										    data-bs-target="#confirmarRecebimentoModal"
										    onclick="capturarDadosConfirmarRecebimento(this)">Confirmar Recebimento</button>
								    <% } %>
								</div>
						        </td>					           
					        </tr>
					        <% } // Fim do loop dos itens do pedido
					        } // Fim do loop dos pedidos
					        %>
					    </tbody>
					</table>

				      
				    </div>
				</main>
         
        </div>
    </div>
    <footer class="p-4 text-light text-center" style="background: black;">
        Desenvolvido por Caynan e Raquel
    </footer>
	  <!-- Modal -->
	<div class="modal fade" id="confirmarRecebimentoModal" tabindex="-1" aria-labelledby="confirmarRecebimentoModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmarRecebimentoModalLabel">Confirmar Recebimento</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body text-center">
	                Inserir item no estoque?
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" onclick="processarConfirmacao(true)" data-bs-dismiss="modal">Sim</button>
	                <button type="button" class="btn btn-secondary" onclick="processarConfirmacao(false)" data-bs-dismiss="modal">Não</button>
	            </div>
	        </div>
	    </div>
	</div>
	    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>