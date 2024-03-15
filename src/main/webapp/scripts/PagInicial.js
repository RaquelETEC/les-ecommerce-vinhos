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
//Neste exemplo, os dados do item são codificados como uma string de consulta (x-www-form-urlencoded)
// e enviados no corpo da solicitação POST. Isso permitirá que você os acesse individualmente na sua servlet 
//usando request.getParameter("produto"), request.getParameter("preco"), e assim por diante.
//fetch é uma alternativa moderna ao XMLHttpRequest. Ele simplifica o código e é mais poderoso.
function adicionarAoCarrinho(produto, preco, quantidade) {
 const url = `AdicionarAoCarrinho?produto=${encodeURIComponent(produto)}&preco=${encodeURIComponent(preco)}&quantidade=${encodeURIComponent(quantidade)}`;

    fetch(url, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        if (data.url) {
            window.location.href = data.url; // Redireciona para a URL do carrinho
        } else {
            alert('Erro ao adicionar produto ao carrinho.');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}


  document.addEventListener("DOMContentLoaded", function() {
            const chatbotContainer = document.getElementById("chatbot-container");
            const toggleChatbotButton = document.getElementById("toggle-chatbot");
            const chatbotContent = document.getElementById("chatbot-content");

            toggleChatbotButton.addEventListener("click", function() {
                chatbotContainer.classList.toggle("expanded");
                if (chatbotContainer.classList.contains("expanded")) {
                    chatbotContent.style.display = "block";
                } else {
                    chatbotContent.style.display = "none";
                }
            });
        });