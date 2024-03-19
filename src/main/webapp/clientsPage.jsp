<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.com.GarageMaster.entities.Client" %>
<%@ page import ="java.util.List"%>
<%
	List<Client> clientes = (List<Client>) request.getAttribute("clients");
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
</style>

<body>
	<div class="main">
		<h1>Clientes Cadastrados</h1>
		<div class="buttons">
			<a href="./addClient.html"><button>Adicionar Cliente</button></a> <a
				href="./index.html"><button>Home Page</button></a>
		</div>
		<div id="tableContainer">
			<table id="tabela">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>CPF</th>
						<th>Endereço</th>
						<th>Telefone</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<% for(int k=0; k < clientes.size(); k++){%>
					<tr>
						<td><%=clientes.get(k).getId()%></td>
						<td><%=clientes.get(k).getNome()%></td>
						<td><%=clientes.get(k).getCpf()%></td>
						<td><%=clientes.get(k).getEndereco()%></td>
						<td><%=clientes.get(k).getTelefone()%></td>
						<td class="buttonsTd">
							<a href="selectClient?idUser=<%=clientes.get(k).getId()%>"><button>Editar</button></a>
							<a href="javascript: confirmar(<%=clientes.get(k).getId()%>)"><button class="remover">Remover</button></a>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script src="./scripts/confirmador.js"></script>
</html>