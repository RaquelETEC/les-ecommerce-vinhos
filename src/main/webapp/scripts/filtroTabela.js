/**
 * Filtro de tabela de clientes
 */
  function filtrarTabela() {
	  debugger;
        var genero = document.getElementById('genero').value;
        var nome = document.getElementById('nome').value.toLowerCase();
        var dataNascimento = document.getElementById('dataNascimento').value;
        var cpf = document.getElementById('cpf').value;
        var telefone = document.getElementById('telefone').value;
        var email = document.getElementById('email').value.toLowerCase();
        var endereco = document.getElementById('endereco').value.toLowerCase();

        var tabela = document.getElementById('tabela');
        var linhas = tabela.getElementsByTagName('tr');

        for (var i = 1; i < linhas.length; i++) {
            var linha = linhas[i];
            var cells = linha.getElementsByTagName('td');

            var id = cells[0].innerText;
            var nomeCliente = cells[1].innerText.toLowerCase();
            var dataNascimentoCliente = cells[6].innerText;
            var cpfCliente = cells[3].innerText;
            var telefoneCliente = cells[5].innerText;
            var emailCliente = cells[2].innerText.toLowerCase();
            var enderecoCliente = cells[8].innerText.toLowerCase();
            var generoCliente = cells[7].innerText.toLowerCase();

            if (
                (genero === '' || generoCliente === genero.toLowerCase()) &&
                (nome === '' || nomeCliente.includes(nome)) &&
                (dataNascimento === '' || dataNascimentoCliente === dataNascimento) &&
                (cpf === '' || cpfCliente.includes(cpf)) &&
                (telefone === '' || telefoneCliente.includes(telefone)) &&
                (email === '' || emailCliente.includes(email)) &&
                (endereco === '' || enderecoCliente.includes(endereco))
            ) {
                linha.style.display = '';
            } else {
                linha.style.display = 'none';
            }
        }
    }

    document.getElementById('btnBuscar').addEventListener('click', filtrarTabela);