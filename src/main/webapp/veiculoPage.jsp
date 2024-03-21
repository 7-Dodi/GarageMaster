<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.com.GarageMaster.entities.Veiculo" %>
<%@ page import ="java.util.List"%>
<%
	List<Veiculo> veiculos = (List<Veiculo>) request.getAttribute("veiculos");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Garage Master</title>
<link rel="icon" href="./images/carro.png" type="image/png">
</head>

<style>
* {
	margin: 0;
	box-sizing: border-box;
	font-family: system-ui;
}

.main {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	padding: 10px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-between;
}
.main h1 {
	font-size: 50px;
}
.buttons{
	width: 100%;
	display: flex;
	gap: 80px;
	justify-content: center;
}
.buttonsTd{
	width: 100%;
	height: 100%;
	display: flex;
	gap: 10px;
	justify-content: center;
}

button {
	background-color: blue;
	color: #fff;
	width: 110px;
	height: 40px;
	border: none;
	border-radius: 10px;
	transition: all 0.5s;
	font-weight: 530;
	cursor: pointer;
}

.remover {
	background-color: red;
}

#tableContainer {
	margin-top: 30px;
	overflow-y: auto; /* Adiciona uma barra de rolagem vertical */
	max-height: 400px;
	/* Altura máxima da tabela antes da barra de rolagem aparecer */
}

#tabela {
	border-collapse: collapse;
	width: 100%;
}

#tabela th {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
	background-color: blue;
	color: #fff;
}

#tabela td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}
#tabela td span{
	color: #000;
	font-weight: bold;
}
</style>

<body>
	<div class="main">
		<h1>Veículos Cadastrados</h1>
		<div class="buttons">
			<a href="clientsVeiculo"><button>Adicionar Veiculo</button></a> <a
				href="./index.html"><button>Home Page</button></a>
		</div>
		<div id="tableContainer">
			<table id="tabela">
				<thead>
					<tr>
						<th>Id</th>
						<th>Modelo</th>
						<th>Placa</th>
						<th>Marca</th>
						<th>Cliente</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<% for(int k=0; k < veiculos.size(); k++){%>
					<tr>
						<td><%=veiculos.get(k).getId()%></td>
						<td><%=veiculos.get(k).getModelo()%></td>
						<td><%=veiculos.get(k).getPlaca()%></td>
						<td><%=veiculos.get(k).getMarca()%></td>
						<td>
							<span> Id: </span><%=veiculos.get(k).getIdClient()%><br>
							<span> Nome: </span><%=veiculos.get(k).getNomeClient()%>
						</td>
						<td class="buttonsTd">
							<a href="selectVeiculo?idVeiculo=<%=veiculos.get(k).getId()%>"><button>Editar</button></a>
							<a href="javascript: confirmarVeiculo(<%=veiculos.get(k).getId()%>)"><button class="remover">Remover</button></a>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script src="./scripts/confirmadorVeiculo.js"></script>
</html>