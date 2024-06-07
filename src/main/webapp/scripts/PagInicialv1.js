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
 
// script.js
document.addEventListener("DOMContentLoaded", function() {
	const chatbotContainer = document.getElementById("chatbot-container");
	const toggleChatbotButton = document.getElementById("toggle-chatbot");
	const chatbotContent = document.getElementById("chatbot-content");
	const assistButton = document.getElementById("assist-button");
	const textAssistent = document.getElementById("text-assistent");

	const infoForm = document.getElementById("info-form");

	toggleChatbotButton.addEventListener("click", function() {
		chatbotContainer.classList.toggle("expanded");
		if (chatbotContainer.classList.contains("expanded")) {
			chatbotContent.style.display = "block";
			toggleChatbotButton.textContent = "Chat-bot";
		} else {
			chatbotContent.style.display = "none";
		}
	});

	assistButton.addEventListener("click", function() {
		assistButton.style.display = "none";
		textAssistent.innerText  = " Vou te ajudar na escolha do vinho ideal! Por favor preencha as informações abaixo:"
		infoForm.style.display = "block";
	});
});

document.addEventListener("DOMContentLoaded", function() {
    const grapeVarietySelect = document.getElementById("grape-variety");
    const otherGrapeVarietyInput = document.getElementById("other-grape-variety");

    grapeVarietySelect.addEventListener("change", function() {
        if (grapeVarietySelect.value === "outra") {	
            otherGrapeVarietyInput.style.display = "block";
        } else {
            otherGrapeVarietyInput.style.display = "none";
        }
    });
});


//enviar chat
document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("info-form");

    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        // Captura dos valores dos campos do formulário
        const wineType = document.getElementById("wine-type").value;
        const grapeVariety = document.getElementById("grape-variety").value;
        const region = document.getElementById("region").value;
        const budget = document.getElementById("budget").value;
        const sweetnessLevel = document.getElementById("sweetness-level").value;
        const occasion = document.getElementById("occasion").value;
        const foodPairing = document.getElementById("food-pairing").value;
        const experienceLevel = document.getElementById("experience-level").value;
        const bodyPreference = document.getElementById("body-preference").value;
        const flavorNotes = document.getElementById("flavor-notes").value;

        // Formatação dos valores em um texto estruturado
        const text = `Tipo de vinho preferido: ${wineType}, Variedade de uva preferida: ${grapeVariety}, Região preferida: ${region}, Faixa de preço: ${budget}, Nível de doçura desejado: ${sweetnessLevel}, Ocasião: ${occasion}, Combinação com alimentos: ${foodPairing}, Nível de experiência: ${experienceLevel}, Preferência por corpo: ${bodyPreference}, Notas de sabor preferidas: ${flavorNotes}`;

        // Fazer a requisição HTTP GET para /ProdutosDisponiveis
        fetch("http://localhost:8080/produtosDisponiveis.html")
            .then(response => response.json()) // Extrair os dados JSON da resposta
            .then(produtos => {
                // Manipular os dados dos produtos como desejado
                console.log(produtos);

                // Exemplo de como você pode utilizar os dados retornados
                for (const produto of produtos) {
                    console.log(`ID do produto: ${produto.id}, Descrição do produto: ${produto.descricao}`);
                }
            })
            .catch(error => console.error("Erro ao obter produtos:", error));

        // Limpar os campos do formulário após o envio (opcional)
        form.reset();
    });
});
