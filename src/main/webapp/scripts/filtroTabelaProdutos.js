/**
 * Filtro de tabela de produtos
 */
function filtrarTabela(event) {
    event.preventDefault(); // Evita o comportamento padrão do botão de submit

    var pesquisarFiltro = document.getElementById('Pesquisar').value.toLowerCase();

    var tabela = document.getElementById('tabela');
    var linhas = tabela.getElementsByTagName('tr');

    for (var i = 1; i < linhas.length; i++) {
        var linha = linhas[i];
        var cells = linha.getElementsByTagName('td');
        var match = false;

        for (var j = 0; j < cells.length; j++) {
            var cell = cells[j];
            if (cell.innerText.toLowerCase().includes(pesquisarFiltro)) {
                match = true;
                break;
            }
        }

        if (match) {
            linha.style.display = '';
        } else {
            linha.style.display = 'none';
        }
    }
}

document.getElementById('btnBuscar').addEventListener('click', filtrarTabela);