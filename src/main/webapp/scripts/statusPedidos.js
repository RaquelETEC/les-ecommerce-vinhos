
function atualizarOpcoesStatus() {
    var statusAtual = document.getElementById("statusPedido").value;
    var opcoesStatus = document.getElementById("statusPedido").options;

    // Habilitar ou desabilitar as opções com base no status atual
    if (statusAtual === "aprovado") {
        opcoesStatus[1].disabled = false; // Em Transporte
        opcoesStatus[2].disabled = true;  // Entregue
    } else if (statusAtual === "em-transporte") {
        opcoesStatus[1].disabled = true;  // Em Transporte
        opcoesStatus[2].disabled = false; // Entregue
    }
}

 document.addEventListener("DOMContentLoaded", function () {
        // Obter o valor do parâmetro 'status' da URL
        const urlParams = new URLSearchParams(window.location.search);
        const status = urlParams.get('status');

        // Selecionar o elemento do campo de seleção
        const statusPedidoSelect = document.getElementById('statusPedido');

        // Atualizar o valor do campo de seleção com o status atual do pedido
        if (statusPedidoSelect) {
            // Remover a opção "status atual"
            statusPedidoSelect.remove(0);

            // Adicionar a opção do status atual e torná-la selecionada
            const option = document.createElement('option');
            option.value = status;
            option.text = status;
            option.selected = true;
            statusPedidoSelect.add(option);

            // Adicionar outras opções conforme necessário
	        option = document.createElement('option');
	        option.value = "Outro Status";
	        option.text = "Outro Status";
	        statusPedidoSelect.add(option);
        }

        // Fazer algo com o status (por exemplo, exibir no console)
        console.log("Status do Pedido:", status);
    });
    
 
   function adicionarHarmonizacao() {
            // Lógica para adicionar uma nova linha na tabela de harmonizações
            var tabelaHarmonizacoes = document.getElementById("tabelaHarmonizacoes");
            var novaLinha = tabelaHarmonizacoes.insertRow();

            var colunaHarmonizacao = novaLinha.insertCell(0);
            var colunaOpcoes = novaLinha.insertCell(1);

            colunaHarmonizacao.innerHTML = '<input type="text" class="form-control" name="harmonizacao[]">';
            colunaOpcoes.innerHTML = '<button class="btn btn-danger" onclick="removerLinha(this)">Remover</button>';
        }

        function removerLinha(botao) {
            // Lógica para remover a linha da tabela de harmonizações
            var linha = botao.parentNode.parentNode;
            linha.parentNode.removeChild(linha);
        }