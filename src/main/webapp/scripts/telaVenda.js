 function addCupomTroca(id){
 // Cria um novo elemento div para representar o card de cupom
        var novoCard = document.createElement("div");
        novoCard.classList.add("card", "mt-3", "styleCards");

        // Conteúdo do card de cupom
        novoCard.innerHTML = `
            <div class="card-body">
                <div class="row">
                    <!-- Parte esquerda com a imagem do cupom -->
                    <div class="col-md-1">
                        <img src="imagens/assets/CupomFrete.png" alt="Imagem do Cupom" class="img-fluid">
                    </div>
                    <!-- Parte direita com o nome do cupom e a data de vencimento -->
                    <div class="col-md-8">
                        <h5 class="card-title">Nome do Cupom</h5>
                        <p class="card-text">Data de vencimento: 20/03/2024</p>
                    </div>
                    <div class="col-md-3 text-end">
                        <a href="/les-ecommerce-vinhos/areaVenda/VendaTrocaEndereco.html" class="btn btn-success">Selecionar Cupom</a>
                    </div>
                </div>
            </div> 
        `;

        // Adiciona o novo card ao container de cupons
        document.getElementById("cupomContainer").appendChild(novoCard);
    };