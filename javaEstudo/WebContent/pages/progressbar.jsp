<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Barra de progresso</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
#myProgress { /* fundo da barra de progresso */
	width: 100%;
	background-color: #ddd; /* fundo da barra de progresso -cor cinza- */
}

#myBar { /* Cor da barra de progresso */
	width: 1%;
	height: 30px;
	background-color: #4CAF50;
}

.ui-progressbar {
	position: relative;
}

.progress-label {
	position: relative;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1px 1px 0px #fff;
}
</style>
</head>
<body>
	<h1>Exemplo com java script</h1>
	<div id="myProgress">
		<!-- Fundo da barra -->
		<div id="myBar"></div>
		<!-- Barra de progresso -->
	</div>
	<br />
	<button onclick="exibirbarra()">iniciar barra de progresso</button>


	<br />
	<h1>Barra de progresso com jQuery</h1>
	<div id="progressbar">
		<div class="progress-label">Carregando...</div>

	</div>


	<script type="text/javascript">
		//script da barra de progresso por jQuery
		$(function() {
			var progressbar = $("#progressbar"), progresslabel = $(".progress-label");

			progressbar.progressbar({// cria a barra no div
				value : false,
				change : function() {
					progresslabel.text(progressbar.progressbar('value') + "%");
				},
				complete : function() {
					progresslabel.text('Completo!');
				}
			});

			function progress() {
				var val = progressbar.progressbar("value") || 0;

				progressbar.progressbar("value", val + 2);
				if (val < 99) {
					setTimeout(progress, 80);
				}
			}

			setTimeout(progress, 2000);// chamado ao abrir a tela
		});

		//script da barra de progresso por JS
		function exibirbarra() {
			var elem = document.getElementById("myBar");
			var width = 1;
			var id = setInterval(frame, 10);

			function frame() {
				if (width >= 100) {
					clearInterval(id);
				} else {
					width++;
					elem.style.width = width + "%";
				}
			}
		}
	</script>
</body>
</html>