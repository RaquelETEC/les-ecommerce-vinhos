<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.entity.Cliente"%>
<%@ page import="java.util.ArrayList"%>
<%
	@ SuppressWarnings ("unchecked")
	ArrayList<Cliente> lista = (ArrayList<Cliente>) request.getAttribute("Requeclientes");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Area da administração</title>
	<link rel="stylesheet" href="../Styles/styleAdm.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
		<div class="container-fluid">
            <a><img src="../imagens/logo-vinho.PNG" alt="Logo Vinho" style="width: 200px;" class="mx-3"></a>
			<div class="navbar-nav ms-auto">
			</div>
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
                            <a class="nav-link" href="/les-ecommerce-vinhos/areaAdministrador/PagInicial.html">
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link bg-white text-dark" href="/les-ecommerce-vinhos/areaAdministrador/Clientes.html">
                                Clientes
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/les-ecommerce-vinhos/areaAdministrador/AprovacaoPedidos.html">
                                Alterar Status de Pedido
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="window.location.href='TrocaPedidos.html';">
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
				        <h2 class="text-center mb-4">Clientes</h2>
				        <!-- FormulÃ¡rio de filtro -->
        <form class="mb-4">
            <div class="row">
                <div class="col-md-2">
                    <label for="genero" class="form-label">Genero</label>
                    <select class="form-select" id="genero" name="genero">
                        <option value="">Todos</option>
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                        <option value="Outros">Outros</option>
                        <option value="Não Binário">Não Binário</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="nome" name="nome">
                </div>
                <div class="col-md-3">
                    <label for="dataNascimento" class="form-label">Data de Nascimento</label>
                    <input type="date" class="form-control" id="dataNascimento" name="dataNascimento">
                </div>
                <div class="col-md-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" class="form-control" id="cpf" name="cpf">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-3">
                    <label for="telefone" class="form-label">Telefone</label>
                    <input type="tel" class="form-control" id="telefone" name="telefone">
                </div>
                <div class="col-md-4">
                    <label for="email" class="form-label">E-mail</label>
                    <input type="email" class="form-control" id="email" name="email">
                </div>
                <div class="col-md-5">
                    <label for="status" class="form-label">Status</label>
                     <select class="form-select" id="status" name="status">
                        <option value="">Todos</option>
                        <option value="Ativo">Ativo</option>
                        <option value="Inativo">Inativo</option>
                    </select>
                </div>
            </div>
          <div class="text-center">
         
            <div class="row mt-3">

            </div>
           </div>
        </form>
                        <div class="col-md-12 button-component">
    						<button id="btnBuscar" type="button" class="btn btn-primary">Buscar</button>
                            <button onclick="window.location.href='/les-ecommerce-vinhos/login.html';" type="submit" class="btn btn-primary">Cadastrar</button>
                        </div>
                <!-- Tabela de resultados -->
         <div class="text-center">
				<table id="tabela"  class="mx-auto">
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>E-mail</th>
								<th>CPF</th>
								<th>Tipo Telefone</th>
								<th>Telefone</th>
								<th>Nascimento</th>
								<th>Genero</th>
								<th>Status</th>
								<th>Opções</th>
							</tr>
						</thead>
						<tbody>
								<%
								   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							    for (int i = 0; i < lista.size(); i++) {
							        String dataNascimento = dateFormat.format(lista.get(i).getDataNasc());
								%>
							<tr>
								<td><%=lista.get(i).getId()%></td>
								<td><%=lista.get(i).getNome()%></td>
								<td><%=lista.get(i).getEmail()%></td>
								<td><%=lista.get(i).getCpf()%></td>
								<td><%=lista.get(i).getTipoTelefone()%></td>
								<td><%=lista.get(i).getTelefone()%></td>
								<td><%=dataNascimento %></td>
								<td><%=lista.get(i).getGenero()%></td>
								<td><%=lista.get(i).getStatus()%></td>
								
								<td>
								<div class= "option-button">
									<a href="/les-ecommerce-vinhos/areaCliente/Perfil.html?id=<%=lista.get(i).getId()%>" class="Botao1">Editar</a>
									<a href="javascript: confirmarCliente(<%=lista.get(i).getId()%>)"class="Botao2">Excluir</a>
								</div>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
			</div>
				    </div>
				</main>
         
        </div>
    </div>

    <footer class="p-4 text-light text-center" style="background: black;">
        Desenvolvido por Caynan e Raquel
    </footer>
	<script src="../scripts/confirmador.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../scripts/filtroTabela.js"></script>
    
</body>
</html>