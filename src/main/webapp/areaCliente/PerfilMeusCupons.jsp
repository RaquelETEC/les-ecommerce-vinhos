<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.entity.Cupons"%>
<%@ page import="model.entity.Cliente"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<Cupons> lista = (ArrayList<Cupons>) request.getAttribute("listaCupons");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>tela 1</title>
<link rel="stylesheet" href="../Styles/StyleAreaCliente.css">
<script src="../scripts/exibirMenus.js" defer></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">

</head>

<body>

	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<a href="/les-ecommerce-vinhos/paginaInical.html"><img src="../imagens/logo-vinho.PNG"
				alt="Logo Vinho" style="width: 200px;" class="mx-3"></a>
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
					<div class="box-exibir-opcoes">
						<button
							onclick="window.location.href='Perfil.html?id=<%=request.getAttribute("id")%>';">
							Perfil</button>
						<button 
							onclick="window.location.href='MeusCartoes.html?id=<%=request.getAttribute("id")%>';">Cartões</button>
						<button
							onclick="window.location.href='MeusEnderecos.html?id=<%=request.getAttribute("id")%>';">Endereços</button>
						<button
							onclick="window.location.href='/les-ecommerce-vinhos/areaCliente/TrocarSenha.html?id=<%=request.getAttribute("id")%>';">Trocar
							Senha</button>
					</div>

					<button class="button-dados-perfil">
						<img src="../imagens/assets/icons-left-perfil-2.png" alt=""
							class="img-icons-perfil"> <span
							onclick="window.location.href='/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=<%=request.getAttribute("id")%>';">Minhas
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
							onclick="window.location.href='MeusCupons.html?id=<%=request.getAttribute("id")%>';">Meus
							Cupons</span>
					</button>

				</div>

			</div>
			<!-- acaba aqui  -->

			<div class="container-register">

				<div class="box-top-register">
					<!-- titulo do cabecalho -->
					<h1 class="text-perfil">Meus Cupons</h1>

					<div class="box-input-pesquisa-e-carrinho">

						<div class="box-input-top-pesquisar">
							<input type="text" class="input-top-pesquisar"
								placeholder="Busca">
							<button class="button-top-register">
								<img src="../imagens/assets/icons-lupa-input.png" alt=""
									class="img-top-register">
							</button>
						</div>

						<button class="button-top-register">
							<img src="../imagens/assets/icons-carrinho.png" alt=""
								class="img-top-register">
						</button>

					</div>

				</div>

				<div class="content-Cupons">
					<!-- conteudo que voce pode trocar, no caso eh o conteudo da parte branca, lado direito maior -->
					<%
					for (int i = 0; i < lista.size(); i++) {
						Date validade = lista.get(i).getValidade();
					    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					    String validadeFormatada = sdf.format(validade);
					%>
					<div class="card mt-3" style="background-color: #F0F0F0; ">
						<div class="card-body">
							<div class="row">
								<!-- Parte esquerda com a imagem do cupom -->
								<div class="col-md-2">
									<img src="../imagens/assets/<%=lista.get(i).getImg()%>"
										alt="Imagem do Cupom" class="img-fluid"  style="width: 50%; display: block;">
								</div>
								<!-- Parte direita com o nome do cupom e a data de vencimento -->
								<div class="col-md-10">
									<h6 class="card-title"><%=lista.get(i).getDesc()%></h6>
									<p class="card-text">
										Vencimento: <%= validadeFormatada %>
									</p>
								</div>
							</div>
						</div>
					</div>
						
						<%
						}
						%>


					<!-- Adicione mais cards conforme necessário -->


					<!-- acaba aqui -->

				</div>

			</div>


		</div>
	</main>
</body>

</html>