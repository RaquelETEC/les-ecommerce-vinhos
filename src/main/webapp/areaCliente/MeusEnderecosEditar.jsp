<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.entity.Endereco"%>

<%
    Endereco endereco = (Endereco) request.getAttribute("endereco");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    
    <title>Adicionar novo Endereco</title>
    <link rel="icon" href="imagens/favicon.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
    <style>
        .card {
            border-radius: 1rem;
        }

        body {
            background-color: #F2F2F2;
        }
    </style>

</head>

<body>
    <nav class="navbar navbar-expand-sm py-3 sticky-top" style="background: black;">
        <div class="container-fluid">
            <a href="index.html"><img src="../imagens/logo-vinho.PNG" alt="Logo Vinho" style="width: 200px;" class="mx-3"></a>
            <div class="navbar-nav ms-auto"></div>
        </div>
    </nav>
        <div class="container py-5">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-8">
                    <div class="card shadow-2-strong">
                        <div class="card-body p-5 ">
                            <h3 class="mb-4 c text-center">Editar Endereço</h3>
                            <!-- Seu formulÃ¡rio de cadastro aqui -->
                             <form name="frmEnderecoEditar" action="EditarEndereco" >
                                <fieldset>                                               
                                <input type="text" name="id" id="id" style="display:none"/>
                                 <input type="text" name="idEnd" id="idEnd"style="display:none"/>
                                
                                    <div class="row">
                                     <div class="col-md-12 mb-4">
                                            <label class="form-label" for="nome">Nome*</label>
                                            <input type="text" name="nome" id="nome" class="form-control form-control-lg" value="<%= endereco.getNome() %>" required/>
                                               
                                        </div>
                                       <div class="col-md-6 mb-4">
										    <label class="form-label" for="tipoResidencia">Tipo de Residencia *</label>
										    <select class="form-select form-select-lg" name="typeTipoResidencia" id="typeTipoResidencia" required>
										        <option value="casa" <%= "casa".equals(endereco.getTipoResidencia()) ? "selected" : "" %>>Casa</option>
										        <option value="apartamento" <%= "apartamento".equals(endereco.getTipoResidencia()) ? "selected" : "" %>>Apartamento</option>
										        <option value="condominio" <%= "condominio".equals(endereco.getTipoResidencia()) ? "selected" : "" %>>Condomínio</option>
										    </select>
										</div>
                                       <div class="col-md-6 mb-4">
										    <label class="form-label" for="tipoLogradouro">Tipo Logradouro*</label>
										    <select class="form-select form-select-lg" name="typeTipoLogradouro" id="typeTipoLogradouro" required>
										        <option value="" disabled></option>
										        <option value="Rua" <%= "Rua".equals(endereco.getTipoLogradouro()) ? "selected" : "" %>>Rua</option>
										        <option value="Estrada" <%= "Estrada".equals(endereco.getTipoLogradouro()) ? "selected" : "" %>>Estrada</option>
										        <option value="Avenida" <%= "Avenida".equals(endereco.getTipoLogradouro()) ? "selected" : "" %>>Avenida</option>
										        <option value="Praça" <%= "Praça".equals(endereco.getTipoLogradouro()) ? "selected" : "" %>>Praça</option>
										        <option value="Corredor" <%= "Corredor".equals(endereco.getTipoLogradouro()) ? "selected" : "" %>>Corredor</option>
										        <option value="Alameda" <%= "Alameda".equals(endereco.getTipoLogradouro()) ? "selected" : "" %>>Alameda</option>
										        <option value="Distrito" <%= "Distrito".equals(endereco.getTipoLogradouro()) ? "selected" : "" %>>Distrito</option>
										    </select>
										</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="logradouro">Logradouro *</label>
                                            <input type="text" name="typeLogradouro" id="typeLogradouro" class="form-control form-control-lg" value="<%= endereco.getLogradouro() %>" required/>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="numero">Número</label>
                                            <input type="text" name="typeNumero" id="typeNumero" class="form-control form-control-lg" value="<%= endereco.getNumero() %>" required/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="typeBairro">Bairro*</label>
                                            <input type="text" name="typeBairro" id="typeBairro" class="form-control form-control-lg" value="<%= endereco.getBairro() %>"  required/>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="typeCidade">Cidade*</label>
                                            <input type="text" name="typeCidade" id="typeCidade" class="form-control form-control-lg" value="<%= endereco.getCidade() %>" required />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="typeEstado">Estado*</label>
                                            <input type="text" name="typeEstado" id="typeEstado" class="form-control form-control-lg"  value="<%= endereco.getEstado() %>" required/>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="typeCep">CEP*</label>
                                            <input type="text" name="typeCep" id="typeCep" class="form-control form-control-lg"  value="<%= endereco.getCep() %>" required/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="typePais">País*</label>
                                            <input type="text" name="typePais" id="typePais" class="form-control form-control-lg" value="<%= endereco.getPais() %>" required />
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="observacoes">Observações</label>
                                            <input type="text" name="observacoes" id="observacoes" class="form-control form-control-lg"  value="<%= endereco.getObservacao() %>" />
                                        </div>
                                    </div>       
 									<div class="d-flex justify-content-center">                            
                                   		<p> Tipo Endereço </p>
                                   </div>
                                    <div class="d-flex justify-content-center">
	                                    <div class="form-check form-check-inline">
	                                      <input class="form-check-input" type="radio" name="tipoEndereco" id="ENTREGA" value="ENTREGA" <%= "ENTREGA".equals(endereco.getTipos().name()) ? "checked" : "" %>>
										  <label class="form-check-label" for="entrega">Entrega</label>
										</div>
										<div class="form-check form-check-inline">
										  <input class="form-check-input" type="radio" name="tipoEndereco" id="RESIDENCIAL" value="RESIDENCIAL" <%= "RESIDENCIAL".equals(endereco.getTipos().name()) ? "checked" : "" %>>
										  <label class="form-check-label" for="residencial">Residencial</label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="tipoEndereco" id="COBRANCA" value="COBRANCA" <%= "COBRANCA".equals(endereco.getTipos().name()) ? "checked" : "" %>>
										  <label class="form-check-label" for="cobranca">Cobrança</label>
										</div>
									</div>
									<br>
                                </fieldset>
                                
                                <div class="text-center">
                                   	<input class="btn btn-primary btn-lg btn-block" type="submit" value="Salvar"> 
                                    <hr class="my-4">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <footer class="p-4 text-light text-center " style="background: black;">
        Desenvolvido por Caynan e Raquel
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
		<script>
		 // Captura o ID passado pela URL
	    var urlParams = new URLSearchParams(window.location.search);
	    var id = urlParams.get('id');
	    var idEnd = urlParams.get('idEnd');

	    // Atribui o valor do ID ao campo de entrada
	    document.getElementById('id').value = id;
	    document.getElementById('idEnd').value = idEnd;

		</script>
</body>

</html>