window.onload = function() {
    filterStatus('Tudo'); // Exibir todos os pedidos inicialmente
};

function filterStatus(status) {
    const pedidos = document.querySelectorAll('.custom-container');

    pedidos.forEach(pedido => {
        const pedidoStatus = pedido.className.split("=")[1];
        
        if (!(status === 'Tudo' || pedidoStatus.includes(status)) && pedidoStatus !== 'tabs' ) {
			pedido.style.display = 'none'; // Ocultar o pedido se não corresponder ao status
        }
		else {
			pedido.style.display = 'block'; // Exibir o pedido
		}
		
    });
}

function cancelarPedido(idPedido) {
    const novoStatus = "EM CANCELAMENTO"; 
    const confirmacao = confirm("Tem certeza de que deseja cancelar este pedido?");
    if (confirmacao) {
        
     const url = `/les-ecommerce-vinhos/areaAdministrador/EditarPedido?id=${idPedido}&PedidoStatus=${novoStatus}`;
 
    fazerRequisicaoAjax(url, function(resposta) {
		if(resposta > 0){
        	alert("Erro ao solicitar cancelamento");
        }
        else
        	alert("Cancelamento solicitado" + resposta)
    }, function() {
        alert("Erro ao gerenciar troca JS");
    });
        
    }
}

function solicitarTroca(pedidoId,novoStatusPedido) {
	debugger
    const checkboxes = document.querySelectorAll('.item-troca[data-pedido="' + pedidoId + '"]:checked');

    const itensSelecionados = Array.from(checkboxes).map(cb => cb.getAttribute('data-item'));

    if (itensSelecionados.length === 0) {
        alert('Selecione pelo menos um item para troca.');
        return;
    }

    const url = `/les-ecommerce-vinhos/areaCliente/solicitarTroca.html?pedido=${pedidoId}&novoStatusPedido=${novoStatusPedido}&tipoSolicitacao=TROCA_SOLICITADA&itens=${itensSelecionados.join(',')}`;

    fazerRequisicaoAjax(url, function(resposta) {
		if(resposta.includes("error")){
        	alert("Erro ao solicitar Troca");
        }
        else 
        	alert("Troca dos itens: "+itensSelecionados.join(',')+ " solicitada")
        	window.location.href = window.location;
    }, function() {
        alert("Erro ao gerenciar troca JS");
    });
}
 
 //da para transformar em uma so função
 function enviarItems(idItem ="",pedidoId="",novoStatus="",novoStatusItem=""){
	debugger;
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
 
 
function handleChecketAll(event, pedidoId) {
    if (event.target.checked) {
        // Selecionar todos os checkboxes associados ao pedido
        var checkboxes = document.querySelectorAll('.item-troca[data-pedido="' + pedidoId + '"]');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = true;
        });
    } else {
        // Desmarcar todos os checkboxes associados ao pedido
        var checkboxes = document.querySelectorAll('.item-troca[data-pedido="' + pedidoId + '"]');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = false;
        });
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