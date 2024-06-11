


function validarLogin2(){

		alert('tudo certo');
		window.location.href='PagInicial.html'
}

function rolarAteCampo(idCampo) {
    var campo = document.getElementById(idCampo);
    
    if (campo) {
        campo.scrollIntoView({ behavior: 'smooth' });
    }
}

function validarCadastro() {

    let nome = document.getElementById("typeNome").value;
    let email = document.getElementById("typeEmail").value;
    let senha = document.getElementById("typeSenha").value;
    let repitasenha = document.getElementById("typeRepitaSenha").value;
    let cpf = document.getElementById("typeCPF").value;
    let telefone = document.getElementById("typeNumeroTelefone").value;
    let nascimento = document.getElementById("typeNascimento").value;
    let genero = document.getElementById("typeGenero").value;
    let tipoResidencia = document.getElementById("typeTipoResidencia").value;
    let tipoLogradouro = document.getElementById("typeTipoLogradouro").value;
    let logradouro = document.getElementById("typeLogradouro").value;
    let numero = document.getElementById("typeNumero").value;
    let bairro = document.getElementById("typeBairro").value;
    let cidade = document.getElementById("typeCidade").value;
    let cep = document.getElementById("typeCep").value;
    let pais = document.getElementById("typePais").value;
    let estado = document.getElementById("typeEstado");
    let tipoTelefone = document.getElementById("TypeTipoTelefone");
    
    let typeTipoResidenciaE = document.getElementById("typeTipoResidenciaE").value;
    let typeTipoLogradouroE = document.getElementById("typeTipoLogradouroE").value;
    let typeLogradouroE = document.getElementById("typeLogradouroE").value;
    let typeNumeroE = document.getElementById("typeNumeroE").value;
    let typeBairroE = document.getElementById("typeBairroE").value;
    let typeCidadeE = document.getElementById("typeCidadeE").value;
    let typeCepE = document.getElementById("typeCepE").value;
    let typePaisE = document.getElementById("typePaisE").value;
    let typeEstadoE = document.getElementById("typeEstadoE");
	
	let typeTipoResidenciaC = document.getElementById("typeTipoResidenciaC").value;
    let typeTipoLogradouroC = document.getElementById("typeTipoLogradouroC").value;
    let typeLogradouroC = document.getElementById("typeLogradouroC").value;
    let typeNumeroC = document.getElementById("typeNumeroC").value;
    let typeBairroC = document.getElementById("typeBairroC").value;
    let typeCidadeC = document.getElementById("typeCidadeC").value;
    let typeCepC = document.getElementById("typeCepC").value;
    let typePaisC = document.getElementById("typePaisC").value;
    let typeEstadoC = document.getElementById("typeEstadoC");
	
    var avisoElement = document.getElementById("senhaAviso");
    var avisoElementRep = document.getElementById("senharepAviso");
    var mensagens =[];
    // Limpa o aviso anterior
    avisoElement.innerHTML = "";

    if (nome === "" || email === "" || senha === "" || repitasenha === "" || 
        cpf === "" || telefone === "" || nascimento === "" || genero === '' ){
        rolarAteCampo("typeNome");
        return false;
    }
    else if( tipoResidencia === "" || tipoLogradouro === "" ||  logradouro === "" ||  
    numero === "" ||  bairro === "" || cidade === "" ||cep === "" ||pais === "" ||  
     estado === "" ||  tipoTelefone === "" ){
        rolarAteCampo("typeTipoResidencia");
        return false;
    }
    
   
    if (senha.length < 8) {
        mensagens.push("Mínino 8 caracteres.");
    }
    // Verifica se a senha contém pelo menos uma letra maiúscula
    if (!/[A-Z]/.test(senha)) {
        mensagens.push("Uma letra maiúscula.");
    }
    // Verifica se a senha contém pelo menos uma letra minúscula
    if (!/[a-z]/.test(senha)) {
        mensagens.push("Uma letra minúscula.");
    }
    // Verifica se a senha contém pelo menos um caractere especial
    if (!/[\W_]/.test(senha)) {
        mensagens.push("Um caractere especial.");
    }
    if(mensagens.length >0){
        avisoElement.innerHTML="A senha deve conter: "+ mensagens.join("<br> ");
        if(senha != repitasenha){
         avisoElementRep.innerHTML = "As senhas não são iguais!";
        }
        window.scrollTo(0, 0); // Isso rolará a tela para o topo
        return false;
    } 
    if(mensagens.length === 0 && senha === repitasenha){
		document.forms["frmcliente"].submit();
        return true;
        
        
    }
   
}



//  function validarSenha() {
// 	 
//         var senha = document.getElementById("typeSenha").value;
//         var repitasenha = document.getElementById("typeRepitaSenha").value;
// 		var avisoElement = document.getElementById("senhaAviso");
// 		var avisoElementRep = document.getElementById("senharepAviso");
// 		var mensagens =[];
//         Limpa o aviso anterior
//         avisoElement.innerHTML = "";
		
//         Verifica se a senha tem pelo menos 8 caracteres
//         if (senha.length < 8) {
//             mensagens.push("Mímino 8 caracteres.");
     
//         }
//         Verifica se a senha contém pelo menos uma letra maiúscula
//     	if (!/[A-Z]/.test(senha)) {
//             mensagens.push("Uma letra maiúscula.");
// 		}
//         Verifica se a senha contém pelo menos uma letra minúscula
//         if (!/[a-z]/.test(senha)) {
//             mensagens.push("Uma letra minúscula.");
//         }
//         Verifica se a senha contém pelo menos um caractere especial
//         if (!/[\W_]/.test(senha)) {
//             mensagens.push("Um caractere especial.");
//         }
//         if(mensagens.length >0){
// 			avisoElement.innerHTML="A senha deve conter: "+ mensagens.join("<br> ");
// 			if(senha != repitasenha){
// 			 avisoElementRep.innerHTML = "As senhas não são iguais!";
// 			}
// 			window.scrollTo(0, 0); // Isso rolará a tela para o topo
// 			return false;
// 		}
// 		if(mensagens.length === 0 && senha === repitasenha){
// 			return true;
// 		}
		
	
//     }

function validarLogin(){
	let email = document.getElementById("typeEmailX-2").value;
	let senha = document.getElementById("typePasswordX-2").value;

	if(email === ""){
		alert('preencha o campo email');
	}
	else if(senha === ""){
		alert('preencha o campo de senha');
	}
	else{
		alert('tudo certo');
	}
}
