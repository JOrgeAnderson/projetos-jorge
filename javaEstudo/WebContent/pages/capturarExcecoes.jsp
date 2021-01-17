<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>capturar exceções</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h3>Capturando Exceções com Jquery</h3>
		<input type="text" value="valor informado" id="txtvalor">
		<input type="button" onclick="testarExececao();" value="Testar Exceção">
		
		
</body>

<script type="text/javascript">
	function testarExececao(){
		valorInformado = $('#txtvalor').val();
		
		$.ajax({
			method: "POST",
			url: "capturarExcecao", // para qual servlet?
			data: {valorParam : valorInformado}
		})
			.done(function(response) {//resposta OK - nenhum erro
				alert("Sucesso: " + response);
				
			})
			.fail(function(xhr, status, errorThrown) {// resposta erro - algum problema ocorrreu
				alert("Error: "+ xhr.responseText); //resposta do servidor
			
			
			//Fazer algo se der errado
				
			});
		
	}

</script>

</html>