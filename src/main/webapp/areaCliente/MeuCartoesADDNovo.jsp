<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login e Cadastro</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
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
			<a href="/les-ecommerce-vinhos/paginaInical.html"><img src="../imagens/logo-vinho.PNG"
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
						<form name="frmcartao" action="inserirCartao">
							<fieldset>
								<div class="box-input-form" style="display: none;">
									<div class="box-label">
										<label for="typeId">Id</label>
									</div>
									<input type="text" name="typeId" id="typeId"
										class="input-form input-width-1"
										value="<%=request.getAttribute("id")%>">

								</div>
								<legend>Cartão</legend>
								<div class="row">
									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoNome">Nome
											impresso do cartão</label> <input type="text" name="CartaoNome"
											id="CartaoNome" class="form-control form-control-lg" required />
									</div>

									<div class="col-md-6 mb-4">
										<label class="form-label" for="tipoBandeira">Bandeira</label>
										<select class="form-select form-select-lg" name="tipoBandeira"
											id="tipoBandeira" required>
											<option value="" disabled selected></option>
											<option value="1">Mastercard</option>
											<option value="2">Visa</option>
										</select>
									</div>
								</div>
								<div class="row">

									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoNumero">N° *</label> <input
											type="text" name="CartaoNumero" id="CartaoNumero"
											class="form-control form-control-lg" required />
									</div>

									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoCodigo">Codigo de
											Segurança</label> <input type="text" name="CartaoCodigo"
											id="CartaoCodigo" class="form-control form-control-lg"
											required />
									</div>

									<div class="col-md-6 mb-4">
										<label class="form-label" for="CartaoPadrao">Padrão</label>
										 <select id="CartaoPadrao" class="form-select form-select-lg" name="CartaoPadrao"required>
											<option value="" disabled selected></option>
											<option value="SIM">SIM</option>
											<option value="NAO">NAO</option>
										</select>
									</div>
								<input type="text" name="CadastrarNoPerfil" value= "SIM" required  style="display: none;"/>
								</div>

							</fieldset>
							<div class="text-center">
								<input id="BotaoCadastrar" class="btn btn-primary btn-lg btn-block" type="submit"
									value="Cadastrar">
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