<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload files</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<form enctype="application/x-www-form-urlencoded">
		<input type="file" id="file" name="file" onchange="uploadFile();" />
		<img alt="Imagem" src="" id="target" width="200" height="200">
	</form>
	<br />
	<br />
	<br />
	<br />
	<a href="fileUpload?acao=carregar">Carregar imagens</a>
	
	<form action="fileUpload" method="get">
	<table>
		<c:forEach items="${listaUserImagem}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>
				<td><a href="fileUpload?acao=download&iduser=${user.id}">Download Arquivo</a></td>
			</tr>
		</c:forEach>
	
	</table>
	</form>
	<br />
	<br />
	<br />
	<br />
</body>

<script type="text/javascript">
	function uploadFile() {

		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];

		var reader = new FileReader();

		reader.onloadend = function() {
			target.src = reader.result;

			/////-----Upload Ajax -----
			$.ajax({
				method : "POST",
				url : "fileUpload",
				data : {
					fileUpload : reader.result
				}

			}).done(function(response) {
				alert("Sucesso: " + response);

			}).fail(function(xhr, status, errorThrown) {
				alert("Error: " + xhr.responseText);

			});
		};

		if (file) {

			reader.readAsDataURL(file);
		} else {
			target.src = "";

		}
	}
</script>

</html>