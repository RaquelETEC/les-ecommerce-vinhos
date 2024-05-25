let totalDesconto = 0.00;
let totalPagamento = 0.00;
let totalPedido = 0.00;
let totalFrete = 0.00;
let totalSaldo= 0.00;
let elementoCartaoParaAlteracao=""; 
var listaCartoes = [];
var listaCupons = [];

let idCupom = 0;

  // Espera o DOM ser carregado
    document.addEventListener("DOMContentLoaded", function() {
        function calculaData(dias, formato) {
            var hoje = new Date();
            var dataEntrega = new Date(hoje);
            dataEntrega.setDate(dataEntrega.getDate() + dias);

            if (formato === "MMM") {
                var meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
                return meses[dataEntrega.getMonth()];
            } else {
                return dataEntrega.toLocaleDateString();
            }
        }

        // Chamada da função para atualizar o elemento HTML
        calcularTotais();
        var recebimento = `Receba entre ${calculaData(4)} e ${calculaData(8)} - ${calculaData(8, "MMM")}`;
        var elementoRecebimento = document.getElementById('id-receba-em');
        if (elementoRecebimento) {
            elementoRecebimento.textContent = recebimento;
        }
    });
    
    
function salvarEndereco() {
	// Obtenha o radio button selecionado
	var enderecoSelecionado = document.querySelector('input[name="enderecoSelecionado"]:checked');
	// Verifique se algum radio button está selecionado
	if (enderecoSelecionado) {
		// Obtenha o valor do atributo value do radio button selecionado
		var enderecoId = enderecoSelecionado.value;
		
		// Atualize os spans com os valores dos campos do endereço
		document.getElementById('Logradouro').textContent = document.getElementById(`Logradouro${enderecoId}`).textContent;
		document.getElementById('Logradouro').textContent = document.getElementById(`Logradouro${enderecoId}`).textContent;
		document.getElementById('Numero').textContent = document.getElementById(`Numero${enderecoId}`).textContent;
		document.getElementById('Bairro').textContent = document.getElementById(`Bairro${enderecoId}`).textContent;
		document.getElementById('Cidade').textContent = document.getElementById(`Cidade${enderecoId}`).textContent;
		document.getElementById('Estado').textContent = document.getElementById(`Estado${enderecoId}`).textContent;
		document.getElementById('Cep').textContent = document.getElementById(`Cep${enderecoId}`).textContent;
		document.getElementById('Pais').textContent = document.getElementById(`Pais${enderecoId}`).textContent;
		document.getElementById('Nome').textContent = document.getElementById(`Nome${enderecoId}`).textContent;
		document.getElementById('idEndereco').textContent = document.getElementById(`enderecoIdModal${enderecoId}`).textContent;

		// Feche o modal de seleção de endereço, se necessário
		$('#modalSelecaoEndereco').modal('hide');
		
		calcularFrete(document.getElementById(`Cidade${enderecoId}`).textContent); 
 

	} else {
		// Se nenhum radio button estiver selecionado, exiba uma mensagem de erro
		console.log("Selecione um endereço ou cadastre!");
	}
	

}

