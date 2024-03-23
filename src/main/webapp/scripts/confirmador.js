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
	debugger;
	let resposta = confirm("Confirma a exclusão deste contato? ID:" + id)
	if (resposta === true) {
		window.location.href = "deleteClient?id=" + id
	}
}

