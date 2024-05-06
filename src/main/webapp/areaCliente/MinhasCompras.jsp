<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.entity.Cliente"%>
	
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Minhas Compras</title>
    <link rel="stylesheet" href="../Styles/StyleMinhasCompras.css">
    <script src="../scripts/exibirMenus.js" defer></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
	  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
 
	<nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
		<div class="container-fluid">
            <a href="/les-ecommerce-vinhos/index.html"><img src="../imagens/logo-vinho.PNG" alt="Logo Vinho" style="width: 200px;" class="mx-3"></a>
            
			<div class="navbar-nav ms-auto">
			</div>
		</div>
	</nav>
    <main>
        <div class="container-main">

            <!-- conteudo do perfil da direita -->
            <div class="container-perfil">
                <div class="box-name-perfil">
                    <img src="../imagens/assets/icon-box-image-name.png" alt="" class="img-name-perfil">
                    <div class="box-name-e-nivel">
                        <p class="text-name">Usuario</p>
                        <p class="text-nivel">NIVEL: 1</p>
                    </div>
                </div>

                <div class="box-dados-perfil">
                    <button class="button-dados-perfil" >
                        <img src="../imagens/assets/icons-left-perfil-1.png" alt="" class="img-icons-perfil">
                        <span>Minha Conta</span>
                    </button>
                    <div class="box-exibir-opcoes">
                        <button>Perfil</button>
                   		<button class = "cartoes" onclick="window.location.href='MeusCartoes.html?id=<%=cliente.getId()%>';">Cartões</button>
                        <button class = "enderecos" onclick="window.location.href='MeusEnderecos.html?id=<%=cliente.getId()%>';">Endereços</button>
                        <button onclick="window.location.href='/les-ecommerce-vinhos/areaCliente/TrocarSenha.html?id=<%=cliente.getId()%>';">Trocar Senha</button>
                    </div>

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-2.png" alt="" class="img-icons-perfil">
                        <span class="ativo" onclick="window.location.href='/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=<%=cliente.getId()%>';">Minhas Compras</span>
                    </button>
                   

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-3.png" alt="" class="img-icons-perfil">
                       <span onclick="window.location.href='Notificacoes.jsp';">Notificacoes</span>
                    </button>
                    

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-4.png" alt="" class="img-icons-perfil">
                       <span onclick="window.location.href='MeusCupons.html?id=<%=cliente.getId()%>';">Meus Cupons</span>
                    </button>
                    
                </div>

            </div>
            <!-- acaba aqui  -->

            <div class="col-md-9">
                <div class="box-top-register">
                    <!-- titulo do cabecalho -->
                    <h1 class="text-perfil">Minhas compras</h1>
                </div>
                <div class="custom-container">
				    <div class="row tabs-content mt-2">
					 	<div class="col-md-12">
					 	  <ul class="nav nav-pills nav-justified" id="minhasAbas" role="tablist">
			                 <li class="nav-item" role="presentation">
			                     <button class="nav-link active" id="abaTudo-tab" data-bs-toggle="tab" data-bs-target="#abaTudo" type="button" role="tab" aria-controls="abaTudo" aria-selected="true">Tudo</button>
			                 </li>
			                 <li class="nav-item" role="presentation">
			                     <button class="nav-link" id="abaTransito-tab" data-bs-toggle="tab" data-bs-target="#abaTransito" type="button" role="tab" aria-controls="abaTransito" aria-selected="false">Em Trânsito</button>
			                 </li>
			                 <li class="nav-item" role="presentation">
			                     <button class="nav-link" id="abaEntregue-tab" data-bs-toggle="tab" data-bs-target="#abaEntregue" type="button" role="tab" aria-controls="abaEntregue" aria-selected="false">Entregue</button>
			                 </li>
			                 <li class="nav-item" role="presentation">
			                     <button class="nav-link" id="abaDevolucao-tab" data-bs-toggle="tab" data-bs-target="#abaDevolucao" type="button" role="tab" aria-controls="abaDevolucao" aria-selected="false">Devolução</button>
			                 </li>
			             </ul>
					 	</div>
					 </div>
				 </div>
                <div class="content">
					                <!-- Conteúdo das Abas -->
                <div class="tab-content mt-4" id="minhasAbasConteudo">
                    <div class="tab-pane fade show active" id="abaTudo" role="tabpanel" aria-labelledby="abaTudo-tab">
                        <!-- Conteúdo da aba "Tudo" -->
                        Aqui vai o conteúdo da aba "Tudo".
                    </div>
                    <div class="tab-pane fade" id="abaTransito" role="tabpanel" aria-labelledby="abaTransito-tab">
                        <!-- Conteúdo da aba "Em Trânsito" -->
                        Aqui vai o conteúdo da aba "Em Trânsito".
                    </div>
                    <div class="tab-pane fade" id="abaEntregue" role="tabpanel" aria-labelledby="abaEntregue-tab">
                        <!-- Conteúdo da aba "Entregue" -->
                        Aqui vai o conteúdo da aba "Entregue".
                    </div>
                    <div class="tab-pane fade" id="abaDevolucao" role="tabpanel" aria-labelledby="abaDevolucao-tab">
                        <!-- Conteúdo da aba "Devolução" -->
                        Aqui vai o conteúdo da aba "Devolução".
                    </div>
                </div>
 					<!--acaba aqui o content-->
                </div>
              
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>