function AdicionarNovoEndereco(id) {
    // Obtenha uma referência ao formulário pelo seu nome
    var formulario = document.forms["frmcliente"];

    // Verifique se todos os campos obrigatórios estão preenchidos
    var camposObrigatorios = formulario.querySelectorAll('[required]');
    for (var i = 0; i < camposObrigatorios.length; i++) {
        if (!camposObrigatorios[i].value) {
            alert("Por favor, preencha todos os campos obrigatórios.");
            return; // Pare a execução se algum campo obrigatório estiver vazio
        }
    }

    // Obtenha os valores dos campos do formulário
    var nome = formulario["nome"].value;
    var tipoResidencia = formulario["typeTipoResidencia"].value;
    var tipoLogradouro = formulario["typeTipoLogradouro"].value;
    var logradouro = formulario["typeLogradouro"].value;
    var numero = formulario["typeNumero"].value;
    var bairro = formulario["typeBairro"].value;
    var cidade = formulario["typeCidade"].value;
    var estado = formulario["typeEstado"].value;
    var cep = formulario["typeCep"].value;
    var pais = formulario["typePais"].value;
    var observacoes = formulario["observacoes"].value;
    var cadastrarEndNoPerfil = formulario["cadastrarEndNopPerfil"].value;
    var tipoEndereco =  (cadastrarEndNoPerfil == "Sim" ?  "ENTREGA" : "NAO_SE_APLICA");
    
    var dados =  "id=" + id + "&nome=" + nome + "&typeTipoResidencia=" + tipoResidencia + "&typeTipoLogradouro=" + tipoLogradouro +
            "&typeLogradouro=" + logradouro + "&typeNumero=" + numero + "&typeBairro=" + bairro + "&typeCidade=" + cidade +
            "&typeEstado=" + estado + "&typeCep=" + cep + "&typePais=" + pais + "&observacoes=" + observacoes +
            "&tipoEndereco=" + tipoEndereco + "&venda=" + cadastrarEndNoPerfil;
	
    var url = "/les-ecommerce-vinhos/inserirEndereco?" + dados;
        
	fazerRequisicaoAjax(url, function(resposta) {
	    if (resposta.toUpperCase().includes('SUCESS')) {
	        alert('Novo endereço salvo!');
	        const idGerado = resposta.split('=')[1]; // Corrigido o erro de sintaxe aqui
	        
		    // Atualize os spans com os valores dos campos do endereço
		    document.getElementById('idEndereco').textContent = idGerado;
		    document.getElementById('Logradouro').textContent = logradouro;
		    document.getElementById('Numero').textContent = numero;
		    document.getElementById('Bairro').textContent = bairro;
		    document.getElementById('Cidade').textContent = cidade;
		    document.getElementById('Estado').textContent = estado;
		    document.getElementById('Cep').textContent = cep;
		    document.getElementById('Pais').textContent = pais;
		    document.getElementById('Nome').textContent = nome;
		
		    // Calcula o frete
		    calcularFrete(cidade);
	    } else {
	        alert(resposta);
	    }
	    $('#modalCadastroEndereco').modal('hide');
	}, function() {
	    alert("Erro ao cadastrar endereço");
	});

}

function calcularFrete(cidade) {
    // Converte a cidade para minúsculas para facilitar a comparação
    cidade = cidade.toUpperCase();
    var recebimento;
    var TotalFrete;

    // Verifica a cidade de destino e define o custo de entrega
    if (cidade.includes('MOGI DAS CRUZES')) {
        TotalFrete = '0';
        recebimento = `Receba entre ${calculaData(1)} e ${calculaData(2)} - ${calculaData(2, "MMM")}`;

    } else if (cidade.includes('ITAQUAQUECETUBA')) {
        TotalFrete = '6.50';
        recebimento = `Receba entre ${calculaData(2)} e ${calculaData(4)} - ${calculaData(4, "MMM")}`;

    } else if (cidade.includes('SAO PAULO')) {
        TotalFrete = '20';
        recebimento = `Receba entre ${calculaData(4)} e ${calculaData(8)} - ${calculaData(8, "MMM")}`;

    } else if (cidade.includes('RIO DE JANEIRO')) {
        TotalFrete = '80';
        recebimento = `Receba entre ${calculaData(16)} e ${calculaData(24)} - ${calculaData(24, "MMM")}`;

    } else {
        TotalFrete = '50';
    }

    document.getElementById('id-receba-em').textContent = recebimento;
    document.getElementById("id-valor-entrega").textContent = `R$ ${TotalFrete}`;

	totalFrete = parseFloat(TotalFrete);
	
	calcularTotais();

}


 function calculaData(dias, formato) {
        var hoje = new Date();
        var dataEntrega = new Date(hoje);
        dataEntrega.setDate(dataEntrega.getDate() + dias);

        if (formato === "MMM") {
            var meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
            return meses[dataEntrega.getMonth()];
        } else {
            return dataEntrega.toLocaleDateString();
        }
    }
    
    
