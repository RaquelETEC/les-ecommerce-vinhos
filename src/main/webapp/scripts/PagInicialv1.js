function incrementarQuant(productId) {
	debugger
	let quantidade = parseInt(document.getElementById(`quantity${productId}`).innerText);
	document.getElementById(`quantity${productId}`).innerText = quantidade + 1;
}

function decrementarQuant(productId) {
	debugger
	let quantidade = parseInt(document.getElementById(`quantity${productId}`).innerText);
	if (quantidade > 1) { // Limite mínimo de 1
		document.getElementById(`quantity${productId}`).innerText = quantidade - 1;
	}
}



function AdicionarAoCarrinho(id, idProd, quant) {
	debugger;
 	var id = id;
    var idProd = idProd;
    var quant = quant;

    var respostaAjax; // Variável para armazenar a resposta da requisição AJAX

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/les-ecommerce-vinhos/AdicionarAoCarrinho?id=" + id + "&idProd=" + idProd + "&quant=" + quant , true);
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
 
  // chat bot
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


function sendMessage() {

}

