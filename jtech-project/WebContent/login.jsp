<%@page import="dao.DaoLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
   <!--Made with love by Mutiullah Samim -->
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="resources/css/login.css">

<meta charset="ISO-8859-1">
	<title>Login</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Login</h3>
				<!-- <div class="d-flex justify-content-end social_icon">
					<span><a href="https://pt-br.facebook.com"><i class="fab fa-facebook-square"></i></a></span>
					<span><a href="//accounts.google.com/Login"><i class="fab fa-google-plus-square"></i></a></span>
					<span><a href="https://twitter.com/login"><i class="fab fa-twitter-square"></i></a></span>
				</div> -->
			</div>
			<div class="card-body">
				<form action="verificaLogin" method="post" onsubmit="return validarCampo() ? true : false">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control" id="usuario" name="usuario" placeholder="usuário ou e-mail">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" class="form-control" id="senha" name="senha" placeholder="informe a senha">
					</div>
					<!-- <div class="row align-items-center remember">
						<input type="checkbox">Lembre-me
					</div> -->
					<div class="form-group">
						<br/>
						<input type="submit" class="login_btn" value="Entrar">
					</div>
				</form>
			</div>
			<!-- <div class="card-footer">
				<div class="d-flex justify-content-center links">
					Não tem uma conta?<a href="#">Cadastre-se</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="#">Esqueceu sua senha?</a>
				</div>
			</div> -->
		</div>
	</div>
</div>
</body>

<script type="text/javascript">
	function validarCampo() {
		
		if(document.getElementById("usuario").value == ''){
			alert('Informe um Usuário/E-mail');
			return false;
		}else if(document.getElementById("senha").value == ''){
			alert('Campo senha vazio.')
			return false;
		}
		
		return true;
	}
</script>

</html>