function verificarEnderecoAntesDeAbrirModal() {
    var enderecoInserido = document.getElementById('idEndereco').textContent;

    if (!enderecoInserido) {
        alert("Por favor, insira um endereço primeiro.");
    } else {
        $('#modalCupomT').modal('show'); // Abrir a modal de cupons
    }

    $('#modalCupomT').modal('hide'); // Fechar a modal de cupons
}

function verificarEnderecoPagamento() {
    var enderecoInserido = document.getElementById('idEndereco').textContent;

    if (!enderecoInserido) {
        alert("Por favor, insira um endereço primeiro.");
    } else {
        $('#modalCartoes').modal('show'); // Abrir a modal de cupons
    }

    $('#modalCartoes').modal('hide'); // Fechar a modal de cupons
}

function listarOpcoesSelecionadas() {
	debugger
	totalDesconto = 0;
    var cuponsSelecionados = document.querySelectorAll('input[name="cupomsSelecionadoT[]"]:checked');
    limparListagemCupons();
    cuponsSelecionados.forEach(function(cupomSelecionado) {
        var cupomId = cupomSelecionado.value;
        var cupomDescricao = document.getElementById(`NomeCupomT${cupomId}`).innerText;
        var cupomImagem = document.getElementById(`imagemCupomT${cupomId}`).src;
        var cupomValor = document.getElementById(`ValorCupomT${cupomId}`).innerText;
        var cupomCodigo = document.getElementById(`CodCupomT${cupomId}`).innerText;
        var cupomTipo= document.getElementById(`CupomTipo${cupomId}`).innerText;
		
		adicionarCupomLista(cupomId, cupomCodigo, cupomDescricao, cupomValor, cupomTipo)
		
        addCupom(cupomDescricao, cupomImagem, cupomValor, cupomId,cupomCodigo,cupomTipo);
    });
    
    calcularTotais();

}

function addCupom(descricao, imagem, valor, cupomId,cupomCodigo,tipo) {
    idCupom++;
    var novoCard = document.createElement("div");
    novoCard.classList.add("card", "mt-3");
    var cardId = "cupomCard_" + idCupom;

	novoCard.innerHTML = `
	    <div id="${cardId}" class="lineCupom" style="padding: 10px;">
	        <div class="row">
	            <div class="col-md-10">
	                <p class="fs-6">${cupomCodigo} - ${descricao}</p>
	            </div>
	            <div class="col-md-2 text-end">
                    <button onClick='removerCupom(this)' type="button" class="btn btn-dark" data-cupom-id="${cupomId}" data-valor-cupom="${valor}" data-tipo-cupom="${tipo}">
	                    <i class="fas fa-trash-alt"></i> 
	                </button>
	            </div>
	        </div>
	    </div>`;
	    
    document.getElementById("cupomContainer").appendChild(novoCard);
    $('#modalCupomT').modal('hide'); // Fechar a modal de cupons

	calcularTotaisCupom(valor,tipo);
};

function adicionarCupomLista(cupomId, cupomCodigo, cupomDescricao, cupomValor, cupomTipo) {
	cupomValor = parseFloat(cupomValor.replace(/[^0-9.-]/g, ''));
    listaCupons.push({ id: cupomId, codigo: cupomCodigo, desc: cupomDescricao, valor: cupomValor, tipo: cupomTipo });
}

let quantTipoP = 0; 
function verificaCupomSelecionado(tipo, cupomId, checked) {
    debugger;

    if (tipo === 'P') {
        if (checked) {
            // Incrementa quantTipoP apenas se a opção for marcada
            quantTipoP += 1; 
        } else {
            // Decrementa quantTipoP apenas se a opção for desmarcada
            quantTipoP -= 1; 
        }
    }
    
    if (quantTipoP >= 2) {
        alert('Você não pode selecionar mais do que 1 cupom promocional!');
        var checkboxId = "cupomTselect" + cupomId;
        document.getElementById(checkboxId).checked = false;
        quantTipoP -= 1;
    }
}

