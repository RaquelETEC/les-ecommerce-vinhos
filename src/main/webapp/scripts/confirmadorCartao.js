/**
 * Confirmar a exclusao de um cartao
 * 
 * @param id
 */

function confirmarCartao(id) {
	debugger;
	let resposta = confirm("Confirma a exclusão deste cartao? ID:" + id)
	if (resposta === true) {
		window.location.href = "deleteCartao?id=" + id
	}
}