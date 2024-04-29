var idCupomTroca = 0;
var idCupomCardTrocaSelecionado = '';
var idCartaoCardSelecionado = '';

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

	if (cadastrarEndNopPerfil == "Sim") {
		var dados = "id=" + id + "&nome=" + nome + "&typeTipoResidencia=" + tipoResidencia + "&typeTipoLogradouro=" + tipoLogradouro +
			"&typeLogradouro=" + logradouro + "&typeNumero=" + numero + "&typeBairro=" + bairro + "&typeCidade=" + cidade +
			"&typeEstado=" + estado + "&typeCep=" + cep + "&typePais=" + pais + "&observacoes=" + observacoes +
			"&tipoEndereco=" + tipoEndereco + "&venda=" + cadastrarEndNopPerfil;

		var url = "/les-ecommerce-vinhos/inserirEndereco?" + dados;
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


function addCupomTroca() {
	idCupomTroca++;
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
                    <button onClick='atualizaCupomIdSelecionado(${idCupomTroca})' type="button" class="btn btn-success" data-toggle="modal" data-target="#modalCupomT">Selecionar Cupom de Troca</button>
                </div>
            </div>
        </div> 
    `;

	// Adiciona o novo card ao container de cupons
	document.getElementById("cupomContainer").appendChild(novoCard);
};

function atualizaCupomIdSelecionado(idCupomTroca) {
	idCupomCardTrocaSelecionado = idCupomTroca;
}

function salvarCupom(name, modal) {
	var cupomSelecionado = document.querySelector(`input[name="${name}"]:checked`);

	var valorCupomPTotal = 0;
	var valorCupomTTotal = 0;


	if (cupomSelecionado) {
		var cupomId = cupomSelecionado.value;
		if (name == "cupomsSelecionadoP") {

			let valorCupomPTotal = parseFloat(document.getElementById(`ValorCupom${cupomId}`).textContent);

			document.getElementById('idCupomSrc').textContent = document.getElementById(`imagemCupom${cupomId}`);
			document.getElementById('idCupomDesc').textContent = document.getElementById(`NomeCupom${cupomId}`).textContent;
			document.getElementById('idCupomValor').textContent = document.getElementById(`ValorCupom${cupomId}`).textContent;

			calcularTotais(valorCupomPTotal, valorCupomTTotal)
		} else {
			valorCupomTTotal = parseFloat(document.getElementById(`ValorCupomT${cupomId}`).textContent);

			document.getElementById(`imagemCupom_cupomCard_${idCupomCardTrocaSelecionado}`).textContent = document.getElementById(`imagemCupomT${cupomId}`);
			document.getElementById(`NomeCupom_cupomCard_${idCupomCardTrocaSelecionado}`).textContent = document.getElementById(`NomeCupomT${cupomId}`).textContent;
			document.getElementById(`ValorCupom_cupomCard_${idCupomCardTrocaSelecionado}`).textContent = document.getElementById(`ValorCupomT${cupomId}`).textContent;

			calcularTotais(valorCupomPTotal, valorCupomTTotal)
		}


		$(`#${modal}`).modal('hide');

	} else {
		console.log("Selecione um cupom ou vincule um novo!");
	}
}