function calcularTotaisCupom(valor,tipo){
    valor = parseFloat(valor.replace(/[^0-9.-]/g, ''));

    if (tipo === 'P') {
        // Calcula o desconto como uma porcentagem do total do pedido
        const desconto = (valor * totalPedido )/100;
        totalDesconto += desconto;
    } else if (tipo === 'T') {
        // Adiciona o valor total do desconto ao totalDesconto
        totalDesconto += valor;
    }    
}

function limparListagemCupons() {
    var cupomContainer = document.getElementById("cupomContainer");
    cupomContainer.innerHTML = ""; 
}

function removerCupom(botao) {
	debugger;
    // Remove o card de cupom pai do botão clicado
    var cardCupom = botao.closest('.lineCupom');
    cardCupom.remove();

    // Obtém os valores dos atributos de dados do botão
    var valorRemovido = parseFloat(botao.dataset.valorCupom.replace(/[^0-9.-]/g, ''));

    var tipoRemovido = botao.dataset.tipoCupom;

    // Subtrai o valor do desconto removido do totalDesconto
    if (tipoRemovido === 'P') {
        totalDesconto -= (valorRemovido * totalPedido )/100;
    } else if (tipoRemovido === 'T') {
        totalDesconto -= valorRemovido;
    }

  	var cupomId = botao.dataset.cupomId;
    var checkboxId = "cupomTselect" + cupomId;
    document.getElementById(checkboxId).checked = false;
    
    totalDesconto = parseFloat(totalDesconto.toFixed(2));
    calcularTotais();

}

function atualizaCupomIdSelecionado(idCupomTroca) {
	idCupomCardTrocaSelecionado = idCupomTroca;
}


function addCartaoCard() {
    var cartaoSelecionado = document.querySelector('input[name="cartaoSelecionado"]:checked');

    if (!cartaoSelecionado) {
        alert('Selecione ou cadastre um cartão de crédito!');
    } else {
        var nomeCartao = document.getElementById(`nomeCartao${cartaoSelecionado.value}`).textContent.trim();
        var numeroCartao = document.getElementById(`numeroCartao${cartaoSelecionado.value}`).textContent;
        var imagemBandeira = document.getElementById(`imagemBandeira${cartaoSelecionado.value}`);
        var codigoCartao = document.getElementById(`codSeguranca${cartaoSelecionado.value}`).textContent;
        var valorCartao = parseFloat(document.getElementById('valorCartao').value);
        var idCartao = parseInt(cartaoSelecionado.value, 10);
        // Verifica se a soma de todos os cupons é equivalente a 90% do total do pedido
        var percentualCupons = (totalDesconto * 100) / totalPedido;
        
        debugger;
        var valorCartaoAlteracao = elementoCartaoParaAlteracao ? elementoCartaoParaAlteracao.getAttribute('data-valor-cartao') : 0;
        
        if( valorCartao > parseFloat(totalSaldo) + parseFloat(valorCartaoAlteracao)){
			alert("O valor máximo a pagar é de: R$" +parseFloat(totalSaldo) + parseFloat(valorCartaoAlteracao))
			
		} else if (percentualCupons >= 85 || valorCartao >= 10) {
            if (elementoCartaoParaAlteracao != "") {
                // Remova o registro atual
                removerCartao(elementoCartaoParaAlteracao);
                elementoCartaoParaAlteracao = "";
            }
            // Insira um novo cartão
            insertCardCartao(idCartao, nomeCartao, numeroCartao, imagemBandeira.src, codigoCartao, valorCartao);
            totalPagamento += parseFloat(valorCartao.toFixed(2));
            adicionarCartaoLista(idCartao, nomeCartao, numeroCartao, imagemBandeira.src, codigoCartao, valorCartao);
            calcularTotais();
            $('#modalCartoes').modal('hide'); // Fechar a modal de cupons
        } else if (!valorCartao) {
            alert("Informe o valor do pagamento para este cartão!");
        } else {
            alert("Só possível adicionar o valor abaixo de R$ 10,00 caso o total dos cupons corresponda a 85% do pedido");
        }
    }
}


