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

