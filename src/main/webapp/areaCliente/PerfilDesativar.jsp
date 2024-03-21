<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Excluir conta</title>
    <link rel="stylesheet" href="../Styles/StyleAreaCliente.css">
    <script src="../scripts/exibirMenus.js" defer></script>
        <script src="../scripts/confirmador.js" defer></script>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
    
</head>

<body>
 
	<nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
		<div class="container-fluid">
            <a href="'index.html'"><img src="../imagens/logo-vinho.PNG" alt="Logo Vinho" style="width: 200px;" class="mx-3"></a>
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
                        <span >Minha Conta</span>
                    </button>
                    <div class="box-exibir-opcoes MinhaConta">
                        <button onclick="window.location.href='Perfil.jsp';">Perfil</button>
                   		<button onclick="window.location.href='MeusCartoes.html';">Cartoes</button>
                        <button onclick="window.location.href='MeusEnderecos.html';">Enderecos</button>
                        <button onclick="window.location.href='PerfilTrocarSenha.jsp';">Trocar Senha</button>
                        <button onclick="window.location.href='PerfilExcluir.jsp';">apagar conta</button>
                    </div>

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-2.png" alt="" class="img-icons-perfil">
                    <span onclick="window.location.href='MinhasCompras.html';">Minhas Compras</span>
                    </button>
                   

                    <button class="button-dados-perfil">
                        <img src="../imagens/assets/icons-left-perfil-3.png" alt="" class="img-icons-perfil">

                        <span>Notificacoes</span>
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
                    <h1 class="text-perfil">Desativar Conta</h1>

                    <div class="box-input-pesquisa-e-carrinho">

                        <div class="box-input-top-pesquisar">
                            <input type="text" class="input-top-pesquisar" placeholder="Busca">
                            <button class="button-top-register"><img src="../imagens/assets/icons-lupa-input.png" alt=""
                                    class="img-top-register"></button>
                        </div>

                        <button class="button-top-register"><img src="../imagens/assets/icons-carrinho.png" alt=""
                                class="img-top-register"></button>

                    </div>

                </div>

                <div class="content">
                    <!-- conteudo que voce pode trocar, no caso eh o conteudo da parte branca, lado direito maior -->
                    <form action="" class="form-register">

                      <div class="box-input-form">
                            <div class="box-label">
                                <label for="">Informe sua senha</label>
                            </div>
                             <input type="text" class="input-form input-width-1">
                            
                        </div>

							<button onclick="excluirConta()" class="button-salvar">Desativar Conta</button>

                    </form>
                    <!-- acaba aqui -->

                </div>

            </div>


        </div>
    </main>
</body>

</html>