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
    <title>tela 1</title>
    <link rel="stylesheet" href="../Styles/StyleAreaCliente.css">

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
                    <button class="button-dados-perfil" >
                        <img src="../imagens/assets/icons-left-perfil-1.png" alt="" class="img-icons-perfil">
                        <span>Minha Conta</span>
                    </button>
                    <div class="box-exibir-opcoes">
                        <button class="ativo">Perfil</button>
                   		<button class = "cartoes" onclick="window.location.href='MeusCartoes.html?id=<%=cliente.getId()%>';">Cartões</button>
                        <button class = "enderecos" onclick="window.location.href='MeusEnderecos.html?id=<%=cliente.getId()%>';">Endereços</button>
                        <button onclick="window.location.href='/les-ecommerce-vinhos/areaCliente/TrocarSenha.html?id=<%=cliente.getId()%>';">Trocar Senha</button>
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
                    <h1 class="text-perfil">Editar Perfil</h1>


                </div>

                <div class="content">
                    <!-- conteudo que voce pode trocar, no caso eh o conteudo da parte branca, lado direito maior -->
                    <form  class="form-register" action="/les-ecommerce-vinhos/updateCliente" onsubmit="return validarAtualizar()">
						
						<div class="box-input-form" style="display: none;">
                            <div class="box-label" >
                                <label for="typeId">Id</label>
                            </div>
                             <input type="text" name="typeId" id="typeId" class="input-form input-width-1" 
                             value="<%= cliente.getId() %>">
                            
                        </div>
                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="typeNome">Nome</label>
                            </div>
                             <input type="text" name="typeNome" id="typeNome" class="input-form input-width-1" 
                             value="<%=cliente.getNome() %>">
                            
                        </div>
                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="typeEmail">E-Mail</label>
                            </div>
                            <input type="email" name="typeEmail" id="typeEmail" class="input-form input-width-1 " 
                            value="<%= cliente.getEmail() %>">
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="typeCPF">CPF</label>
                            </div>
                            <input type="number" name="typeCPF" id="typeCPF" class="input-form input-width-2 " 
                             value="<%= cliente.getCpf() %>">
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="typeNascimento">Data de nascimento</label>
                            </div>
                            <input type="date" name="typeNascimento" id="typeNascimento" class="input-form input-width-3 " 
                             value="<%= cliente.getDataNasc() %>">
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="typeNumeroTelefone">Telefone</label>
                            </div>
                            <input type="tel" name="typeNumeroTelefone" id="typeNumeroTelefone" class="input-form input-width-2 " 
                            value="<%= cliente.getTelefone() %>">
                             
                             <div class="box-label-tipo">
                                <label for="tipoTelefone">Tipo Telefone</label>
                            </div>
                          <select id="tipoTelefone" name="tipoTelefone" class="input-form input-width-2">
						    <option value="Fixo" <%= "Fixo".equals(cliente.getTipoTelefone()) ? "selected" : "" %>>Fixo</option>
						    <option value="Celular" <%= "Celular".equals(cliente.getTipoTelefone()) ? "selected" : "" %>>Celular</option>
						</select>
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="genero">Gênero</label>
                            </div>
                             <input type="radio" name="genero" id="Feminino" class="input-radio" 
                             value="Feminino" <%= "Feminino".equals(cliente.getGenero()) ? "checked" : "" %>>
						     <label for="Feminino" style="font-weight: 100"  >Feminino</label>
						
						     <input type="radio" name="genero" id="Masculino" class="input-radio" 
						     value="Masculino" <%= "Masculino".equals(cliente.getGenero()) ? "checked" : "" %>>
						     <label for="Masculino" style="font-weight: 100;">Masculino</label>
						     
						 	 <input type="radio" name="genero" id="nao_binario" class="input-radio" 
						 	 value="Não Binário" <%= "Não Binário".equals(cliente.getGenero()) ? "checked" : "" %>>
						     <label for="nao_binario" style="font-weight: 100;">Não Binario</label>
						     
						     <input type="radio" name="genero" id="Outros" class="input-radio" 
						     value="Outros" <%= "Outros".equals(cliente.getGenero()) ? "checked" : "" %>>
						     <label for="Outros" style="font-weight: 100;">Outros</label>
                        </div>
						<div class="box-input-form">
						    <div class="box-label">
						        <label for="statusCliente">Status</label>
						    </div>
						     <select id="statusCliente" name="statusCliente" class="input-form input-width-1">
							    <option value="Ativo" <%= "Ativo".equals(cliente.getStatus()) ? "selected" : "" %>>Ativo</option>
							    <option value="Inativo" <%= "Inativo".equals(cliente.getStatus()) ? "selected" : "" %>>Inativo</option>
							</select>
						</div>
                         <input class="button-salvar" type="submit" value="Atualizar"> 
                    </form>
                    <!-- acaba aqui -->

                </div>

            </div>


        </div>
    </main>
</body>

</html>