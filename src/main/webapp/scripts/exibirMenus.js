// funcoes de exibir os botoes no container do perfil

const exibirMinhaConta = () => {
    let minhaConta = document.querySelector(".MinhaConta");

    if (minhaConta.classList.contains("exibir")) {
        minhaConta.classList.remove("exibir");
    } else {
        minhaConta.classList.add("exibir");
    }
}

function validarAtualizar(){
	debugger;
	document.forms["form-register"].submit();
    return true;
}