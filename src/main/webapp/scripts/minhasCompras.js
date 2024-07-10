

window.onload = function() {
    filterStatus('Tudo'); // Exibir todos os pedidos inicialmente
};

function filterStatus(status) {
    const pedidos = document.querySelectorAll('.custom-container');

    pedidos.forEach(pedido => {
        const pedidoStatus = pedido.className.split("=")[1];
        
        if (!(status === 'Tudo' || pedidoStatus.includes(status)) && pedidoStatus !== 'tabs' ) {
			pedido.style.display = 'none'; // Ocultar o pedido se não corresponder ao status
        }
		else {
			pedido.style.display = 'block'; // Exibir o pedido
		}
		
    });
}

function cancelarPedido(idPedido) {
    const novoStatus = "EM CANCELAMENTO"; 
    const confirmacao = confirm("Tem certeza de que deseja cancelar este pedido?");
    if (confirmacao) {
        
     const url = `/les-ecommerce-vinhos/areaAdministrador/EditarPedido?id=${idPedido}&PedidoStatus=${novoStatus}`;
 
    fazerRequisicaoAjax(url, function(resposta) {
		if(resposta > 0){
        	alert("Erro ao solicitar cancelamento");
        }
        else
        	alert("Cancelamento solicitado" + resposta)
    }, function() {
        alert("Erro ao gerenciar troca JS");
    });
        
    }
}

 //da para transformar em uma so função
 function enviarItems(idItem ="",idTroca = "", quantItem="",quantTocada="", pedidoId="",novoStatus="",novoStatusItem=""){
	
 // Extrair os IDs e quantidades dos itens de troca
  const itensSelecionados = [idItem];
  const quantidades = [quantItem];
  const quantidadesTrocadas = [quantTocada]; 

  // Construir a URL com os IDs e quantidades dos itens
  const url = `/les-ecommerce-vinhos/areaCliente/solicitarTroca.html?idTroca=${idTroca}&pedido=${pedidoId}&novoStatusPedido=${novoStatus}&tipoSolicitacao=${novoStatusItem}&itens=${itensSelecionados.join(',')}&quantidades=${quantidades.join(',')}&quantidadesTrocadas=${quantidadesTrocadas.join(',')}`; // Adicionado

  fazerRequisicaoAjax(url, function(resposta) {
    if (resposta.includes("error")) {
      alert("Erro ao enviar items para Troca" + resposta);
    } else {
      alert("Item enviado ");
      window.location.href = window.location;
    }
  }, function() {
    alert("Erro ao enviar items para Troca");
  });
}
 
 
function handleChecketAll(event, pedidoId) {
    if (event.target.checked) {
        // Selecionar todos os checkboxes associados ao pedido
        var checkboxes = document.querySelectorAll('.item-troca[data-pedido="' + pedidoId + '"]');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = true;
        });
    } else {
        // Desmarcar todos os checkboxes associados ao pedido
        var checkboxes = document.querySelectorAll('.item-troca[data-pedido="' + pedidoId + '"]');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = false;
        });
    }
}
function fazerRequisicaoAjax(url, sucessoCallback, erroCallback) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				sucessoCallback(xhr.responseText);
			} else {
				erroCallback();
			}
		}
	};
	xhr.send();
}

