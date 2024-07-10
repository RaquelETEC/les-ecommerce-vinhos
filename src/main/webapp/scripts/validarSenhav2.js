function editarSenha() {
    var senhaAtual = document.getElementById("typeSenhaAtual").value;
    var novaSenha = document.getElementById("typeNovaSenha").value;
    var repitaSenha = document.getElementById("typeRepitaSenha").value;
    var id = document.getElementById("typeId").value;

    var respostaAjax; // Variável para armazenar a resposta da requisição AJAX

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/les-ecommerce-vinhos/editarSenha?id=" + id + "&typeSenhaAtual=" + senhaAtual + "&typeNovaSenha=" + novaSenha + "&typeRepitaSenha=" + repitaSenha, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
				
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