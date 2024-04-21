function incrementarQuantidade(produtoId, carrinhoId) {
    debugger

   if (quantidade < 999) { // Limite máximo arbitrário de 999
       document.getElementById(`quantity${productId}`).innerText = quantidade + 1;
       atualizarQuantidade(productId);
   }
}

function decrementarQuantidade(produtoId, carrinhoId) {
   let quantidade = parseInt(document.getElementById(`quantity${productId}`).innerText);
   if (quantidade > 1) { // Limite mínimo de 1
       document.getElementById(`quantity${productId}`).innerText = quantidade - 1;
       atualizarQuantidade(productId);
   }
}



function send(produtoId, carrinhoId, quantidade){
    var respostaAjax; // Variável para armazenar a resposta da requisição AJAX

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/les-ecommerce-vinhos/AlterarQuantCarrinho?id=" + carrinhoId + "&idProd=" + produtoId + "&quant=" + quantidade );
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
				debugger;
                respostaAjax = xhr.responseText; // Armazena a resposta da requisição AJAX na variável respostaAjax
                alert(respostaAjax)
            } else {
                alert("Erro ao realizar a requisição.");
            }
        }
    };
    xhr.send();

    // A partir daqui, você pode usar a variável respostaAjax conforme necessário

}