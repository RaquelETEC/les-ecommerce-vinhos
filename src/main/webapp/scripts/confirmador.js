/**
 * Confirmar a exclusao de um contato
 * 
 * @author Professor Jose de Assis
 * @param idcon
 */

function confirmar(idcon) {
	let resposta = confirm("Confirma a exclusão deste contato?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
}


function confirmarCliente(id) {
	let resposta = confirm("Confirma a exclusão deste contato? ID:" + id)
	if (resposta === true) {
		window.location.href = "deleteClient?id=" + id
	}
}


function confirmarExcluirEndereco(idEnd, id) {
	let resposta = confirm("Confirma a exclusão deste contato? ID:" + idEnd)
	if (resposta === true) {
		window.location.href = "deleteEndereco?idEnd=" + idEnd + "&id=" + id;
	}
}



function confirmarProduto(idPedido, i, ValorCupom, idCliente) {
	debugger
	const Status = document.getElementById('statusPedido' + i).value;

	if (Status == "CANCELADO") {

		const url = `/les-ecommerce-vinhos/areaCliente/InserirCupomTroca?idPedido=${idPedido}&ValorCupom=${ValorCupom}&idCliente=${idCliente}`;

		fazerRequisicaoAjax(url, function(resposta) {
			if (resposta > 0) {
				alert("Cupom gerado" + resposta)
			}
			else
				alert("Erro ao solicitar cupom");
		}, function() {
			alert("Erro ao gerenciar troca JS");
		});

	} else {

		window.location.href = "EditarPedido?id=" + idPedido + "&PedidoStatus=" + Status;
		alert("Status alterado com sucesso");
	}



}

function confirmarTroca (idItem ="",pedidoId="",novoStatus="",novoStatusItem=""){
	const itens = [idItem];

	var dados = `&itens=${itens.join(',')}&pedido=${pedidoId}&novoStatusPedido=${novoStatus}&tipoSolicitacao=${novoStatusItem}`;
   
	var url = "/les-ecommerce-vinhos/areaCliente/solicitarTroca.html?" + dados;
	fazerRequisicaoAjax(url, function(resposta) {
		if (resposta.includes("erro")) {
			alert(resposta);
		}
		else {
			alert("Status alterado com sucesso");
			window.location.href = window.location;
		}
	}, function() {
		alert("Erro ao cadastrar pedido JS");
	});
}
	
	
// Variáveis para armazenar os dados do botão "Confirmar Recebimento"
let itemId, pedidoId, prodId, total, quantidadeItem, novoStatus, novoStatusItem, clienteId;

// Função para capturar os dados do botão "Confirmar Recebimento"
function capturarDadosConfirmarRecebimento(btn) {
    itemId = btn.getAttribute('data-item-id');
    prodId = btn.getAttribute('data-prod-id');
	total = btn.getAttribute('data-prod-valor');
    pedidoId = btn.getAttribute('data-pedido-id');
    clienteId = btn.getAttribute('data-cliente-id');
    quantidadeItem = btn.getAttribute('data-quant-item');
    novoStatus = btn.getAttribute('data-novo-status');
    novoStatusItem = btn.getAttribute('data-novo-status-item');
    
}

function processarConfirmacao(confirmacao) {
	confirmarTroca (itemId,pedidoId,novoStatus,novoStatusItem)
   	gerarCupomTroca(pedidoId,prodId,total,clienteId);
   	
    if (confirmacao)
	//movimentarEstoque(prodId, pedidoId, quant);


    itemId = null;
    pedidoId = null;
    quantidadeItem = null;
    novoStatus = null;
    novoStatusItem = null;
}

function movimentarEstoque(itemId, pedidoId, quant){
	alert("voce movimentou o estoque")
}
	
function gerarCupomTroca(pedidoId, prodId,total,clienteId)	{
	debugger;
	var dados = `&pedido=${pedidoId}&prodId=${prodId}&valorCupom=${total}&tipoCupom=T`;
   
	var url = "http://localhost:8080/les-ecommerce-vinhos/gerarCupom.html?" + dados;
	fazerRequisicaoAjax(url, function(resposta) {
		if (resposta.includes("erro")) {
			alert(resposta);
		}
		else {
			vincularCupomAoCliente(resposta, clienteId);
			
		}
	}, function() {
		alert("Erro ao cadastrar pedido JS");
	});
}
	
	
function vincularCupomAoCliente(cupomId, clienteID){
	debugger;
	var dados = `&cupomId=${cupomId}&clienteId=${clienteID}`;
   
	var url = "http://localhost:8080/les-ecommerce-vinhos/VincularCupomAoCliente.html?" + dados;
	fazerRequisicaoAjax(url, function(resposta) {
		if (resposta.includes("erro")) {
			alert(resposta);
		}
		else {
			alert("Cupom vinculado ao cliente com sucesso!");
		}
	}, function() {
		alert("Erro ao cadastrar pedido JS");
	});
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
