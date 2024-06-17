/**
 * Função para filtrar a tabela de clientes
 */
function filtrarTabela() {
    var clienteFiltro = document.getElementById('cliente').value.toLowerCase();
    var pedidoIdFiltro = document.getElementById('ID').value.toLowerCase();
    var dataFiltro = document.getElementById('dataPedido').value;
    var valorFiltro = document.getElementById('valor').value.toLowerCase();

    var dataFormatadaFiltro = '';
    if (dataFiltro) {
        var partesData = dataFiltro.split("-");
        dataFormatadaFiltro = partesData[2] + "/" + partesData[1] + "/" + partesData[0];
    }

    var tabela = document.getElementById('tabela');
    var linhas = tabela.getElementsByTagName('tr');
	
    for (var i = 1; i < linhas.length; i++) {
        var linha = linhas[i];
        var cells = linha.getElementsByTagName('td');

        var nomeCliente = cells[1].innerText.toLowerCase();
        var dataPedido = cells[2].innerText;
        var pedidoId = cells[0].innerText.toLowerCase();
        var valor = cells[6].innerText.toLowerCase();

        var clienteMatch = nomeCliente.includes(clienteFiltro);
        var pedidoIdMatch = pedidoId.includes(pedidoIdFiltro);
        var dataMatch = (dataFiltro === '' || dataPedido === dataFormatadaFiltro);
        var valorMatch = valor.includes(valorFiltro);
		
        if (clienteMatch && pedidoIdMatch && dataMatch && valorMatch) {
            linha.style.display = '';
        } else {
            linha.style.display = 'none';
        }
    }

}

document.getElementById('btnBuscar').addEventListener('click', filtrarTabela);