function addCartaoCard() {
	// Recupera o elemento do cartão selecionado
	var cartaoSelecionado = document.querySelector('input[name="cartaoSelecionado"]:checked').value;

	// Obtém os valores dos atributos de dados
	var nomeCartao = document.getElementById(`nomeCartao${cartaoSelecionado}`).textContent.trim();
	var numeroCartao = document.getElementById(`numeroCartao${cartaoSelecionado}`).textContent;
	var imagemBandeira = document.getElementById(`imagemBandeira${cartaoSelecionado}`);
	var codigoCartao = document.getElementById(`codSeguranca${cartaoSelecionado}`).textContent;
	var valorCartao = parseFloat(document.getElementById('valorCartao').value);

	// Verifica se a soma de todos os cupons é equivalente a 90% do total do pedido
	var totalCupons = totalCupomPromocional + totalCupomTroca;
	var totalPedido = parseFloat(document.getElementById("idTotalPedido").textContent.replace('R$', '').trim());
	var percentualCupons = (totalCupons * 100) / totalPedido;

	if (percentualCupons >= 85) {
		// Permite adicionar um cartão com valor de R$ 10,00
		insertCardCartao(nomeCartao, numeroCartao, imagemBandeira.src, codigoCartao, valorCartao);
		calcularTotais(0, 0, parseFloat(valorCartao));
	} else {
		// O mínimo é R$ 10,00 por cartão
		if (valorCartao >= 10) {
			insertCardCartao(nomeCartao, numeroCartao, imagemBandeira.src, codigoCartao, valorCartao);
			calcularTotais(0, 0, parseFloat(valorCartao));
		} else {
			alert("O valor mínimo para o cartão é de R$ 10,00.");
		}
	}
}

function AdicionarNovoCartao(idCliente) {
    var totalCupons = totalCupomPromocional + totalCupomTroca;
    var totalPedido = parseFloat(document.getElementById("idTotalPedido").textContent.replace('R$', '').trim());
    var percentualCupons = (totalCupons * 100) / totalPedido;

    if (percentualCupons >= 85 || valorCartao === 10) {
        var formulario = document.forms["frmCartao"];
        var nome = formulario["CartaoNome"].value;
        var bandeira = formulario["tipoBandeira"].value;
        var numero = formulario["CartaoNumero"].value;
        var codigo = formulario["CartaoCodigo"].value;
        var cadastrarNoPerfil = formulario["cadastrarCartNoPerfil"].value;
        var valorCartao = formulario["valorCartaoCad"].value;
        var cartaoPadrao = 'NÃO';
        var imagemBandeira = bandeira == 1 ? 'imagens/assets/CartaoMaster.png' : 'imagens/assets/CartaoVisa.png';

        if (cadastrarNoPerfil == "Sim") {
            var dados =
                "typeId=" + idCliente +
                "&CartaoNumero=" + numero +
                "&CartaoNome=" + nome +
                "&CartaoPadrao=" + cartaoPadrao +
                "&CartaoCodigo=" + codigo +
                "&tipoBandeira=" + bandeira +
                "&tipoSolicitacao=" + 'venda';

            var url = "/les-ecommerce-vinhos/areaCliente/inserirCartao?" + dados;

            fazerRequisicaoAjax(url, function(resposta) {
                alert(resposta);
                insertCardCartao(nome, numero, imagemBandeira, codigo, valorCartao);
                calcularTotais(0, 0, parseFloat(valorCartao));
            }, function() {
                alert("Erro ao cadastrar Cartão");
            });
        } else {
             insertCardCartao(nomeCartao, numeroCartao, imagemBandeira, codigoCartao, valorCartao)
             calcularTotais(0, 0, parseFloat(valorCartao));
        }
    } else {
        alert("O valor mínimo para o cartão deve ser R$ 10,00.");
    }
}
function insertCardCartao(nomeCartao, numeroCartao, imagemBandeira, codigoCartao, valorCartao) {
	// Cria um novo elemento div para representar o card de forma de pagamento
	var novoPagamento = document.createElement("div");
	novoPagamento.classList.add("CartoesSection");

	// Define o conteúdo do card de forma de pagamento usando os valores recuperados
	novoPagamento.innerHTML = `
        <div class="row lineCart">
            <div class="col-2">
                <p id="cartao" class="text-md-start">${nomeCartao}</p>
            </div>
            <div class="col-2">
                <p id="numeroCartao" class="text-md-start">${numeroCartao}</p>
            </div>
	            <div class="col-2">
				    <img src="${imagemBandeira}" alt="Imagem da Bandeira" class="img-fluid" style="width: 35%; height: auto;">
				</div>
            <div class="col-1">
                <p class="text-md-start">${codigoCartao}</p>
            </div>
            <div class="col-2">
                <p class="text-center">${valorCartao}</p>
            </div>
            <div class="col-3">
             	<button class="Botao2" onClick="Editar(this)">Editar</button>
                <button class="Botao1" onClick="removerCartao(this)">Excluir</button>
            </div>
        </div>
    `;

	// Adiciona o novo card de forma de pagamento ao container de formas de pagamento
	document.getElementById("pagamentoContainer").appendChild(novoPagamento);
}



