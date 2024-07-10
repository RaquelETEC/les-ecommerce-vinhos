<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Administração</title>
    <link rel="icon" href="../imagens/favicon.png">
    <link rel="stylesheet" href="../Styles/styleAdm.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="../scripts/dashboard.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
        <div class="container-fluid">
            <a href='/les-ecommerce-vinhos/paginaInical.html'>
                <img src="../imagens/logo-vinho.PNG" alt="Logo Vinho" style="width: 200px;" class="mx-3">
            </a>            
        </div>
    </nav>
    
    <div class="container-fluid">
        <div class="row">
            <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-dark sidebar">
                <div class="position-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-Title">Menu</li>
                        <li class="nav-item">
                            <a class="nav-link bg-white text-dark" onclick="window.location.href='PagInicial.html';">Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/les-ecommerce-vinhos/areaAdministrador/Clientes.html">Clientes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/les-ecommerce-vinhos/areaAdministrador/PedidoVenda.html">Alterar Status de Pedido</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="window.location.href='TrocaPedidos.html';">Pedidos Aguardando Troca</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/les-ecommerce-vinhos/areaAdministrador/Produtos.html">Produtos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="window.location.href='Estoque.html';">Estoque</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="container mt-4">
                    <h2 class="text-center mb-4">Dashboard</h2>
                    
                    <!-- Filtros -->
                    <div class="row">
                        <div class="col-md-4">
                            <label for="produto" class="form-label">Produto:</label>
                            <select class="form-select" id="produto">
                                <option value="all">Todos os Produtos</option>
                                
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label for="dataDe" class="form-label">Data de:</label>
                            <input type="date" class="form-control" id="dataDe"  value="2023-01-01">
                        </div>
                        <div class="col-md-3">
                            <label for="dataAte" class="form-label">Data até:</label>
                            <input type="date" class="form-control" id="dataAte"  value="2024-06-04">
                        </div>
						<div class="col-md-2 d-flex align-items-end justify-content-center">
                          	<button onClick="filtrar()" id="filtrarBtn" class="btn btn-dark" style="min-width: 15vh;">Filtrar</button>
                        </div>
                    </div>
					
                     <!-- Gráficos -->
					<!-- Gráfico de Linha -->
					<div class="chat-line mt-5" style="min-width: 100%; max-height: 50vh;">
					    <canvas id="salesChart" style="min-width: 100%; max-height: 50vh;"></canvas>
					</div>
					<div class="row mt-4">
					    <!-- Gráfico de barras -->
					    <div class="col-md-6" style="height: 30vh;">
					        <p>Totais por Produto</p>
					        <canvas id="salesBarChart"></canvas>
					    </div>
					    <!-- Total vendido -->
					    <div class="col-md-6 text-center" style="height: 30vh;">
					        <p>Valor Total</p>
					        <p id="totalSales" style="font-size: 4vh; font-weight: 400; color: #7ac0c0;"></p>
					        <br>
					        <p>Quantidade Total</p>
					        <p id="totalSalesQuant" style="font-size: 4vh; font-weight: 400; color: #7ac0c0;"></p>
					    </div>
					</div>
                </div>
            </main>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>