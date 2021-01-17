<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Usuários</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body style="background-color: gray;">

<a href="condominioServlet?acao=listarcond"><button type="button" class="btn btn-warning">Voltar</button></a>

<table class="table table-hover table-dark" style="transform: translate(0, 10%);">
  <thead>
    <tr>
      <th scope="col">código</th>
      <th scope="col">nome</th>
      <th scope="col">login</th>
      <th scope="col">e-mail</th>
      <th scope="col">Categoria</th>
      <th scope="col">Editar</th>
      <th scope="col">Excluir</th>
    </tr>
  </thead>
  <tbody>
  	
  	<c:forEach items="${usuarios}" var="user">
    <tr>
      <th scope="row"><c:out value="${user.id}"></c:out></th>
      <td><c:out value="${user.nome}"></c:out></td>
      <td><c:out value="${user.login}"></c:out></td>
      <td><c:out value="${user.email}"></c:out></td>
      <td><c:out value="${user.categoria}"></c:out></td>
      <td><a href="userServlet?acao=editaruser&user=${user.id}"><img alt="Editar dados" src="resources/img/editadados.jpg" width="28px" height="28px"></a></td>
      <td><a href="userServlet?acao=excluir&user=${user.id}"><img alt="Excluir dados" src="resources/img/excluir.png" width="28px" height="28px" onclick="return confirm('Deseja Excluir o usuário ${user.login}?')"></a></td>
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

<script src="resources/javascript/slim.min.js"></script>
<script src="resources/javascript/popper.min.js"></script>
<script src="resources/javascript/bootstrap.min.js"></script>
</body>
</html>