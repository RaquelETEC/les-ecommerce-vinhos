document.addEventListener("DOMContentLoaded", function() {
    var totalElement = document.getElementById('totalCarriho');
        var finalizarCompraBtn = document.getElementById('btn-finalizar-compra'); 
    var total = parseFloat(totalElement.innerText.replace('R$', '').trim()); // Obtém o valor total e converte para número

    if (total === 0) {
        finalizarCompraBtn.disabled = true; // Desativa o botão se o total for zero
    }
});

function decrementarQuantidade(idItem, i) {
    const btnDecrementar = document.getElementById('btn-decrementar-' + i);
    const btnIncrementar = document.getElementById('btn-incrementar-' + i);
    const quantidadeSpan = document.getElementById(`quantity${i}`).innerText.replace("x ", "");

    const quantidade = parseInt(quantidadeSpan);
    const quantidadeAtualizar = quantidade -1;
    // Desabilita o botão enquanto a requisição AJAX está em andamento
    btnIncrementar.disabled = true;
    btnIncrementar.classList.add('btn-disabled'); // Adiciona a classe CSS
    btnDecrementar.disabled = true;
    btnDecrementar.classList.add('btn-disabled');

 	setTimeout(function() {
	    var url = "/les-ecommerce-vinhos/AlterarQuantCarrinho?idItem=" + idItem + "&quant="+quantidadeAtualizar;
	    fazerRequisicaoAjax(url, function(resposta) {
	       window.location.href = window.location.href;
	    }, function() {
	        alert("Erro ao decrementar quantidade");
	        // Habilita o botão em caso de erro também
	        btnDecrementar.disabled = false;
	        btnIncrementar.disabled = false;
	    });
    }, 100); // 2 segundos em milissegundos
}

function incrementarQuantidade(idItem, i) {
    const btnDecrementar = document.getElementById('btn-decrementar-' + i);
    const btnIncrementar = document.getElementById('btn-incrementar-' + i);
    const quantidadeSpan = document.getElementById(`quantity${i}`).innerText.replace("x ", "");

    const quantidade = parseInt(quantidadeSpan);
	const quantidadeAtualizar = quantidade +1;

    // Desabilita o botão enquanto a requisição AJAX está em andamento
    btnIncrementar.disabled = true;
    btnIncrementar.classList.add('btn-disabled'); // Adiciona a classe CSS
    btnDecrementar.disabled = true;
    btnDecrementar.classList.add('btn-disabled'); // Adiciona a classe CSS

 	setTimeout(function() {
	    var url = "/les-ecommerce-vinhos/AlterarQuantCarrinho?idItem=" + idItem + "&quant="+quantidadeAtualizar;
	    fazerRequisicaoAjax(url, function(resposta) {
		        // Habilita o botão após receber a resposta
		       window.location.href = window.location.href;
	
	    }, function() {
	        alert("Erro ao incrementar quantidade");
	        // Habilita o botão em caso de erro também
	        btnDecrementar.disabled = false;
	        btnIncrementar.disabled = false;
	    });
	 }, 100); // 2 segundos em milissegundos

}

  function redirecionarParaFinalizarCompra(idCliente) {
        var url = "/les-ecommerce-vinhos/FinalizarCompra?idCliente=" + idCliente;
        window.location.href = url;
    }

function alterarStatusItem(carrinhoId, produtoId, quantidade, motivo , idstatuts, itemCarrinhoId) {
	debugger
    var url = "/les-ecommerce-vinhos/AlterarStatusItemCarriho?"
    + "carrinhoId=" + carrinhoId 
    + "&produtoId=" + produtoId 
    + "&quantidade="+ quantidade
    + "&motivo=" + motivo
    + "&idStatus="+idstatuts
    + "&itemCarrinhoId="+itemCarrinhoId
    
    fazerRequisicaoAjax(url, function(resposta) {
        alert(resposta);
        // Após remover o produto com sucesso, redirecione para a URL atual para recarregar os produtos
        window.location.href = window.location.href;
    }, function() {
        alert("Erro ao remover produto do carrinho");
    });
}


function finalizarCompra(idCliente){
	debugger;
    var url = "/les-ecommerce-vinhos/FinalizarCompra?idCliente=" + idCliente;
    fazerRequisicaoAjax(url, function(resposta) {
        alert(resposta);
        // Após remover o produto com sucesso, redirecione para a URL atual para recarregar os produtos
        window.location.href = window.location.href;
    }, function() {
        alert("Erro ao Finalziar Compra");
    });
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
