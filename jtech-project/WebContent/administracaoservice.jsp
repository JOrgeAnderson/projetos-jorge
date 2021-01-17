<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<script src="resources/javascript/jquerycep.js"></script>

<meta charset="ISO-8859-1">
<title>Tela Principal</title>
<link rel="stylesheet" href="resources/css/formcond.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
            
</head>
<body style="background-color: gray;">

<div class="fixed-top">
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
      <h5 class="text-white h4">JTECH SYSTEM</h5><br/>
      <a href="cadastrouser.jsp"><span class="text-white h7">Cadastrar Usuario</span></a><br/>
      <a href="userServlet?acao=listarusuarios"><span class="text-white h7">Listar Usuarios</span></a><br/>
      <a href="login.jsp?acao=sair"><span class="text-white h7">Sair</span></a><br/>
    </div>
  </div>
  <nav class="navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </nav>
</div>
	<!-- <div class="card">
	<h2>TESTE SISTEMA DE CONDOMINIO</h2>
	<span class="column1">
	<a href="cadastrouser.jsp">
	<img alt="Cadastrar Usuário" title="Cadastrar Usuario" src="resources/img/adduser.png" width="100px" height="=100px">
	</a>
	</span>
	<span class="column2">
	<img alt="Cadastrar Condominio" title="Cadastrar Condominio" src="resources/img/addcondominio.png" width="100px" height="=100px">
	</span>
	</div> -->
	<div class="container">
	<ul class="form-style-1">
			<li>
			<h3>Cadastro de Condominios</h3>
			<h3 style="color: red;">${msg}</h3>
			<form action="condominioServlet" method="post">
				<table>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" placeholder="Informe o nome" maxlength="100"></td>
						
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua" placeholder="Informe o rua" maxlength="50" readonly="readonly"></td>
					</tr>

					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone" placeholder="Informe o telefone" maxlength="11"></td>
						
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro" placeholder="Informe o bairro" maxlength="50" readonly="readonly"></td>

					</tr>

					<tr>
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep();" placeholder="Informe o cep"></td>
							
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade" placeholder="Informe a cidade" maxlength="50" readonly="readonly"></td>
						
					</tr>

					<tr>
							<td>Estado:</td>
						<td><input type="text" id="estado" name="estado" placeholder="Informe o Estado"maxlength="50" readonly="readonly"></td>
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
	
	<table class="table table-hover table-dark" style=" transform: translate(0, 30%);">
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
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
				
        	
        	if (!("erro" in dados)) {
                
        		$("#rua").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#cidade").val(dados.localidade);
                $("#estado").val(dados.uf);
        		
            } //end if.
            else {
                //CEP pesquisado não foi encontrado.
                alert("CEP não encontrado.");
            }
        });
            
    }
	
</script>

<!-- <script src="resources/javascript/slim.min.js"></script> -->
<script src="resources/javascript/popper.min.js"></script>
<script src="resources/javascript/bootstrap.min.js"></script>
</body>

</html>