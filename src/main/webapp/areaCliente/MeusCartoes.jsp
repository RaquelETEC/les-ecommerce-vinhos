<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.CartaoDeCredito"%>
<%@ page import="model.entity.Cliente"%>

<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<CartaoDeCredito> lista = (ArrayList<CartaoDeCredito>) request.getAttribute("listaCartoes");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cartoes</title>
<link rel="stylesheet" href="../Styles/StyleAreaCliente.css">
<link rel="stylesheet" href="../Styles/StyleMeusCartoesV4.css">

<script src="../scripts/exibirMenus.js" defer></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">

</head>

<body>

	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<a href="/les-ecommerce-vinhos/index.html"><img
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
						<p class="text-name">Usuario</p>
						<p class="text-nivel">NIVEL: 1</p>
					</div>
				</div>

				<div class="box-dados-perfil">
					<button class="button-dados-perfil" onclick="exibirMinhaConta()">
						<img src="../imagens/assets/icons-left-perfil-1.png" alt=""
							class="img-icons-perfil"> <span>Minha Conta</span>
					</button>
					<div class="box-exibir-opcoes MinhaConta">
						<button
							onclick="window.location.href=>'Perfil.html?id=<%=request.getAttribute("id")%>';">Meu
							Perfil</button>
						<button class="ativo"
							onclick="window.location.href='MeusCartoes.html?id=<%=request.getAttribute("id")%>';">Cartoes</button>
						<button
							onclick="window.location.href='MeusEnderecos.html?id=<%=request.getAttribute("id")%>';">Enderecos</button>
						<button onclick="window.location.href='PerfilTrocarSenha.jsp';">Trocar
							Senha</button>
					</div>

					<button class="button-dados-perfil">
						<img src="../imagens/assets/icons-left-perfil-2.png" alt=""
							class="img-icons-perfil"> <span
							onclick="window.location.href='MinhasCompras.html';">Minhas
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
							onclick="window.location.href='PerfilMeusCupons.jsp';">Meus
							Cupons</span>
					</button>

				</div>

			</div>
			<!-- acaba aqui  -->

			<div class="container-register">

				<div class="box-top-register">
					<!-- titulo do cabecalho -->
					<h1 class="text-perfil">Cartões de Crédito</h1>
				</div>
				<div class="content-Cupons">
					<div class="buttons-content">
						<button
							onclick="window.location.href='LoginCartao.html?id=<%=request.getAttribute("id")%>';"
							class="botaoADDNovoCartao">+ Adicionar
							Novo Cartão</button>
					</div>
					<%
					for (int i = 0; i < lista.size(); i++) {
						CartaoDeCredito cartao = lista.get(i);
					%>
					<!-- Conteúdo do primeiro card -->
					<div class="card mt-3"
						style="background-color: #F0F0F0; width: 60rem; height: 8rem">
						<div class="card-body">
							<div class="row">
								<div class="col-md-2">
									<img src="<%=lista.get(i).getBandeira().getImg()%>"
										alt="Imagem da Bandeira" class="img-fluid-cartao">
								</div>
								<div class="col-md-7">
									<h5 class="card-title"><%=lista.get(i).getNome()%></h5>
									<p class="card-text">
										Número:
										<%=lista.get(i).getNumero()%></p>
									<p class="card-text">
										Padrão: <span style="color: green;"><%=lista.get(i).getPadrao()%></span>
									</p>
								</div>
								<div class="col-md-3 buttons-options">
									<a type="button" id="BotaoEditar" class="btn btn-primary"
										onclick="window.location.href='EditarCartao.html?id=<%=request.getAttribute("id")%>&idCartao=<%=cartao.getId()%>&idBandeira=<%=cartao.getBandeira().getId()%>';">Editar</a>
									<a	
										href="javascript: confirmarCartao(<%=lista.get(i).getId()%>, id=<%=request.getAttribute("id")%>)"
										id="btnExcluir" class="btn btn-danger">Excluir</a>
	
								</div>
							</div>
						</div>
					</div>
					<%
					}
					%>


					<!-- acaba aqui -->

				</div>


			</div>


		</div>
		<script src="../scripts/confirmadorCartao.js"></script>
	</main>
</body>

</html>