function abrirCadastroCartao() {
    $('#modalCartoes').modal('hide'); // Fecha a modal atual
    $('#modalCadastroCartao').modal('show'); // Abre a nova modal
}

function AdicionarNovoCartao(idCliente) {
	debugger;
     var percentualCupons = (totalDesconto * 100) / totalPedido;

    var formulario = document.forms["frmCartao"];
    var nome = formulario["CartaoNome"].value;
    var bandeira = formulario["tipoBandeira"].value;
    var numero = formulario["CartaoNumero"].value;
    var codigo = formulario["CartaoCodigo"].value;
    var cadastrarNoPerfil = formulario["cadastrarCartNoPerfil"].value;
    var valorCartao = parseFloat(formulario["valorCartaoCad"].value);
    var cartaoPadrao = 'NÃO';
    var imagemBandeira = bandeira == 1 ? 'imagens/assets/CartaoMaster.png' : 'imagens/assets/CartaoVisa.png';

	if( valorCartao > totalSaldo ){
		alert("O valor máximo a pagar é de: R$" + totalSaldo)
	}else if (percentualCupons >= 85 || valorCartao >= 10) {
            var dados =
                "typeId=" + idCliente +
                "&CartaoNumero=" + numero +
                "&CartaoNome=" + nome +
                "&CartaoPadrao=" + cartaoPadrao +
                "&CartaoCodigo=" + codigo +
                "&tipoBandeira=" + bandeira+
                "&CadastrarNoPerfil=" + cadastrarNoPerfil+
                "&tipoSolicitacao="+cadastrarNoPerfil;
                
            var url = "/les-ecommerce-vinhos/areaCliente/inserirCartao?" + dados;

            fazerRequisicaoAjax(url, function(resposta) {
			    if (resposta.toUpperCase().includes('SUCESS')) {
			        alert('Novo cartão salvo!');
			        const idCartao = resposta.split('=')[1]; // Corrigido o erro de sintaxe aqui
				
		 			insertCardCartao(idCartao, nome, numero, imagemBandeira, codigo, valorCartao);
		            totalPagamento += parseFloat(valorCartao.toFixed(2));
		            adicionarCartaoLista(idCartao, nome, numero, imagemBandeira, codigo, valorCartao);
		            adicionarNovoCartaoAoHTML(idCartao, nome, numero, imagemBandeira, codigo);

		            calcularTotais();
		            $('#modalCadastroCartao').modal('hide');

               }
            }, function() {
                alert("Erro ao cadastrar Cartão");
            });

     } else if (!valorCartao) {
            alert("Informe o valor do pagamento para este cartão!");
     }else {
         alert("Só possível adicionar o valor abaixo de R$ 10,00 caso o total dos cupons corresponda a 85% do pedido");

    }
}


function insertCardCartao(idCartao, nomeCartao, numeroCartao, imagemBandeira, codigoCartao, valorCartao) {
	// Cria um novo elemento div para representar o card de forma de pagamento
	var novoPagamento = document.createElement("div");
	novoPagamento.classList.add("CartoesSection");

	// Define o conteúdo do card de forma de pagamento usando os valores recuperados
	novoPagamento.innerHTML = `
        <div class="row lineCart">
            <div class="col-2">
                <p id="cartao" class="text-md-start">${nomeCartao}</p>
            </div>
            <div class="col-2">
                <p id="numeroCartao" class="text-md-start">${numeroCartao}</p>
            </div>
	            <div class="col-2">
				    <img src="${imagemBandeira}" alt="Imagem da Bandeira" class="img-fluid" style="width: 35%; height: auto;">
				</div>
            <div class="col-1">
                <p class="text-md-start">${codigoCartao}</p>
            </div>
            <div class="col-2">
                <p class="text-center">${valorCartao}</p>
            </div>
            <div class="col-3">
             	<button class="Botao2" onClick="EditarCartao(this)" data-id-cartao="${idCartao}" data-valor-cartao="${valorCartao}">Editar</button>
                <button class="Botao1" onClick="removerCartao(this)" data-id-cartao="${idCartao}" data-valor-cartao="${valorCartao}">Excluir</button>
            </div>
        </div>
    `;

	// Adiciona o novo card de forma de pagamento ao container de formas de pagamento
	document.getElementById("pagamentoContainer").appendChild(novoPagamento);
	
	
	 var radios = document.querySelectorAll('input[name="cartaoSelecionado"]');
    radios.forEach(function(radio) {
        radio.checked = false;
    });
    document.getElementById('valorCartao').value = null;
    $('#modalCartoes').modal('hide'); 

}

