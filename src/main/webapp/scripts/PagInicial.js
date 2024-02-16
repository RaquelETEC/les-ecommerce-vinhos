 function incrementarQuantidade(productId) {
	 debugger
	 let quantidade = parseInt(document.getElementById(`quantity${productId}`).innerText);

    if (quantidade < 999) { // Limite máximo arbitrário de 999
        document.getElementById(`quantity${productId}`).innerText = quantidade + 1;
        atualizarQuantidade(productId);
    }
}

 function decrementarQuantidade(productId) {
	let quantidade = parseInt(document.getElementById(`quantity${productId}`).innerText);
    if (quantidade > 1) { // Limite mínimo de 1
        document.getElementById(`quantity${productId}`).innerText = quantidade - 1;
        atualizarQuantidade(productId);
    }
}

function atualizarQuantidade(productId) {
	debugger;
	document.getElementById(`quantity${productId}`).innerText
}
