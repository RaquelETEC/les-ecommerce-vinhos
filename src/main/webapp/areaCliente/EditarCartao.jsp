<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login e Cadastro</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
<script src="scripts/validadorCadastrov2.js"></script>
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
	<nav class="navbar navbar-expand-sm py-3 sticky-top"
		style="background: black;">
		<div class="container-fluid">
			<a href="index.html"><img src="imagens/logo-vinho.PNG"
				alt="Logo Vinho" style="width: 200px;" class="mx-3"></a>
			<div class="navbar-nav ms-auto"></div>
		</div>
	</nav>
	<div class="container py-5">
		<div
			class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-12 col-md-8 col-lg-6 col-xl-8">
				<div class="card shadow-2-strong">
					<div class="card-body p-5 ">
						<h3 class="mb-4 c text-center">Cadastro de Cartão</h3>
						<!-- Seu formulÃ¡rio de cadastro aqui -->
                         <form name="frmcartao" action="insertCartao" onsubmit="return validarCadastro()">
							<fieldset>
								<legend>Cartão</legend>
								<div class="row">
									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoNome">Nome
											impresso do cartão</label> <input type="text" name="CartaoNome"
											id="CartaoNome" class="form-control form-control-lg" required
											value=<%= request.getAttribute("nome")%> />
									</div>

									<div class="col-md-6 mb-4">
										<label class="form-label" for="tipoBandeira">Bandeira</label>
										<select class="form-select form-select-lg" name="tipoBandeira"
											id="tipoBandeira" required>
											<option value="1" <%= "1".equals(request.getAttribute("tiposbandeira")) ? "selected" : "" %>>Mastercard</option>
											<option value="2" <%= "2".equals(request.getAttribute("tiposbandeira")) ? "selected" : "" %>>Visa</option>
										</select>
									</div>
								</div>
								<div class="row">

									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoNumero">N° *</label> <input
											type="text" name="CartaoNumero" id="CartaoNumero"
											class="form-control form-control-lg" required  value=<%= request.getAttribute("numero")%> />
									</div>

									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoCodigo">Codigo de Segurança</label> <input type="text" name="CartaoCodigo"
											id="CartaoCodigo" class="form-control form-control-lg"
											required value=<%= request.getAttribute("Codigo")%>  />
									</div>
								</div>

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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>