// Função para adicionar um novo cartão à lista na página
function adicionarNovoCartaoAoHTML(id, nome, numero, imagemBandeira, codigo) {
	debugger;
    var modalBody = document.querySelector('.modal-body');
    
    var novoCartaoHTML = `
        <div class="card mt-3">
            <div class="card-body">
                <div class="row">
                    <div class=" col-md-2 d-flex align-items-center justify-content-center">
                        <!-- Adicionando classes para alinhar vertical e horizontalmente -->
                        <div class="custom-radio">
                            <input type="radio" id="cartaoselect${id}" name="cartaoSelecionado" value="${id}">
                            <label for="cartaoselect${id}"></label>
                        </div>
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-center">
                        <img id="imagemBandeira${id}" src="${imagemBandeira}" alt="Imagem da Bandeira" class="img-fluid">
                    </div>
                    <div class="col-md-8">
                        <p id="nomeCartao${id}" class="card-title">
                            <strong>${nome}</strong>
                        </p>
                        <p class="card-text">
                            <span id="numeroCartao${id}">${numero}</span>
                            <span id="codSeguranca${id}">${codigo}</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    `;
    
    // Adiciona o HTML do novo cartão à lista de cartões na página
    modalBody.innerHTML += novoCartaoHTML;
}

function adicionarCartaoLista(id, nome, numero, bandeira, codigo, valor) {
    listaCartoes.push({ id: id, nome: nome, numero: numero, codigoSeguranca: codigo, valor: valor });
}

function EditarCartao(elemento) {
	debugger;
	elementoCartaoParaAlteracao = elemento;
	
    var valorCartao = elemento.getAttribute('data-valor-cartao');
    var idCartao = elemento.getAttribute('data-id-cartao');

    // Desmarque a opção selecionada
    var radios = document.querySelectorAll('input[name="cartaoSelecionado"]');
    radios.forEach(function(radio) {
        radio.checked = false;
    });

    // Selecione a opção desejada
    var radioId = "cartaoselect" + idCartao;
    document.getElementById(radioId).checked = true;

    // Passe o valor do cartão selecionado para a modal de edição
    document.getElementById('valorCartao').value = valorCartao;

    // Abra a modal de edição
    $('#modalCartoes').modal('show');
    
    
}

function removerCartao(botao) {
	// Remove o card de forma de pagamento pai do botão clicado
	debugger;
	var cardPagamento = botao.closest('.lineCart');
	var valorRemovido = parseFloat(botao.dataset.valorCartao.replace(/[^0-9.-]/g, ''));
	var idCartao = botao.dataset.idCartao;

	totalPagamento -= valorRemovido; 
	calcularTotais();
	
	cardPagamento.remove();
	
	removerCartaoLista(idCartao); 
}

//remover cartao da lista que é enviada para o banco de dados
function removerCartaoLista(id) {
    listaCartoes = listaCartoes.filter(cartao => cartao.id != id);
}

