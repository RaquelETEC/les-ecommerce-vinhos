/**
 * Confirmar
 * 
 * @author Raquel e caynan
 */

function confirmar(idcon) {
	let resposta = confirm("Confirma a exclusão deste contato?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
}


function confirmarCliente(id) {
	let resposta = confirm("Confirma a exclusão deste contato? ID:" + id)
	if (resposta === true) {
		window.location.href = "deleteClient?id=" + id
	}
}


function confirmarExcluirEndereco(idEnd, id) {
	let resposta = confirm("Confirma a exclusão deste contato? ID:" + idEnd)
	if (resposta === true) {
		window.location.href = "deleteEndereco?idEnd=" + idEnd + "&id=" + id;
	}
}


function confirmarPedido(idPedido, i, ValorCupom, idCliente) {
	debugger;
    const Status = document.getElementById('statusPedido' + i).value;
    let url = "";

    if (Status == "RECEBER PRODUTOS") {
        url = "EditarPedido?id=" + idPedido + "&PedidoStatus=" + 'PEDIDO CANCELADO';

        fazerRequisicaoAjax(url, async function(resposta) {
            if (resposta === "SUCESS") {
                alert("Status alterado com sucesso");

                try {
                    await gerarCupomTroca(idPedido, 0, ValorCupom, idCliente);
                } catch (erro) {
                    alert("Erro ao gerar cupom de troca: " + erro);
                }

            } else {
                alert("Erro ao alterar status");
                window.location.href = window.location.href;
            }
        }, function(erro) {
            alert("Erro ao alterar status: " + erro);
        });

    } else {
        url = "EditarPedido?id=" + idPedido + "&PedidoStatus=" + Status;

        fazerRequisicaoAjax(url, function(resposta) {
            if (resposta === "SUCESS") {
                alert("Status alterado com sucesso");
                window.location.href = window.location.href;
            } else {
                alert("Erro ao alterar status");
                window.location.href = window.location.href;
            }
        }, function(erro) {
            alert("Erro ao alterar status: " + erro);
        });
    }
}
/*
function confirmarTroca (idItem ="",pedidoId="",novoStatus="",novoStatusItem=""){
	debugger;
	const itens = [idItem];

	var dados = `&itens=${itens.join(',')}&pedido=${pedidoId}&novoStatusPedido=${novoStatus}&tipoSolicitacao=${novoStatusItem}`;
   
	var url = "/les-ecommerce-vinhos/areaCliente/solicitarTroca.html?" + dados;
	fazerRequisicaoAjax(url, function(resposta) {
		if (resposta.includes("erro")) {
			alert(resposta);
		}
		else {
			alert("Status alterado com sucesso");
			window.location.href = window.location;
		}
	}, function() {
		alert("Erro ao cadastrar pedido JS");
	});
}
	*/
function confirmarTroca(idItem ="", idTroca="", quantItem ="", quantTocada ="", pedidoId="",novoStatus="",novoStatusItem="") {
 debugger;
  // Extrair os IDs e quantidades dos itens de troca
  const itensSelecionados = [idItem];
  const quantidades = [quantItem];
  const quantidadesTrocadas = [quantTocada]; 

  const url = `/les-ecommerce-vinhos/areaCliente/solicitarTroca.html?idTroca=${idTroca}&pedido=${pedidoId}&novoStatusPedido=${novoStatus}&tipoSolicitacao=${novoStatusItem}&itens=${itensSelecionados.join(',')}&quantidades=${quantidades.join(',')}&quantidadesTrocadas=${quantidadesTrocadas.join(',')}`; // Adicionado

  fazerRequisicaoAjax(url, function(resposta) {
    if (resposta.includes("error")) {
      alert("Erro ao gerenciar Troca");
    } else {
      alert("Status alterado com sucesso");
      window.location.href = window.location;
    }
  }, function() {
    alert("Erro ao gerenciar troca JS");
  });
}	
	
// Variáveis para armazenar os dados do botão "Confirmar Recebimento"
let itemId,trocaId, pedidoId, prodId, total, quantidadeItem, novoStatus, novoStatusItem, clienteId;

// Função para capturar os dados do botão "Confirmar Recebimento"
function capturarDadosConfirmarRecebimento(btn) {
    itemId = btn.getAttribute('data-item-id');
    trocaId= btn.getAttribute('data-item-troca-id');
    prodId = btn.getAttribute('data-prod-id');
	total = btn.getAttribute('data-prod-valor');
    pedidoId = btn.getAttribute('data-pedido-id');
    clienteId = btn.getAttribute('data-cliente-id');
    quantidadeItem = btn.getAttribute('data-quant-item');
    novoStatus = btn.getAttribute('data-novo-status');
    novoStatusItem = btn.getAttribute('data-novo-status-item');
    
}

function processarConfirmacao(confirmacao) {
	debugger;
	let  confirmart = confirmarTroca (itemId,trocaId,quantidadeItem,0, pedidoId, novoStatus,novoStatusItem)
   	let  confirmarC =  gerarCupomTroca(pedidoId,prodId,total,clienteId);
   	
    if (confirmacao)
    alert("Estoque movimentado!")
	//movimentarEstoque(prodId, pedidoId, quant);
	

    itemId = null;
    pedidoId = null;
    quantidadeItem = null;
    novoStatus = null;
    novoStatusItem = null;
}

function movimentarEstoque(itemId, pedidoId, quant){
	alert("voce movimentou o estoque")
}
	
async function gerarCupomTroca(pedidoId, prodId,total,clienteId)	{
	debugger;
	var dados = `&pedido=${pedidoId}&prodId=${prodId}&valorCupom=${total}&tipoCupom=T`;
   
	var url = "http://localhost:8080/les-ecommerce-vinhos/gerarCupom.html?" + dados;
	fazerRequisicaoAjax(url, function(resposta) {
		if (resposta.includes("erro")) {
			alert(resposta);
		}
		else {
			vincularCupomAoCliente(resposta, clienteId);
			
		}
	}, function() {
		alert("Erro ao gerar cupom de troca");
	});
}
	
	
function vincularCupomAoCliente(cupomId, clienteID){
	debugger;
	var dados = `&cupomId=${cupomId}&clienteId=${clienteID}`;
   
	var url = "http://localhost:8080/les-ecommerce-vinhos/VincularCupomAoCliente.html?" + dados;
	fazerRequisicaoAjax(url, function(resposta) {
		if (resposta.includes("erro")) {
			alert(resposta);
			 window.location.href = window.location.href;
		}
		else {
			alert("Cupom vinculado ao cliente com sucesso!");
		}
	}, function(e) {
		alert("Erro ao vincular cupom com cliente"+e);
	});
}

async function fazerRequisicaoAjax(url, sucessoCallback, erroCallback) {
    try {
        const resposta = await fetch(url);
        if (resposta.ok) {
            sucessoCallback(await resposta.text());
        } else {
            throw new Error('Erro na requisição: ' + resposta.status);
        }
    } catch (erro) {
        erroCallback(erro);
    }
}
