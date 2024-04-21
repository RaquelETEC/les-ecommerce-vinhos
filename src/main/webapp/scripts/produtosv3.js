function decrementarQuantidade(idItem, i) {
    const btnDecrementar = document.getElementById('btn-decrementar-' + i);
    const btnIncrementar = document.getElementById('btn-incrementar-' + i);
    const quantidade = parseInt(document.getElementById(`quantity${i}`).innerText);
	const quantidadeAtualizar = quantidade -1;
    // Desabilita o botão enquanto a requisição AJAX está em andamento
    btnIncrementar.disabled = true;
    btnDecrementar.disabled = true;

    var url = "/les-ecommerce-vinhos/AlterarQuantCarrinho?idItem=" + idItem + "&quant="+quantidadeAtualizar;
    fazerRequisicaoAjax(url, function(resposta) {
        alert(resposta);
        // Habilita o botão após receber a resposta
        document.getElementById(`quantity${i}`).innerText = quantidade - 1;
        btnDecrementar.disabled = false;
        btnIncrementar.disabled = false;
    }, function() {
        alert("Erro ao decrementar quantidade");
        // Habilita o botão em caso de erro também
        btnDecrementar.disabled = false;
        btnIncrementar.disabled = false;
    });
}

function incrementarQuantidade(idItem, i) {
    const btnDecrementar = document.getElementById('btn-decrementar-' + i);
    const btnIncrementar = document.getElementById('btn-incrementar-' + i);
    const quantidade = parseInt(document.getElementById(`quantity${i}`).innerText);
	const quantidadeAtualizar = quantidade +1;

    // Desabilita o botão enquanto a requisição AJAX está em andamento
    btnIncrementar.disabled = true;
    btnDecrementar.disabled = true;

    var url = "/les-ecommerce-vinhos/AlterarQuantCarrinho?idItem=" + idItem + "&quant="+quantidadeAtualizar;
    fazerRequisicaoAjax(url, function(resposta) {
		alert(resposta);
		if(resposta = 'Quantidade atualizada no carrinho com sucesso!'){
	        // Habilita o botão após receber a resposta
	        document.getElementById(`quantity${i}`).innerText = quantidade + 1;
	        btnDecrementar.disabled = false;
	        btnIncrementar.disabled = false;
	       }
    }, function() {
        alert("Erro ao incrementar quantidade");
        // Habilita o botão em caso de erro também
        btnDecrementar.disabled = false;
        btnIncrementar.disabled = false;
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

function alterarQuantidade(produtoId, carrinhoId, quantidade){
    var url = "/les-ecommerce-vinhos/AlterarQuantCarrinho?id=" + carrinhoId + "&idProd=" + produtoId + "&quant=" + quantidade;
    fazerRequisicaoAjax(url, function(resposta) {
        alert(resposta);
    }, function() {
        alert("Erro ao realizar a requisição.");
    });
}

function removerProduto(carrinhoId, produtoId) {
	debugger
    var url = "/les-ecommerce-vinhos/RemoverProdutoCarrinho?carrinhoId=" + carrinhoId + "&produtoId=" + produtoId;
    fazerRequisicaoAjax(url, function(resposta) {
        alert(resposta);
        // Após remover o produto com sucesso, redirecione para a URL atual para recarregar os produtos
        window.location.href = window.location.href;
    }, function() {
        alert("Erro ao remover produto do carrinho");
    });
}
