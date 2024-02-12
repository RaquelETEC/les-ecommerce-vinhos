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
   
    if (nome === "" || email === "" || senha === "" || repitasenha === "" || cpf === "" || telefone === "" || nascimento === "" || genero === '') {
		//alert('Preencha o campo Nome')
		//document.getElementById("typeNome").focus(); //faz o campo dicar com uma borda azulzinho 
		window.scrollTo(0, 0); // Isso rolará a tela para o topo
		return false
}
	else //if(validarSenha()){
		{
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
		
        return true;
		
       
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