function removerCartao(botao) {
	// Remove o card de forma de pagamento pai do botão clicado
	var cardPagamento = botao.closest('.lineCart');
	cardPagamento.remove();
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

let totalCupomPromocional = 0.00;
let totalCupomTroca = 0.00;
let totalCartoes = 0.00;
let totalPedido = 0.00;
function calcularTotais(valorCupomPerc = 0, valorCupomT = 0, valorCartoes = 0) {
	let TotalProduto = parseFloat(document.getElementById("idTotalProduto").textContent.replace('R$', '').trim());

	// Somando os valores aos totais
	totalCupomPromocional += parseFloat(valorCupomPerc);
	totalCupomTroca += parseFloat(valorCupomT);
	totalCartoes += parseFloat(valorCartoes); // Basta adicionar o valor dos cartões

	let totalCupomPValor = TotalProduto - (totalCupomPromocional > 0 ? (totalCupomPromocional * 100) / TotalProduto : 0);

	totalPedido = TotalProduto - totalCupomPValor - totalCupomTroca;
	totalAPagar = totalPedido - totalCartoes;


	// Arredondando os totais para duas casas decimais
	totalCupomPValor = parseFloat(totalCupomPValor.toFixed(2));
	totalCupomTroca = parseFloat(totalCupomTroca.toFixed(2)); // Convertendo para número e arredondando
	total = parseFloat(totalPedido.toFixed(2));
	totalCartoes = parseFloat(totalCartoes.toFixed(2)); // Convertendo para número e arredondando
	totalAPagar = parseFloat(totalAPagar.toFixed(2)); // Convertendo para número e arredondando

	document.getElementById(`idTotalCupomP`).textContent = `R$ ${totalCupomPValor}`;
	document.getElementById(`idTotalCupomT`).textContent = `R$ ${totalCupomTroca}`;
	document.getElementById(`idTotalPagamento`).textContent = `R$ ${totalCartoes}`;
	document.getElementById(`idTotalApagar`).textContent = `R$ ${totalAPagar}`;

}
function validarPedido(idCliente) {
	debugger
    let totalCupomPromocional = parseFloat(document.getElementById(`idTotalCupomP`).textContent.replace('R$', '').trim())
	let totalCupomTroca = parseFloat(document.getElementById(`idTotalCupomT`).textContent.replace('R$', '').trim())

	let totalCupons =  totalCupomPromocional + totalCupomTroca;

	totalPedido = parseFloat(document.getElementById("idTotalPedido").textContent.replace('R$', '').trim());
	totalPagamento = parseFloat(document.getElementById("idTotalPagamento").textContent.replace('R$', '').trim());
	
	
	if (totalCartoes > totalPedido) {
		alert('O valor do pagamento ultrapassou o total a se pagar');
	} 
	else {
		cadastrarPedidoVenda(idCliente);
	}

}


function cadastrarPedidoVenda(idCliente) {
	debugger
	totalPedido = parseFloat(document.getElementById("idTotalPedido").textContent.replace('R$', '').trim());

	var dados = "idCliente=" + idCliente + "&totalPedido=" + totalPedido;

	var url = "/les-ecommerce-vinhos/CadastrarPedidoVenda?" + dados;
	fazerRequisicaoAjax(url, function(resposta) {
		if (resposta.includes("erro")) {
			alert(resposta);
		}
		else {
			window.location.href = "/les-ecommerce-vinhos/areaVenda/VendaFinalizada.html" + resposta;
		}
	}, function() {
		alert("Erro ao cadastrar pedido JS");
	});
}


