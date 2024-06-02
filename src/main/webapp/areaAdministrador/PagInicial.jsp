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
            <a href='http://localhost:8080/les-ecommerce-vinhos/paginaInical.html'>
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
                        <div class="col-md-4">
                            <label for="dataDe" class="form-label">Data de:</label>
                            <input type="date" class="form-control" id="dataDe">
                        </div>
                        <div class="col-md-4">
                            <label for="dataAte" class="form-label">Data até:</label>
                            <input type="date" class="form-control" id="dataAte">
                        </div>
                    </div>
                    
                    <!-- Botão de Filtrar -->
                    <div class="row mt-4">
                        <div class="col-md-12 text-center">
                            <button onClick="filtrar()" id="filtrarBtn" class="btn btn-primary">Filtrar</button>
                        </div>
                    </div>
                    
                    <!-- Gráficos -->
                    <div class="row mt-4">
                        <!-- Gráfico de Linha -->
                        <div class="col-md-6">
                            <canvas id="salesChart" style="width: 100%; height: 300px;"></canvas>
                        </div>
                        <!-- Gráfico de Pizza -->
                        <div class="col-md-6">
                            <canvas id="salesPieChart" style="width: 100%; height: 300px;"></canvas>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <footer class="p-4 text-light text-center" style="background: black;">
        Desenvolvido por Caynan e Raquel
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>