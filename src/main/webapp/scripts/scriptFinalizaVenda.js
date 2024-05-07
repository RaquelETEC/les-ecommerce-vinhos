// Obter a URL atual
var url = new URL(window.location.href);
// Obter o valor do parâmetro 'idPedido' da URL
var idPedido = url.searchParams.get("idPedido");

document.getElementById('idTextPedido').textContent = `COMPRA ${idPedido} REALIZADA COM SUCESSO !`


function acessoMinhasCompras(){
	window.location.href = "/les-ecommerce-vinhos/areaCliente/MinhasCompras.html?id=" + idPedido;
}