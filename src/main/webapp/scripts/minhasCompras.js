window.onload = function() {
    filterStatus('Tudo'); // Exibir todos os pedidos inicialmente
};

function filterStatus(status) {
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



function solicitarTroca(pedidoId) {
    const checkboxes = document.querySelectorAll('.item-troca:checked');
    const itensSelecionados = Array.from(checkboxes).map(cb => {
		cb.getAttribute('data-item')
		cb.getAttribute('data-pedido')
		} );


    // Aqui você pode implementar a lógica para enviar a solicitação de troca ao servidor
    alert(`Solicitando troca para os itens: ${itensSelecionados.join(', ')}`);
    // Exemplo: fazer uma requisição AJAX para solicitar a troca ao servidor
    // Aqui, `pedidoId` é o ID do pedido para o qual a troca está sendo solicitada
    // e `itensSelecionados` é um array com os IDs dos itens selecionados
    // Implemente a lógica de envio da solicitação ao servidor de acordo com sua aplicação
 }