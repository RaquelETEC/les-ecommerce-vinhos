<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>tela 1</title>
    <link rel="stylesheet" href="StyleAreaCliente.css">
    <script src="./scripts/exibirMenus.js" defer></script>
</head>

<body>
    <main>
        <header>
            <h1 class="title-crnines">
                <span style="color:#fff;"> CR </span> WINES
            </h1>
            <button class="button-header-minha-conta"> MINHA CONTA <img src="./imagens/assets/icon-button-header.png"
                    alt="icon-button-header" class="icon-button-header"></button>
        </header>

        <div class="container-main">

            <!-- conteudo do perfil da direita -->
            <div class="container-perfil">
                <div class="box-name-perfil">
                    <img src="./imagens/assets/icon-box-image-name.png" alt="" class="img-name-perfil">
                    <div class="box-name-e-nivel">
                        <p class="text-name">Usuario</p>
                        <p class="text-nivel">NIVEL: 1</p>
                    </div>
                </div>

                <div class="box-dados-perfil">
                    <button class="button-dados-perfil" onclick="exibirMinhaConta()">
                        <img src="./imagens/assets/icons-left-perfil-1.png" alt="" class="img-icons-perfil">
                        <span>Minha Conta</span>
                    </button>
                    <div class="box-exibir-opcoes MinhaConta">
                        <button>Perfil</button>
                        <button>Cartoes</button>
                        <button>Enderecos</button>
                        <button>Trocar Senha</button>
                    </div>

                    <button class="button-dados-perfil">
                        <img src="./imagens/assets/icons-left-perfil-2.png" alt="" class="img-icons-perfil">
                        <span>Minhas Compras</span>
                    </button>
                   

                    <button class="button-dados-perfil">
                        <img src="./imagens/assets/icons-left-perfil-3.png" alt="" class="img-icons-perfil">

                        <span>Notificacoes</span>
                    </button>
                    

                    <button class="button-dados-perfil">
                        <img src="./imagens/assets/icons-left-perfil-4.png" alt="" class="img-icons-perfil">
                        <span>Meus Cupons</span>
                    </button>
                    
                </div>

            </div>
            <!-- acaba aqui  -->

            <div class="container-register">

                <div class="box-top-register">
                    <!-- titulo do cabecalho -->
                    <h1 class="text-perfil">Perfil</h1>

                    <div class="box-input-pesquisa-e-carrinho">

                        <div class="box-input-top-pesquisar">
                            <input type="text" class="input-top-pesquisar" placeholder="Busca">
                            <button class="button-top-register"><img src="./imagens/assets/icons-lupa-input.png" alt=""
                                    class="img-top-register"></button>
                        </div>

                        <button class="button-top-register"><img src="./imagens/assets/icons-carrinho.png" alt=""
                                class="img-top-register"></button>

                    </div>

                </div>

                <div class="content">
                    <!-- conteudo que voce pode trocar, no caso eh o conteudo da parte branca, lado direito maior -->
                    <form action="" class="form-register">

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="">Nome</label>
                            </div>
                            <input type="text" class="input-form input-width-1" 
                            value="<%out.print(request.getAttribute("typeNome"));%>" >
                            
                        </div>
                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="">E-Mail</label>
                            </div>
                            <input type="text" class="input-form input-width-1 ">
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="">CPF</label>
                            </div>
                            <input type="text" class="input-form input-width-2 ">
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="">Data de nascimento</label>
                            </div>
                            <input type="date" class="input-form input-width-3 ">
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="">Telefone</label>
                            </div>
                            <input type="tel" class="input-form input-width-2 ">
                        </div>

                        <div class="box-input-form">
                            <div class="box-label">
                                <label for="">Genero</label>
                            </div>
                            <input type="radio" name="genero" id="Feminino" class="input-radio">
                            <label for="Feminino" style="font-weight: 100;">Feminino</label>

                            <input type="radio" name="genero" id="Masculino" class="input-radio">
                            <label for="Masculino" style="font-weight: 100;">Masculino</label>

                            <input type="radio" name="genero" id="Outro" class="input-radio">
                            <label for="Outro" style="font-weight: 100;">Outro</label>
                        </div>

                        <button class="button-salvar">Salvar</button>

                    </form>
                    <!-- acaba aqui -->

                </div>

            </div>


        </div>
    </main>
</body>

</html>