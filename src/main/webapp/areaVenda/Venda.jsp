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
ArrayList<Cupons> listaCuponsDeTroca= (ArrayList<Cupons>) request.getAttribute("listaCuponsPromocional");

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
								<p id="Nome"class="card-title "><strong></strong></p>
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
									<button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCupomP">Selecionar Cupom</button>
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
							<p id="Nome<%=endereco.getId()%>" class="card-title "><strong><%=endereco.getNome()%> </strong></p>
						    <p class="card-text">
							    <span id="Logradouro<%=endereco.getId()%>"><%= endereco.getLogradouro() %></span>,
							    <span id="Numero<%=endereco.getId()%>"><%= endereco.getNumero() %></span>,
							    <span id="Bairro<%=endereco.getId()%>"><%= endereco.getBairro() %></span>,
							    <span id="Cidade<%=endereco.getId()%>"><%= endereco.getCidade() %></span>,
							    <span id="Estado<%=endereco.getId()%>"><%= endereco.getEstado() %></span> - 
							    <span id="Cep<%=endereco.getId()%>"><%= endereco.getCep() %></span>,
							    <span id="Pais<%=endereco.getId()%>"><%= endereco.getPais() %></span>
							</p>
			            </div>
			        </div>
	               </div>
	           </div>
			   <% } %>
			    <hr>
			    <p>Não encontrou o endereço desejado?</p>
			    <button type="button" class="btn btn-primary" data-dismiss="modal" data-toggle="modal" data-target="#modalCadastroEndereco">Adicionar endereço</button>
			
			</div>
            <div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="salvarEndereco()">Salvar</button>
            </div>
        </div>
    </div>
</div>
<!-- cadastrar endereco -->

<div class="modal fade" id="modalCadastroEndereco" tabindex="-1" aria-labelledby="modalCadastroEndereco" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalSelecaoEnderecoLabel">Seleção de Endereço</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
		<div class="modal-body" style="max-height: 70vh; overflow-y: auto;">
		<!-- Seu formulário de cadastro aqui -->
	           <form name="frmcliente" >
	              <fieldset>                                               
	              <input type="text" name="id" id="id" style="display: none"/>
	              <div class="row">
	               <div class="col-md-12 mb-4">
	                      <label class="form-label" for="nome">Nome*</label>
	                      <input type="text" name="nome" id="nome" class="form-control form-control-lg"  required/>
	                         
	                  </div>
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="tipoResidencia">Tipo de Residência *</label>
	                      <select class="form-select form-select-lg" name="typeTipoResidencia" id="typeTipoResidencia" required>
	                          <option value="" disabled selected></option>
	                          <option value="casa">Casa</option>
	                          <option value="apartamento">Apartamento</option>
	                          <option value="condominio">Condomínio</option>
	                      </select>
	                  </div>
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="tipoLogradouro">Tipo Logradouro*</label>
	                      <select class="form-select form-select-lg" name="typeTipoLogradouro" id="typeTipoLogradouro" required>
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
	                      <label class="form-label" for="logradouro">Logradouro *</label>
	                      <input type="text" name="typeLogradouro" id="typeLogradouro" class="form-control form-control-lg" required/>
	                  </div>
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="numero">N° *</label>
	                      <input type="text" name="typeNumero" id="typeNumero" class="form-control form-control-lg" required/>
	                  </div>
	              </div>
	              <div class="row">
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="typeBairro">Bairro*</label>
	                      <input type="text" name="typeBairro" id="typeBairro" class="form-control form-control-lg"required/>
	                  </div>
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="typeCidade">Cidade*</label>
	                      <input type="text" name="typeCidade" id="typeCidade" class="form-control form-control-lg" required />
	                  </div>
	              </div>
	              <div class="row">
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="typeEstado">Estado*</label>
	                      <input type="text" name="typeEstado" id="typeEstado" class="form-control form-control-lg" required/>
	                  </div>
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="typeCep">CEP*</label>
	                      <input type="text" name="typeCep" id="typeCep" class="form-control form-control-lg" required/>
	                  </div>
	              </div>
	              <div class="row">
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="typePais">País*</label>
	                      <input type="text" name="typePais" id="typePais" class="form-control form-control-lg" required />
	                  </div>
	                  <div class="col-md-6 mb-4">
	                      <label class="form-label" for="observacoes">Observações</label>
	                      <input type="text" name="observacoes" id="observacoes" class="form-control form-control-lg"  />
	                  </div>
	              </div>    
	              <label class="form-label" for="cadastrarEndNopPerfil">Incluir endereço ao perfil?</label>
	                      <select class="form-select form-select-lg" name="cadastrarEndNopPerfil" id="cadastrarEndNopPerfil" required>
	                          <option value="Sim" >Sim</option>
	                          <option value="Não">Não</option>
	                      </select>
	              
	              <input type="hidden" name="tipoEndereco" value="ENTREGA">
	              
	              <br>
	              </fieldset>
	             </form>
			</div>
            <div class="modal-footer">
              <button class="btn btn-primary btn-lg btn-block" id="BotaoCadastrar" type="button" onClick="AdicionarNovoEndereco(<%=request.getParameter("idCliente")%>)">Cadastrar</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal Cupom -->
<div class="modal fade" id="modalCupomP" tabindex="-1" aria-labelledby="modalCupomP" aria-hidden="true">
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
			for (int i = 0; i < listaCuponsPromocionais.size(); i++) {
				Cupons cupom = listaCuponsPromocionais.get(i); // Crie um novo objeto Endereco
			%>
			<div class="card mt-3">
				<div class="card-body">
					<div class="row">
						<div class="col-md-2 d-flex align-items-center justify-content-center"> 
				   			<img src= <%=cupom.getImg()%>alt="Imagem do Cupom" class="img-fluid">
				         </div>
					 	<div class="col-md-10">
							<p id="NomeCupom" class="card-title "><strong><%=cupom.getDesc()%> </strong></p>
						    <p class="card-text">
							    <span id="ValorCupom"><%= cupom.getValor() %></span>,
							    
							</p>
			            </div>
			        </div>
	               </div>
	           </div>
			   <% } %>
			    <hr>
			    <p>Não encontrou o endereço desejado?</p>
			    <button type="button" class="btn btn-primary" data-dismiss="modal" data-toggle="modal" data-target="#modalCadastroEndereco">Adicionar endereço</button>
			
			</div>
            <div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="salvarEndereco()">Salvar</button>
            </div>
        </div>
    </div>
</div>
<!-- cadastrar endereco -->

	</main>


	<footer class="p-4 text-light text-center footer">
		Desenvolvido por Caynan e Raquel </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>