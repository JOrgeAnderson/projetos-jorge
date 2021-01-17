<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<h1>Página pai load jquery</h1>
<input type="button" value="Carregar página" onclick="carregar();">

<div id="mostrarpaginafilha"></div> <!-- Local de carregameto da página -->
</body>
<script type="text/javascript">

function carregar() {
	$("#mostrarpaginafilha").load('paginafilha.jsp');//load página em jQuery
}

</script>
</html>