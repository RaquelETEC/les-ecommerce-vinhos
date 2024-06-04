/**
 * Confirmar a exclusao de um cartao
 * 
 * @param idCartao
 * @param id
 * 
 */

function confirmarCartao(idCartao,id) {
	debugger
	let resposta = confirm("Confirma a exclusão deste cartao? ID:" + idCartao,id)
	if (resposta === true) {
		window.location.href = "deleteCartao?idCartao=" + idCartao + "&id="+id
	}
}