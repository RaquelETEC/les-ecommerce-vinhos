
 
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
                        <img src="imagens/assets/CupomFrete.png" alt="Imagem do Cupom"
                        class="img-fluid rounded-start" style="max-width: 70px; max-height: 70px;">

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
    
 function addCartao(id){
 // Cria um novo elemento div para representar o card de cupom
        var novoPagamento = document.createElement("div");
        novoPagamento.classList.add("styleCards");

        // Conteúdo do card de cupom
        novoPagamento.innerHTML = `
            <div class="row">
				<div class="col-md-2">
					<p class="text-md-start">raquel</p>
				</div>
				<div class="col-md-2">
					<p class="text-md-start">15454</p>
				</div>
				<div class="col-md-2">
					<p class="text-md-start">123</p>
				</div>
				<div class="col-md-2">
					<p class="text-md-start">1200</p>
				</div>
			</div>
        `;

        // Adiciona o novo card ao container de cupons
        document.getElementById("pagamentoContainer").appendChild(novoPagamento);
    };
    
    
function salvarEndereco() {
    // Obtenha o radio button selecionado
    var enderecoSelecionado = document.querySelector('input[name="enderecoSelecionado"]:checked');

    // Verifique se algum radio button está selecionado
    if (enderecoSelecionado) {
        // Obtenha o valor do atributo value do radio button selecionado
        var enderecoId = enderecoSelecionado.value;
		var logradouro = document.getElementById(`Logradouro${enderecoId}`).value;
		
        // Atualize os spans com os valores dos campos do endereço
        document.getElementById('Logradouro').textContent = document.getElementById(`Logradouro${enderecoId}`).value;
        document.getElementById('Numero').textContent = document.getElementById(`Numero${enderecoId}`).value;
        document.getElementById('Bairro').textContent = document.getElementById(`Bairro${enderecoId}`).value;
        document.getElementById('Cidade').textContent = document.getElementById(`Cidade${enderecoId}`).value;
        document.getElementById('Estado').textContent = document.getElementById(`Estado${enderecoId}`).value;
        document.getElementById('Cep').textContent = document.getElementById(`Cep${enderecoId}`).value;
        document.getElementById('Pais').textContent = document.getElementById(`Pais${enderecoId}`).value;
        document.getElementById('Nome').textContent = document.getElementById(`Nome${enderecoId}`).value;

        // Feche o modal de seleção de endereço, se necessário
        $('#modalSelecaoEndereco').modal('hide');
    } else {
        // Se nenhum radio button estiver selecionado, exiba uma mensagem de erro
        console.log("Selecione um endereço ou cadastre!");
    }
}

