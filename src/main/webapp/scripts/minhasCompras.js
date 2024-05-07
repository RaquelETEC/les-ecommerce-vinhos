window.onload = function() {
    filterStatus('Tudo'); // Exibir todos os pedidos inicialmente
};

function filterStatus(status) {
	debugger
    const pedidos = document.querySelectorAll('.custom-container');

    pedidos.forEach(pedido => {
        const pedidoStatus = pedido.className.split("=")[1];
        
        if (!(status === 'Tudo' || pedidoStatus === status) && pedidoStatus !== 'tabs' ) {
			pedido.style.display = 'none'; // Ocultar o pedido se não corresponder ao status
        }
        else{
			pedido.style.display = 'block'; // Exibir o pedido
		}
    });
}


function solicitarTroca(pedido,produto){
	alert("voce solicitou uma troca");
}