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

    form.addEventListener("submit", async(event) => {
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
        const informaçõesComplementares = document.getElementById("obs-notes").value;

        // Formatação dos valores em um texto estruturado
        const textForm = `Tipo de vinho preferido: ${wineType}, Variedade de uva preferida: ${grapeVariety}, Região preferida: ${region}, Faixa de preço: ${budget}, Nível de doçura desejado: ${sweetnessLevel}, Ocasião: ${occasion}, Combinação com alimentos: ${foodPairing}, Nível de experiência: ${experienceLevel}, Preferência por corpo: ${bodyPreference}, Notas de sabor preferidas: ${flavorNotes}, Observações: ${informaçõesComplementares}`;

        let textProdutosDisponiveis = "";

        // Fazer a requisição HTTP GET para /ProdutosDisponiveis
        await fetch("http://localhost:8080/les-ecommerce-vinhos/produtosDisponiveis.html")
            .then(response => response.json()) // Extrair os dados JSON da resposta
            .then(produtos => {
                // Manipular os dados dos produtos como desejado

                // Exemplo de como você pode utilizar os dados retornados
                for (const produto of produtos) {
                    textProdutosDisponiveis += `ID=${produto.id}, DESC=${produto.desc}, PRECO=${produto.pro_preco_venda}; `;
                }
            })
            .catch(error => console.error("Erro ao obter produtos:", error));

        const textForIa = `Com base na lista de vinhos, recomendo um produto com base nas respostas: ${textForm}. 
        Esta é lista de vinhos que o site possui: ${textProdutosDisponiveis}. 
        Por favor, forneça sua recomendação no seguinte formato:
        ID= <ID do produto>
        MOTIVO= <Motivo da recomendação>`;
        
       await fetch("https://api.openai.com/v1/chat/completions", {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                Authorization: "Bearer " + OPENAI_API_KEY
            },
            body: JSON.stringify({
                model: "gpt-3.5-turbo",
                messages: [{ role: "user", content: textForIa }],
                max_tokens: 2048,
                temperature: 0.5
            }),
        })
        .then((resposta) => resposta.json())
        .then((dados) => {
           exibirResposta(dados.choices[0].message.content)
        })
        .catch((erro) => {
            console.log(erro);
        });
 
        // Limpar os campos do formulário após o envio (opcional)
        form.reset();
    });
   
});


async function exibirResposta(respostaIa) {
    const linhas = respostaIa.split('\n');
    const id = linhas[0].split('=')[1].trim();
    const motivo = linhas[1].split('=')[1].trim();
	const textAssistent = document.getElementById("text-assistent");
	const infoForm = document.getElementById("info-form");
	
	infoForm.style.display = "none";
	textAssistent.innerText  = 'Aqui esta sua recomendação: '
	
    // Inicializar o produto com valores padrão
    const produto = {
        id: id,
        desc: "",
        preco: "",
        imgSrc: ""
    };

    try {
        // Fazer a requisição para buscar o produto pelo ID
        const response = await fetch(`http://localhost:8080/les-ecommerce-vinhos/buscaProduto.html?id=${id}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const dadosProduto = await response.json();

        // Atualizar o objeto produto com os dados retornados
        produto.desc = dadosProduto.desc;
        produto.preco = `R$ ${dadosProduto.pro_preco_venda}`;
        produto.imgSrc = `imagens/produtos/${dadosProduto.codigo_barra}.png`;

        // Criar os elementos HTML e exibi-los no chat
        const chatResponseContainer = document.getElementById('chat-response-container');
        const card = document.createElement('div');
        card.className = 'card mb-3';

        const imageContainer = document.createElement('div');
        imageContainer.className = 'image-container';
        imageContainer.style.backgroundColor = '#f2f2f2';

        const img = document.createElement('img');
        img.src = produto.imgSrc;
        img.alt = `Imagem do Produto ${produto.desc}`;
        img.className = 'card-img-top img-fluid rounded-start';
        img.style.width = '40%';
        img.style.display = 'block';
        img.style.margin = '0 auto';
        imageContainer.appendChild(img);

        const cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        const cardTitle = document.createElement('h6');
        cardTitle.className = 'card-title text-center';
        cardTitle.style.height = '5vh';
        cardTitle.innerText = produto.desc;
        cardBody.appendChild(cardTitle);

        const price = document.createElement('p');
        price.className = 'price';
        price.innerHTML = `<span id="price">${produto.preco}</span>`;
        cardBody.appendChild(price);

        const addToCartButton = document.createElement('button');
        addToCartButton.type = 'button';
        addToCartButton.className = 'btn btn-lg add-to-cart-btn text-center add_carrinho';
        addToCartButton.onclick = () => AdicionarAoCarrinho('20', id, '1');
        addToCartButton.innerText = 'Adicionar ao carrinho';
        cardBody.appendChild(addToCartButton);


        card.appendChild(imageContainer);
        card.appendChild(cardBody);
        chatResponseContainer.appendChild(card);

              // Adicionar o motivo abaixo do card com margem à direita
        const motivoText = document.createElement('p');
        motivoText.innerText = motivo;
        motivoText.style.marginRight = '40px'; 
        motivoText.style.textAlign = 'justify'; 
        chatResponseContainer.appendChild(motivoText);

        // Adicionar o botão "Voltar"
        const voltarButton = document.createElement('button');
        voltarButton.className = 'btn btn-secondary';
        voltarButton.innerText = 'Voltar';
        voltarButton.onclick = () => resetChat();
        chatResponseContainer.appendChild(voltarButton);
        
    } catch (error) {
        console.error("Erro ao obter produto:", error);
    }
}

function resetChat() {
    const chatResponseContainer = document.getElementById('chat-response-container');
    chatResponseContainer.innerHTML = ''; // Limpa o contêiner de respostas

    const textAssistent = document.getElementById("text-assistent");
    textAssistent.innerText = 'Olá, sou Roberto 🤖 seu assistente virtual, como posso ajudar?';

    const infoForm = document.getElementById("info-form");
    infoForm.style.display = "none"; // Esconder o formulário novamente

    const assistButton = document.getElementById("assist-button");
    assistButton.style.display = "block"; // Mostrar o botão de assistência
}