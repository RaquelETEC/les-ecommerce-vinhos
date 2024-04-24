var idCupomTroca = 0; 

function addCupomTroca() {	
	debugger
	idCupomTroca ++; 	
    // Cria um novo elemento div para representar o card de cupom
    var novoCard = document.createElement("div");
    novoCard.classList.add("card", "mt-3", "styleCards");

    // Cria um identificador exclusivo para o card de cupom
    var cardId = "cupomCard_" + idCupomTroca; // Adicione um número único ou o ID do cupom como parte do identificador

    // Conteúdo do card de cupom
    novoCard.innerHTML = `
        <div id="${cardId}" class="card-body">
            <div class="row">
                <!-- Parte esquerda com a imagem do cupom -->
                <div class="col-md-1">
                    <img id="imagemCupom_${cardId}" src="imagens/assets/CupomFrete.png" alt="Imagem do Cupom"
                    class="img-fluid rounded-start" style="max-width: 70px; max-height: 70px;">
                </div>
                <!-- Parte direita com o nome do cupom e a data de vencimento -->
                <div class="col-md-8">
                    <h5 id="NomeCupom_${cardId}" class="card-title"></h5>
                    <p id="ValorCupom_${cardId}" class="card-text"></p>
                </div>
                <div class="col-md-3 text-end">
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCupomT">Selecionar Cupom de Troca</button>
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

        // Atualize os spans com os valores dos campos do endereço
        document.getElementById('Logradouro').textContent = document.getElementById(`Logradouro${enderecoId}`).textContent;
        document.getElementById('Numero').textContent = document.getElementById(`Numero${enderecoId}`).textContent;
        document.getElementById('Bairro').textContent = document.getElementById(`Bairro${enderecoId}`).textContent;
        document.getElementById('Cidade').textContent = document.getElementById(`Cidade${enderecoId}`).textContent;
        document.getElementById('Estado').textContent = document.getElementById(`Estado${enderecoId}`).textContent;
        document.getElementById('Cep').textContent = document.getElementById(`Cep${enderecoId}`).textContent;
        document.getElementById('Pais').textContent = document.getElementById(`Pais${enderecoId}`).textContent;
        document.getElementById('Nome').textContent = document.getElementById(`Nome${enderecoId}`).textContent;

        // Feche o modal de seleção de endereço, se necessário
        $('#modalSelecaoEndereco').modal('hide');
        
    } else {
        // Se nenhum radio button estiver selecionado, exiba uma mensagem de erro
        console.log("Selecione um endereço ou cadastre!");
    }
}
function AdicionarNovoEndereco(id) {
    // Obtenha os valores dos campos do formulário
    // Obtenha uma referência ao formulário pelo seu nome
        var formulario = document.forms["frmcliente"];

        // Obtenha os valores dos campos individualmente
        var nome = formulario["nome"].value;
        var tipoResidencia = formulario["typeTipoResidencia"].value;
        var tipoLogradouro = formulario["typeTipoLogradouro"].value;
        var logradouro = formulario["typeLogradouro"].value;
        var numero = formulario["typeNumero"].value;
        var bairro = formulario["typeBairro"].value;
        var cidade = formulario["typeCidade"].value;
        var estado = formulario["typeEstado"].value;
        var cep = formulario["typeCep"].value;
        var pais = formulario["typePais"].value;
        var observacoes = formulario["observacoes"].value;
        var cadastrarEndNopPerfil = formulario["cadastrarEndNopPerfil"].value;
    	var tipoEndereco = "ENTREGA";
    
	if(cadastrarEndNopPerfil == "Sim"){
	    var dados = "id="+id+"&nome=" + nome + "&typeTipoResidencia=" + tipoResidencia + "&typeTipoLogradouro=" + tipoLogradouro + 
	                "&typeLogradouro=" + logradouro + "&typeNumero=" + numero + "&typeBairro=" + bairro + "&typeCidade=" + cidade + 
	                "&typeEstado=" + estado + "&typeCep=" + cep + "&typePais=" + pais + "&observacoes=" + observacoes + 
	                "&tipoEndereco=" + tipoEndereco + "&venda=" + cadastrarEndNopPerfil ;
	
		var url="/les-ecommerce-vinhos/inserirEndereco?"+dados;
	    fazerRequisicaoAjax(url, function(resposta) {
			
	        alert(resposta);
	    }, function() {
	        alert("Erro ao cadastrar endereço");
	    });
	}
	
  // Atualize os spans com os valores dos campos do endereço
    document.getElementById('Logradouro').textContent = nome;
    document.getElementById('Numero').textContent = numero;
    document.getElementById('Bairro').textContent = bairro;
    document.getElementById('Cidade').textContent = cidade;
    document.getElementById('Estado').textContent = estado;
    document.getElementById('Cep').textContent = cep;
    document.getElementById('Pais').textContent = pais;
    document.getElementById('Nome').textContent = observacoes;

}

function salvarCupom(name, modal){
debugger
    var cupomSelecionado = document.querySelector(`input[name="${name}"]:checked`);

    if (cupomSelecionado) {

        var cupomId = cupomSelecionado.value;
        if(name == "cupomsSelecionadoP"){
			document.getElementById('idCupomSrc').textContent = document.getElementById(`imagemCupom${cupomId}`)	;
	        document.getElementById('idCupomDesc').textContent = document.getElementById(`NomeCupom${cupomId}`).textContent;
	        document.getElementById('idCupomValor').textContent = document.getElementById(`ValorCupom${cupomId}`).textContent;			
		}else{
        	var cardId = cupomSelecionado.closest('.card-body').id;
        	
			document.getElementById(`imagemCupom_${cardId}`).textContent = document.getElementById(`imagemCupom${cupomId}`)	;
	        document.getElementById(`NomeCupom_${cardId}`).textContent = document.getElementById(`NomeCupom${cupomId}`).textContent;
	        document.getElementById(`ValorCupom_${cardId}`).textContent = document.getElementById(`ValorCupom${cupomId}`).textContent;			
		}
        
        $(`#${modal}`).modal('hide');
        
    } else {
        console.log("Selecione um cupom ou vincule um novo!");
    }
	}


function fazerRequisicaoAjax(url, sucessoCallback, erroCallback) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                sucessoCallback(xhr.responseText);
            } else {
                erroCallback();
            }
        }
    };
    xhr.send();
}
