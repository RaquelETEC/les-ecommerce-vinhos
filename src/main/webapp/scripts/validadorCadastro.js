function validadorCadastro() {
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
   
    if (nome === "") {
		//alert('Preencha o campo Nome')
		//document.getElementById("typeNome").focus(); //faz o campo dicar com uma borda azulzinho 
		window.scrollTo(0, 0); // Isso rolará a tela para o topo
		return false
	} else if (email === "") {
		//alert('Preencha o campo email')
		//document.getElementById("typeEmail").focus();
		//window.scrollTo(0, 0); 
		return false
	// }else if (senha === "") {
	// 	alert('Preencha o campo senha')
	// 	document.getElementById("typeSenha").focus();
	// 	window.scrollTo(0, 0); 
	// 	return false
	// }else if (repitasenha === "") {
	// 	alert('Preencha o campo repita a senha')
	// 	document.getElementById("typeRepitaSenha").focus();
	// 	window.scrollTo(0, 0); 
	// 	return false
	// }else if (cpf === "") {
	// 	alert('Preencha o campo CPF')
	// 	document.getElementById("typeCPF").focus();
	// 	window.scrollTo(0, 0); 
	// 	return false
	// } else if (telefone === "") {
	// 	alert('Preencha o campo telefone')
	// 	document.getElementById("typeNumeroTelefone").focus();
	// 	window.scrollTo(0, 0); 
	// 	return false
	// } else if (nascimento === "") {
	// 	alert('Preencha o campo Data de nascimento')
	// 	document.getElementById("typeNascimento").focus();
	// 	window.scrollTo(0, 0); 
	// 	return false
	// } else if (genero === '') {
	// 	alert('Preencha o campo gênero')
	// //	document.getElementById("typeGenero").focus();
	// 	window.scrollTo(0, 0); 
	// 	return false
	// } else if (tipoResidencia === "") {
	// 	alert('Preencha o campo tipo de residencia')
	// 	document.getElementById("typeTipoResidencia").focus();
	// 	return false
	// } else if (tipoLogradouro === "") {
	// 	alert('Preencha o campo tipo de Logradouro')
	// 	document.getElementById("typeTipoLogradouro").focus();
	// 	return false
	// } else if (logradouro === "") {
	// 	alert('Preencha o campo logradouro')
	// 	document.getElementById("typeLogradouro").focus();
	// 	return false
	// } else if (numero === "") {
	// 	alert('Preencha o campo numero')
	// 	document.getElementById("typeNumero").focus();
	// 	return false
	// } else if (bairro === "") {
	// 	alert('Preencha o campo bairro')
	// 	document.getElementById("typeBairro").focus();
	// 	return false
	// } else if (cidade === "") {
	// 	alert('Preencha o campo cidade')
	// 	document.getElementById("typeCidade").focus();
	// 	return false
	// } else if (cep === "") {
	// 	alert('Preencha o campo cep')
	// 	document.getElementById("typeCep").focus();
	// 	return false;
	// } else if (pais === "") {
	// 	alert('Preencha o campo pais')
	// 	document.getElementById("typePais").focus();
	// 	return false;
	// 
}
	else if(validarSenha()){
		alert("Cadastro com sucesso");
		document.forms["frmcliente"].submit();
		return true;
	}
}

 function validarSenha() {
	 debugger;
        var senha = document.getElementById("typeSenha").value;
        var repitasenha = document.getElementById("typeRepitaSenha").value;
		var avisoElement = document.getElementById("senhaAviso");
		var avisoElementRep = document.getElementById("senharepAviso");
		var mensagens =[];
        // Limpa o aviso anterior
        avisoElement.innerHTML = "";
		avisoElementRep = "";
		
        // Verifica se a senha tem pelo menos 8 caracteres
        if (senha.length < 8) {
            mensagens.push("Mímino 8 caracteres.");
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
		if(senha != repitasenha){
			 avisoElementRep.innerHTML = "as senhas não são iguais!";
			 return false;
		}
		if(mensagens.length >0){
			avisoElement.innerHTML="A senha deve conter: "+ mensagens.join(",<br> ");
			return false;
		}
		else{
			 // Se todas as condições forem atendidas, a senha é válida
        	return true;
		}
       
    }

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