<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Atualizar dados</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/cadastrouser.css">
</head>
<body>

<a href="userServlet?acao=listarusuarios"><button type="button" class="btn btn-warning">Voltar</button></a>

	<div class="card">
		<div class="container">
			<h3 style="color: red;">${msg}</h3>
			<form class="well form-horizontal" action="userServlet" method="post"
				id="formUser" onsubmit="return validarCampos()? true : false;">
				<fieldset class="fieldset">
							<h2>Atualização de Registro</h2>
	<input id="id" name="id" value="${user.id}" readonly="readonly" class="form-control" type="text" style="width: 12px; display: none;">
					<br>
					<div class="form-group">
						
						<label class="col-md-4 control-label" style="margin-left: 7%;">Nome Completo</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input id="nome" name="nome"
									value="${user.nome}" placeholder="informe o nome" class="form-control" type="text">
							</div>
						</div>
					</div>

					<!-- Text input-->

					<!-- <div class="form-group">
						<label class="col-md-4 control-label">Last Name</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input name="last_name"
									placeholder="Last Name" class="form-control" type="text">
							</div>
						</div>
					</div> -->
					
					<!-- Text input-->
					<br style="padding: 20%;">
					<div class="form-group">
						<label class="col-md-4 control-label" style="margin-left: 14%;">login</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input id="login" name="login"
									value="${user.login}" placeholder="informe o login" class="form-control" type="text">
							</div>
						</div>
					</div>
					<!-- Text input-->
					<br style="padding: 20%;">
					<div class="form-group">
						<label class="col-md-4 control-label" style="margin-left: 13%;">Senha</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input
									id="senha" name="senha" value="${user.senha}" placeholder="informe a senha"
									class="form-control" type="password">
							</div>
						</div>
					</div>

					<!-- Text input-->
					<br style="padding: 20%;">
					<div class="form-group">
						<label class="col-md-4 control-label" style="margin-left: 7%;">Confirmar Senha</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input
									id="confirmarsenha" name="confirmarsenha" value="${user.senha}" placeholder="Confirme sua senha"
									class="form-control" type="password">
							</div>
						</div>
					</div>
					<br style="padding: 20%;">
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" style="margin-left: 13%;">E-Mail</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input id="email" name="email"
									value="${user.email}" placeholder="Informe seu e-mail" class="form-control" type="text">
							</div>
						</div>
					</div>
					
					<br style="padding: 20%;">
					<div class="form-group">
						<label class="col-md-4 control-label" style="margin-left: -10%;">Categoria</label>
						<div class="col-md-4 selectContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-list"></i></span> <select id="categoria" name="categoria"
									class="form-control selectpicker" style="margin-left: -10%; height:30px;">
									<option value="">Selecione a Categora</option>
									
								<option value="Administrador"
									<%
								if(request.getAttribute("user") != null){
									
									Usuario user = (Usuario) request.getAttribute("user");
									if(user.getCategoria().equalsIgnoreCase("administrador")){
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
									
								}
							
							%>
							>Administrador</option>
							<option value="Funcionario" 
							<%
								if(request.getAttribute("user") != null){
									
									Usuario user = (Usuario) request.getAttribute("user");
									if(user.getCategoria().equalsIgnoreCase("Funcionario")){
										out.print(" ");
										out.print("selected=\"selected\"");
										out.print(" ");
									}
								}
							%>
							>Funcionario</option>
								</select>
							</div>
						</div>
					</div>

					<!-- Text input-->

					<!-- <div class="form-group">
						<label class="col-md-4 control-label">Contact No.</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input
									name="contact_no" placeholder="(639)" class="form-control"
									type="text">
							</div>
						</div>
					</div> -->

					<!-- Select Basic -->

					<!-- Success message -->
					<!-- <div class="alert alert-success" role="alert" id="success_message">
						Success <i class="glyphicon glyphicon-thumbs-up"></i> Success!.
					</div> -->

					<!-- Button -->
					<!-- <div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-4"> -->
					<br style="padding: 20%;">
						<div id="botao">
							<input type="submit" name="botao" value="Salvar"
								class="botaoEnviar" style="margin-left: 35%;"/>
						</div>
					<!-- </div>
					</div> -->

				</fieldset>
			</form>
		</div>
	</div>

<script type="text/javascript">
function validarCampos() {

	if (document.getElementById("nome").value == '') {
		alert('Campo nome vazio');
		return false;
	}

	else if (document.getElementById("login").value == '') {
		alert('Campo login vazio');
		return false;
	} else if (document.getElementById("senha").value == '') {
		alert('Campo senha vazio');
		return false;
	} else if (document.getElementById("confirmarsenha").value == '') {
		alert('Confirme sua senha');
		return false;
	} else if (document.getElementById("email").value == '') {
		alert('Campo e-mail vazio');
		return false;
	} else if (document.getElementById("categoria").value == '') {
		alert('Campo categoria vazio');
		return false;
	}else if (document.getElementById("senha").value != document.getElementById("confirmarsenha").value) {
		alert('Senhas informadas não são iguais');
		return false;
	} 

	return true;

}
	

</script>
</body>
</html>