function abrirModalTroca(pedidoId) {
  // Limpar formulário
  document.getElementById('trocaForm').innerHTML = '';

  // Log de depuração
  console.log('Pedidos:', pedidos);

  // Pegar os itens do pedido usando o pedidoId
  let pedido = pedidos.find(p => p.id === pedidoId);

  // Log de depuração
  console.log('Pedido encontrado:', pedido);

  // Verificar se o pedido e os itens do pedido estão definidos
  if (pedido && Array.isArray(pedido.PedidoItens)) {
    // Preencher o formulário com os itens do pedido
    for (let i = 0; i < pedido.PedidoItens.length; i++) {
      let item = pedido.PedidoItens[i];
      let itemHtml = `
         <div class="row mt-3">
          <div class="col-md-2">
            <div class="image-container">
              <img
                src="../imagens/produtos/${item.produto.codigo_barra}.png"
                class="card-img-top img-fluid rounded-start"
                alt="Imagem do Produto">
            </div>
          </div>
          <div class="col-md-9 col-sm-12">
            <div class="card-body">
              <h6 class="card-title">
                ${item.descricao}
              </h6>
              <div class="card-options">
                <div class="card-text row">
                  <div class="col-12">
                    <p>
                      Disponivel para troca: ${item.quantidade - item.quantidadeTrocada} 
                    </p>
                  </div> 
                </div>
              </div>
              <div class="mb-3">
               	<div class="col-9">
                	<input type="number" class="form-control" name="quantidade-${item.id}" placeholder="Quantidade para troca" min="1" max="${item.quantidade}">
                </div>
                <div class="col-3" style="display: none;">
			        <input type="number" class="form-control" name="quant-trocada${item.id}" value="${item.quantidadeTrocada}">
			    	<input type="number" class="form-control" name="quant-item${item.id}" value="${item.quantidade}">

			    </div>
              </div>
            </div>
          </div>
        </div>
      `;
      document.getElementById('trocaForm').insertAdjacentHTML('beforeend', itemHtml);
    }
    
    document.getElementById('enviarTrocaBtn').setAttribute('data-pedido-id', pedidoId);

    // Abrir modal
    var trocaModal = new bootstrap.Modal(document.getElementById('trocaModal'));
    trocaModal.show();
  } else {
    console.error('Pedido ou itens do pedido não encontrados ou inválidos');
    alert('Erro ao abrir modal de troca. Por favor, tente novamente.');
  }
}
function enviarTroca() {
  // Pegar o ID do pedido do atributo data
  let pedidoId = document.getElementById('enviarTrocaBtn').getAttribute('data-pedido-id');

  // Pegar os dados do formulário
  let formData = new FormData(document.getElementById('trocaForm'));
  let trocaItems = [];

  formData.forEach((value, key) => {
    if (key.startsWith('quantidade-') && value > 0) {
      	let itemId = key.split('-')[1];
		let quantidadeTrocadaElement = document.querySelector(`input[name="quant-trocada${itemId}"]`);
		let quantidadeTrocada = quantidadeTrocadaElement ? quantidadeTrocadaElement.value : 0;      // Pegar a quantidade trocada
		let quantProd = document.querySelector(`input[name="quant-item${itemId}"]`) ? document.querySelector(`input[name="quant-item${itemId}"]`).value : 0;

      trocaItems.push({ id: itemId, quantidade: value, quantidadeTrocada: quantidadeTrocada , quantidadeProduto: quantProd});
    }
  });

  if (trocaItems.length === 0) {
    alert('Selecione pelo menos um item e quantidade para troca.');
    return;
  }

  // Verificar se a quantidade de troca é maior do que a disponível
  for (let i = 0; i < trocaItems.length; i++) {
    let item = trocaItems[i];
    let quantidadeDisponivel = item.quantidadeProduto - item.quantidadeTrocada;
    if (item.quantidade > quantidadeDisponivel) {
      alert(`A quantidade selecionada é maior do que a quantidade disponível para troca.`);
      return;
    }
  }

  solicitarTroca(pedidoId, 'EM TROCA', trocaItems);
}

function solicitarTroca(pedidoId, novoStatusPedido, trocaItems) {
  // Extrair os IDs e quantidades dos itens de troca
  const itensSelecionados = trocaItems.map(item => item.id);
  const quantidades = trocaItems.map(item => item.quantidade);
  const quantidadesTrocadas = trocaItems.map(item => item.quantidadeTrocada); // Adicionado

  // Construir a URL com os IDs e quantidades dos itens
  const url = `/les-ecommerce-vinhos/areaCliente/solicitarTroca.html?idTroca=0&pedido=${pedidoId}&novoStatusPedido=${encodeURIComponent(novoStatusPedido)}&tipoSolicitacao=TROCA_SOLICITADA&itens=${itensSelecionados.join(',')}&quantidades=${quantidades.join(',')}&quantidadesTrocadas=${quantidadesTrocadas.join(',')}`; // Adicionado

  fazerRequisicaoAjax(url, function(resposta) {
    if (resposta.includes("error")) {
      alert("Erro ao solicitar Troca");
    } else {
      alert("Troca dos itens: " + itensSelecionados.join(',') + " solicitada");
      window.location.href = window.location;
    }
  }, function() {
    alert("Erro ao gerenciar troca JS");
  });
}

