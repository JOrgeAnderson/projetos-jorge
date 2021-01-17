<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela Principal</title>
<link rel="stylesheet" href="resources/css/formcond.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
</head>
<body style="background-color: gray;">
	
	<div class="btn-toolbar mb-3" role="toolbar" aria-label="Toolbar with button groups">
  <div class="btn-group mr-2" role="group" aria-label="First group">
    <a href="login.jsp?acao=sair"><button type="button" class="btn btn-outline-warning">Sair</button></a>
  </div>
  </div>	
	<div class="container">
	<ul class="form-style-1">
			<li>
			<h3 style="color: red;">${msg}</h3>
			<form action="condominioServlet" method="post">
				<table>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" placeholder="Informe o nome" maxlength="100"></td>
						
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua" placeholder="Informe o rua" maxlength="50"></td>
					</tr>

					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone" placeholder="Informe o telefone" maxlength="11"></td>
						
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro" placeholder="Informe o bairro" maxlength="50"></td>

					</tr>

					<tr>
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep();" placeholder="Informe um cep válido" maxlength="9"></td>
							
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade" placeholder="Informe a cidade" maxlength="50"></td>
						
					</tr>

					<tr>
							<td>Estado:</td>
						<td><input type="text" id="estado" name="estado" placeholder="Informe o Estado"maxlength="50"></td>
					</tr>
						
						
						<tr>
						<td></td>
						<td>
						<br>
						<input type="submit" class="btn btn-outline-warning" value="Salvar"/>
						</td>
						</tr>
				</table>
				</form>
			</li>
		</ul>
		</div>
	
	<table class="table table-hover table-dark" style=" transform: translate(0, 50%);">
  <thead>
    <tr>
      <th scope="col">código</th>
      <th scope="col">nome</th>
      <th scope="col">Logradouro</th>
      <th scope="col">telefone</th>
      <th scope="col">Valor</th>
    </tr>
  </thead>
  <tbody>
  	
  	<c:forEach items="${condominios}" var="cond">
    <tr>
      <th scope="row"><c:out value="${cond.id}"></c:out></th>
      <td><c:out value="${cond.nome}"></c:out></td>
      <td><c:out value="${cond.rua}"></c:out></td>
      <td><c:out value="${cond.telefone}"></c:out></td>
      <td>777</td>
    </tr>
    <!-- <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td colspan="2">Larry the Bird</td>
      <td>@twitter</td>
    </tr> -->
    </c:forEach>
  </tbody>
</table>

<script type="text/javascript">
function consultaCep() {
	var cep = $("#cep").val();

	//Consulta o webservice viacep.com.br/
	$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {

				if (!("erro" in dados)) {
					//Atualiza os campos com os valores da consulta.
					$("#rua").val(dados.logradouro);
					$("#bairro").val(dados.bairro);
					$("#cidade").val(dados.localidade);
					$("#estado").val(dados.uf);
				} //end if.
				else {
					$("#cep").val('');
					$("#rua").val('');
					$("#bairro").val('');
					$("#cidade").val('');
					$("#estado").val('');
					alert("CEP não encontrado.");
				}
			});
}
</script>
<!-- <script src="resources/javascript/jquerycep.js"></script> -->
<script src="resources/javascript/slim.min.js"></script>
<script src="resources/javascript/popper.min.js"></script>
<script src="resources/javascript/bootstrap.min.js"></script>
</body>
</html>