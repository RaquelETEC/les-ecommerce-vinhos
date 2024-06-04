/**
 * Filtro de tabela de clientes
 */
  function filtrarTabela() {
        var generoFiltro = document.getElementById('genero').value;
        var nomeFiltro = document.getElementById('nome').value.toLowerCase();
        var dataNascimentoFiltro = document.getElementById('dataNascimento').value;
        var cpfFiltro = document.getElementById('cpf').value;
        var telefoneFiltro = document.getElementById('telefone').value;
        var emailFiltro = document.getElementById('email').value.toLowerCase();
        var statusFiltro = document.getElementById('status').value.toLowerCase();

        var tabela = document.getElementById('tabela');
        var linhas = tabela.getElementsByTagName('tr');

		//convertendo a data
		var dataFormatadaFiltro = '';
  	
		if (dataNascimentoFiltro) {
			var partesData = dataNascimentoFiltro.split("-");
			dataFormatadaFiltro = partesData[2] + "/" + partesData[1] + "/" + partesData[0];
		}

        for (var i = 1; i < linhas.length; i++) {
            var linha = linhas[i];
            var cells = linha.getElementsByTagName('td');

            var nomeCliente = cells[1].innerText.toLowerCase();
            var dataNascimentoCliente = cells[6].innerText;
            var cpfCliente = cells[3].innerText;
            var telefoneCliente = cells[5].innerText;
            var emailCliente = cells[2].innerText.toLowerCase();
            var statusCliente = cells[8].innerText.toLowerCase();
            var generoCliente = cells[7].innerText.toLowerCase();

            if (
                (generoFiltro === '' || generoCliente === generoFiltro.toLowerCase()) &&
                (nomeFiltro === '' || nomeCliente.includes(nomeFiltro)) &&
                (dataFormatadaFiltro === '' || dataNascimentoCliente === dataFormatadaFiltro) &&
                (cpfFiltro === '' || cpfCliente.includes(cpfFiltro)) &&
                (telefoneFiltro === '' || telefoneCliente.includes(telefoneFiltro)) &&
                (emailFiltro === '' || emailCliente.includes(emailFiltro)) &&
                (statusFiltro === '' || statusCliente.includes(statusFiltro))
            ) {
                linha.style.display = '';
            } else {
                linha.style.display = 'none';
            }
        }
    }

    document.getElementById('btnBuscar').addEventListener('click', filtrarTabela);