/**
 * Configurações da tela principal
 * 
 * @author Raquel Gonçalves
 */

var baseUrl = window.location.origin; 

function incrementarQuant(productId) {
	let quantidade = parseInt(document.getElementById(`quantity${productId}`).innerText);
	document.getElementById(`quantity${productId}`).innerText = quantidade + 1;
}

function decrementarQuant(productId) {
	let quantidade = parseInt(document.getElementById(`quantity${productId}`).innerText);
	if (quantidade > 1) { // Limite mínimo de 1
		document.getElementById(`quantity${productId}`).innerText = quantidade - 1;
	}
}

function AdicionarAoCarrinho(id, idProd, quant) {
 	var id = id;
    var idProd = idProd;
    var quant = quant;

    var respostaAjax; 

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/les-ecommerce-vinhos/AdicionarAoCarrinho?id=" + id + "&idProd=" + idProd + "&quant=" + quant , true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
				
                respostaAjax = xhr.responseText; 
                alert(respostaAjax)
            } else {
                alert("Erro ao realizar a requisição.");
            }
        }
    };
    xhr.send();
}

//Chat-bot 
document.addEventListener("DOMContentLoaded", async function() {
	const toggleChatbotButton = document.getElementById("toggle-chatbot");
    const chatbotContent = document.getElementById("chatbot-content");
    const chatbotContainer = document.getElementById("chatbot-container");
    const chatResponseContainer = document.getElementById("chat-response-container");
    const userInput = document.getElementById("user-input");
    const sendMessageButton = document.getElementById("send-message");

    const notificationSound = new Audio('imagens/assets/new-notification-7-210334.mp3'); 
	
	let conversaIniciada = false; 
	let listaProdutos = '';
    let chatStep = 0;
    let userResponses = {};
    let messageHistory = []; // Array para armazenar o histórico de mensagens

	if(listaProdutos.length == 0){
	listaProdutos = await ProdutosDisponiveis();
	}

	function iniciarConversa() {
		if (!conversaIniciada) {
			conversaIniciada = true;
	        const mensagemInicial = `
	        INSTRUÇÕES PARA RESPOSTA: 
	        Voce precisará coletar informações sobre a preferência de vinhos/espumantes do usuario para 
	        recomendar um produto da lista de produtos. 
	        NÃO RESPONDA NEM FAÇA PERGUNTAS QUE NÃO ESTÃO RELACIONADAS A VINHOS.
	        QUANDO VOCÊ POSSUIR INFORMAÇÕES SUFICIENTES SOBRE A PREFERENCIA DO USUARIO PARA UM VINHO OU ESPUMANTE PARA RECOMENDAR O MELHOR PRODUTO POSSIVEL, FORNEÇA A RECEOMENDAÇÃO DE 1 VINHO DA LISTA DE VINHOS NO SEGUINTE FORMATO:
	        ID= <ID do produto>
	        MOTIVO= <Motivo da recomendação>
	        FAÇA NO MÍNIMO 3 PERGUNTAS SOBRE AS PREFERENCIAS DO USUARIO PARA VINHOS OU ESPUMANTES
	        FAÇA APENAS UMA PERGUNTA POR VEZ AO USUARIO
	        SE O USUÁRIO NÃO RESPONDER SUA PERGUNTA, ENTENTA A NECESSIDADE DELE, ATE CONSEGUIR RECOMENDAR UM VINHO
	        NÃO RECOMENDE O MESMO VINHO MAIS DE 1 VEZ PARA O MESMO USUARIO.
	        Esta é a lista de produtos: ${listaProdutos}
	        PARA ESTA PRIMEIRA PERGUNTA RESPONDA APENAS: Olá, sou Roberto 🤖, seu assistente virtual! Posso te ajudar a escolher o vinho/espumante ideal?
			`
	        enviarMensagemParaIa(mensagemInicial);
        }
    }
    
    toggleChatbotButton.addEventListener("click", function() {
        chatbotContainer.classList.toggle("expanded");
        if (chatbotContainer.classList.contains("expanded")) {
            chatbotContent.style.display = "block";
            iniciarConversa();
        } else {
            chatbotContent.style.display = "none";
        }
    });

    sendMessageButton.addEventListener("click", function() {
        const response = userInput.value.trim();
        if (response !== "") {
            capturarResposta(response);
            userInput.value = "";
        }
    });

    userInput.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            const response = userInput.value.trim();
            if (response !== "") {
                capturarResposta(response);
                userInput.value = "";
            }
        }
    });

    function playNotificationSound() {
        notificationSound.play();
    }
    
    function scrollToBottom() {
        chatbotContent.scrollTop = chatbotContent.scrollHeight;
    }
    
   function CreatedisplayAwaittMessage() {
	    const loadingMessage = document.createElement("div");
	    loadingMessage.className = "assistant-message bubble ia-bubble";
	    loadingMessage.id = 'loading-message';
	    loadingMessage.innerText = 'Carregando...';
	    chatResponseContainer.appendChild(loadingMessage);
	    scrollToBottom();
    }
   function DeletedisplayAwaittMessage() {
	   const loadingMessage = document.getElementById("loading-message");
	    if (loadingMessage) {
	        loadingMessage.remove();
	    }
    }
    
    function displayAssistantMessage(message) {
		DeletedisplayAwaittMessage(); 
        const messageElement = document.createElement("div");
        messageElement.className = "assistant-message bubble ia-bubble";
        messageElement.innerText = message;
        chatResponseContainer.appendChild(messageElement);
        playNotificationSound();
        scrollToBottom();
        sendMessageButton.disabled = false;
    }

	function displayUserMessage(message) {
	    const messageContainer = document.createElement("div");
	    messageContainer.className = "user-message";
	
	    const messageElement = document.createElement("div");
	    messageElement.className = "bubble user-bubble";
	    messageElement.innerText = message;
	
	    messageContainer.appendChild(messageElement);
	    chatResponseContainer.appendChild(messageContainer);
	    scrollToBottom();
	}

    async function enviarMensagemParaIa(message) {
		sendMessageButton.disabled = true;
  		CreatedisplayAwaittMessage();
  		
  		messageHistory.push({ role: "user", content: message });

        const response = await fetch("https://api.openai.com/v1/chat/completions", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + OPENAI_API_KEY
            },
            body: JSON.stringify({
                model: "gpt-3.5-turbo",
                messages: messageHistory,
                max_tokens: 150,
                temperature: 0.7
            })
        });

        const dados = await response.json();
        const respostaIa = dados.choices[0].message.content;
        
		processarResposta(respostaIa)
       
        
        return respostaIa;
    }

    async function capturarResposta(response) {
        displayUserMessage(response);
        const respostaIa = await enviarMensagemParaIa(response);
    }

    function processarResposta(respostaIa) {
        if (respostaIa.includes("ID=") && respostaIa.includes("MOTIVO=")) {
            exibirRecomendacao(respostaIa);
        } else {
            
	        displayAssistantMessage(respostaIa);
	        messageHistory.push({ role: "assistant", content: respostaIa }); 
	        
        }
    }

    async function ProdutosDisponiveis() {
		const sendMessageButton = document.getElementById("send-message");
		sendMessageButton.disabled = true;

        let textProdutosDisponiveis = "";
        const baseUrl = window.location.origin;  // Obtém a URL base atual
   		const response = await fetch(`${baseUrl}/les-ecommerce-vinhos/produtosDisponiveis.html`);  // Constrói a URL completa
        const produtos = await response.json();
        produtos.forEach(produto => {
            textProdutosDisponiveis += `ID=${produto.id}, DESC=${produto.desc}, PRECO=${produto.pro_preco_venda}; `;
        });
        sendMessageButton.disabled = false;

        return textProdutosDisponiveis;
    }

    function exibirRecomendacao(respostaIa) {
		const linhas = respostaIa.split('\n');
		
        // Encontra a linha com "ID="
	    const idLinha = linhas.find(line => line.startsWith("ID="));
	    const id = idLinha ? idLinha.split('=')[1].trim() : null;
	
	    // Encontra a linha com "MOTIVO="
	    const motivoLinha = linhas.find(line => line.startsWith("MOTIVO="));
	    const motivo = motivoLinha ? motivoLinha.split('=')[1].trim() : "Motivo não especificado";
	
	    // Todas as linhas antes da linha "ID=" serão consideradas como parte da introdução
	    const iaReposta = linhas.slice(0, linhas.indexOf(idLinha)).join('\n').trim();
	
	    if (!id) {
	        console.error('ID do produto não encontrado na resposta da IA.');
	        return;
	    }

        fetch(`/les-ecommerce-vinhos/buscaProduto.html?id=${id}`)
            .then(response => response.json())
            .then(produto => {
				DeletedisplayAwaittMessage(); 
		        const messageElement = document.createElement("div");
		        messageElement.className = "assistant-message bubble ia-bubble";

                const card = document.createElement('div');
                card.className = "card-recomendacao";
                
                const txtIaRecomendacao = document.createElement('p');
				txtIaRecomendacao.innerHTML = iaReposta;
				

                const imageContainer = document.createElement('div');
                imageContainer.className = 'image-container';
                imageContainer.style.backgroundColor = '#f2f2f2';

                const img = document.createElement('img');
                img.src = `imagens/produtos/${produto.codigo_barra}.png`;
                img.alt = `Imagem do Produto ${produto.desc}`;
                img.className = 'card-img-top img-fluid rounded-start';
                img.style.width = '40%';
                img.style.display = 'block';
                img.style.margin = '0 auto';
                imageContainer.appendChild(img);

                const cardBody = document.createElement('div');

                const cardTitle = document.createElement('h6');
                cardTitle.className = 'text-center';
                cardTitle.style.height = '2vh';
                cardTitle.innerText = produto.desc;
                cardBody.appendChild(cardTitle);

                const price = document.createElement('p');
                price.className = 'price';
                price.innerHTML = `<span id="price">R$ ${produto.pro_preco_venda}</span>`;
                cardBody.appendChild(price);

				const addToCartButton = document.createElement('button');
		        addToCartButton.type = 'button';
		        addToCartButton.className = 'btn btn-lg add-to-cart-btn text-center add_carrinho';
		        addToCartButton.onclick = () => AdicionarAoCarrinho('20', id, '1');
		        addToCartButton.innerText = 'Adicionar ao carrinho';
		        cardBody.appendChild(addToCartButton);

                const recommendation = document.createElement('p');
                recommendation.className = 'motivo';
                recommendation.innerHTML = `<span>${motivo}</span>`;
                cardBody.appendChild(recommendation);

				card.appendChild(txtIaRecomendacao);
                card.appendChild(imageContainer);
                card.appendChild(cardBody);

                //chatResponseContainer.appendChild(card);
                
                messageElement.appendChild(card)
                
		        chatResponseContainer.appendChild(messageElement);
		        
		        playNotificationSound();
		        sendMessageButton.disabled = false;
		        scrollToBottom()
		        
            })
            .catch(error => {
                console.error('Erro ao buscar o produto:', error);
            });
    }

    
});