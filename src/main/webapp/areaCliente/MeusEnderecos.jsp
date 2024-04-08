<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.entity.Endereco"%>
<%@ page import="java.util.ArrayList"%>
<%
	@ SuppressWarnings ("unchecked")
	ArrayList<Endereco> listaEntrega = (ArrayList<Endereco>) request.getAttribute("listaEnderecosEntrega");
	ArrayList<Endereco> listaCobranca = (ArrayList<Endereco>) request.getAttribute("listaEnderecosCobranca");
	ArrayList<Endereco> listaResidencial = (ArrayList<Endereco>) request.getAttribute("listaEnderecosResidencial");


%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meus enderecos</title>
    <link rel="stylesheet" href="../Styles/StyleAreaCliente.css">
    <link rel="stylesheet" href="../Styles/StyleMeusCartoesV4.css">

    <script src="../scripts/exibirMenus.js" defer></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
    
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
                    <button class="button-dados-perfil" onclick="exibirMinhaConta()">
                        <img src="../imagens/assets/icons-left-perfil-1.png" alt="" class="img-icons-perfil">
                        <span>Minha Conta</span>
                    </button>
                    <div class="box-exibir-opcoes MinhaConta">
                        <button onclick="window.location.href=>'Perfil.html?id=<%=request.getAttribute("id")%>';">Meu Perfil</button>
                   		<button onclick="window.location.href='MeusCartoes.html?id=<%=request.getAttribute("id")%>';">Cartoes</button>
                        <button class="ativo" onclick="window.location.href='MeusEnderecos.html?id=<%=request.getAttribute("id")%>';">Enderecos</button>
                        <button onclick="window.location.href='PerfilTrocarSenha.jsp'?id=<%=request.getAttribute("id")%>;">Trocar Senha</button>
                    </div>

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-2.png" alt="" class="img-icons-perfil">
                        <span onclick="window.location.href='MinhasCompra.html';">Minhas Compras</span>
                    </button>
                   

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-3.png" alt="" class="img-icons-perfil">
                       <span onclick="window.location.href='Notificaï¿½ï¿½es.jsp';">Notificacoes</span>
                    </button>
                    

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-4.png" alt="" class="img-icons-perfil">
                       <span onclick="window.location.href='PerfilMeusCupons.jsp';">Meus Cupons</span>
                    </button>
                    
                </div>

            </div>
            <!-- acaba aqui  -->

            <div class="container-register">

                <div class="box-top-register">
                    <!-- titulo do cabecalho -->
                    <h1 class="text-perfil">Endereços</h1>
                </div>
   				<div class="content-Cupons">
   					<div class="buttons-content">
   				        <button 
   				        class="botaoADDNovoCartao" 
   				        onclick="window.location.href='MeusCartoesADDNovo.html?id=<%=request.getAttribute("id")%>';">
   				        + Adicionar Novo Endereço
   				       </button>
   					</div>
   					<p class="text-name">Endereços de Entrega</p>
   				 	<!--   Conteúdo do primeiro card -->
   					
	             	<%
							for (int i = 0; i < listaEntrega.size(); i++) {
								Endereco endereco = listaEntrega.get(i); // Crie um novo objeto Endereco

					%>
					<div class="card mt-3" style="background-color: #F0F0F0; width: 100%;height: 6rem">
					    <div class="card-body">
					        <div class="row">
					            <div class="col-md-10">
					                <h5 class="card-title "><%=endereco.getNome()%>
					                 <% if ("S".equals(endereco.getPadrao())) { %>
									        <span  style="background-color: #56b07a; color: white;" class="badge rounded-pill">Padrão</span>
									    <% } %>
					                </h5>
									 <p class="card-text">
										  <%=endereco.getTipoLogradouro()%> <%=endereco.getLogradouro()%>, <%=endereco.getNumero()%>,
										  <%=endereco.getBairro()%>, <%=endereco.getCidade()%>, <%=endereco.getEstado()%> - <%=endereco.getCep()%>, <%=endereco.getPais()%>
									 </p>					                
					            </div>
					            <div class="col-md buttons-options">
					                <button type="button" class="btn btn-primary" onClick="window.location.href='MeusEnderecosEditar.html?id=<%=request.getAttribute("id")%>&idEnd=<%=endereco.getId()%>';">Editar</button>
					                <button type="button" class="btn btn-danger">Excluir</button>
					           </div>
					        </div>
					    </div>
					</div>
						
					<%
						}
					%>
				    
                    <!-- acaba aqui -->
                    <hr>
                    <p class="text-name">Endereços de Cobrança</p>
   				 	<!--   Conteúdo do primeiro card -->
   					
	             	<%
							for (int i = 0; i < listaCobranca.size(); i++) {
								Endereco endereco = listaCobranca.get(i); // Crie um novo objeto Endereco

					%>
					<div class="card mt-3" style="background-color: #F0F0F0; width: 100%;height: 6rem">
					    <div class="card-body">
					        <div class="row">
					            <div class="col-md-10">
									<h5 class="card-title"><%=endereco.getNome()%>
					                 <% if ("S".equals(endereco.getPadrao())) { %>
									        <span  style="background-color: #56b07a; color: white;" class="badge rounded-pill">Padrão</span>
									    <% } %>
					                </h5>									 <p class="card-text">
										  <%=endereco.getTipoLogradouro()%> <%=endereco.getLogradouro()%>, <%=endereco.getNumero()%>,
										  <%=endereco.getBairro()%>, <%=endereco.getCidade()%>, <%=endereco.getEstado()%> - <%=endereco.getCep()%>, <%=endereco.getPais()%>
									 </p>					                
					            </div>
					            <div class="col-md buttons-options">
					                <button type="button" class="btn btn-primary" onClick="window.location.href='MeusEnderecosEditar.html?id=<%=request.getAttribute("id")%>&idEnd=<%=endereco.getId()%>';">Editar</button>
					                <button type="button" class="btn btn-danger">Excluir</button>
					           </div>
					        </div>
					    </div>
					</div>
						
					<%
						}
					%>
				    
                    <!-- acaba aqui -->
  					<hr>
  					<p class="text-name">Endereço Residêncial</p>
   				 	<!--   Conteúdo do primeiro card -->
   					
	             	<%
							for (int i = 0; i < listaResidencial.size(); i++) {
								Endereco endereco = listaResidencial.get(i); // Crie um novo objeto Endereco

					%>
					<div class="card mt-3" style="background-color: #F0F0F0; width: 100%;height: 6rem">
					    <div class="card-body">
					        <div class="row">
					            <div class="col-md-10">
								<h5 class="card-title"><%=endereco.getNome()%>
					                 <% if ("S".equals(endereco.getPadrao())) { %>
									        <span  style="background-color: #56b07a; color: white;" class="badge rounded-pill">Padrão</span>
									    <% } %>
					                </h5>									 <p class="card-text">
										  <%=endereco.getTipoLogradouro()%> <%=endereco.getLogradouro()%>, <%=endereco.getNumero()%>,
										  <%=endereco.getBairro()%>, <%=endereco.getCidade()%>, <%=endereco.getEstado()%> - <%=endereco.getCep()%>, <%=endereco.getPais()%>
									 </p>					                
					            </div>
					            <div class="col-md buttons-options">
					                <button type="button" class="btn btn-primary" onClick="window.location.href='MeusEnderecosEditar.html?id=<%=request.getAttribute("id")%>&idEnd=<%=endereco.getId()%>';">Editar</button>
					                <button type="button" class="btn btn-danger">Excluir</button>
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
        <br><br>
        
    </main>
</body>

</html>