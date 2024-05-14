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
        else{
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