function calcularTotais() {
	debugger;
	let TotalProduto = parseFloat(document.getElementById("idTotalProduto").textContent.replace('R$', '').trim());
	// Somando os valores aos totais
	//totalCupomPromocional += parseFloat(valorCupomPerc);
	//totalCupomTroca += parseFloat(valorCupomT);
	//totalCartoes += parseFloat(valorCartoes); // Basta adicionar o valor dos cartões

	//let totalCupomPValor = TotalProduto - (totalCupomPromocional > 0 ? (totalCupomPromocional * 100) / TotalProduto : 0);

	totalPedido = TotalProduto + totalFrete
	//totalAPagar = totalPedido - totalCartoes;
	totalSaldo = totalPedido - totalDesconto - totalPagamento;

	// Arredondando os totais para duas casas decimais
	
	totalFrete = parseFloat(totalFrete.toFixed(2));
	totalPagamento = parseFloat(totalPagamento.toFixed(2));
	totalPedido =  parseFloat(totalPedido.toFixed(2));
	totalSaldo = parseFloat(totalSaldo.toFixed(2));
	
	//totalCupomPValor = parseFloat(totalCupomPValor.toFixed(2));
	//totalCupomTroca = parseFloat(totalCupomTroca.toFixed(2)); // Convertendo para número e arredondando
	//total = parseFloat(totalPedido.toFixed(2));
	//totalCartoes = parseFloat(totalCartoes.toFixed(2)); // Convertendo para número e arredondando
	//totalAPagar = parseFloat(totalAPagar.toFixed(2)); // Convertendo para número e arredondando

	document.getElementById(`id-valor-total-Entrega`).textContent = `R$ ${totalFrete}`;
	document.getElementById(`id-valor-entrega`).textContent = `R$ ${totalFrete}`;
	document.getElementById(`idTotalPedido`).textContent = `R$ ${totalPedido}`;
	document.getElementById(`idTotalPedidoS`).textContent = `R$ ${totalPedido}`;
	document.getElementById(`idTotalSaldo`).textContent = `R$ ${totalSaldo}`;
	document.getElementById(`idTotalCupom`).textContent = `R$ ${totalDesconto}`;
	document.getElementById(`idTotalPagamento`).textContent = `R$ ${totalPagamento}`;


	//document.getElementById(`idTotalCupom`).textContent = `R$ ${totalCupomPValor}`;
	//document.getElementById(`idTotalPagamento`).textContent = `R$ ${totalCartoes}`;
	//document.getElementById(`idTotalApagar`).textContent = `R$ ${totalAPagar}`;

}

function validarPedido(idCliente) {
	debugger	
	var enderecoInserido = document.getElementById('idEndereco').textContent;

    if (!enderecoInserido) {
        alert("Por favor, insira um endereço primeiro.");
    } else if (totalSaldo > 0) {
		alert('Valor restante a ser pago é de R$:'+totalSaldo+'. \nRevise os dados de pagamento.');
	} 
	else {
		cadastrarPedidoVenda(idCliente);
	}

}


function cadastrarPedidoVenda(idCliente) {
    debugger;
    totalPedido = parseFloat(document.getElementById("idTotalPedido").textContent.replace('R$', '').trim());
	var idEndereco = document.getElementById('idEndereco').textContent;

    var dados = {
        idCliente: idCliente,
        totalPedido: totalPedido,
        totalDesconto: totalDesconto,
        totalPagamento: totalPagamento, 
        totalFrete: totalFrete,
        totalSaldo: totalSaldo,
        idEndereco: idEndereco,
        cartoes: listaCartoes,
        cupons: listaCupons

    };

	var url = "/les-ecommerce-vinhos/CadastrarPedidoVenda";
	
	fazerRequisicaoAjax(url, function(resposta) {
	    if (resposta.toUpperCase().includes("ERRO")) {
	        alert(resposta);
	    } else {
	        window.location.href = "/les-ecommerce-vinhos/areaVenda/VendaFinalizada.html" + resposta;
	    }
	}, function() {
	    alert("Erro ao cadastrar pedido JS");
	}, 'POST', JSON.stringify(dados), 'application/json');
}

function fazerRequisicaoAjax(url, sucessoCallback, erroCallback, metodo='GET', dados, tipoConteudo) {
    var xhr = new XMLHttpRequest();
    xhr.open(metodo, url);
    xhr.setRequestHeader("Content-Type", tipoConteudo);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                sucessoCallback(xhr.responseText);
            } else {
                erroCallback();
            }
        }
    };
    xhr.send(dados);
}


