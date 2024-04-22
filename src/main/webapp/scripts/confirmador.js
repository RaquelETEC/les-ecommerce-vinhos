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


function confirmarExcluirEndereco(idEnd, id){
		let resposta = confirm("Confirma a exclusão deste contato? ID:" + idEnd)
	if (resposta === true) {
		window.location.href = "deleteEndereco?idEnd=" + idEnd + "&id=" + id ; 
	}
}



function confirmarProduto(idPedido, i) {
	const Status = document.getElementById('status' + i).value;

	window.location.href = "EditarPedido?id=" + idPedido + "&PedidoStatus=" + Status; 

}
	