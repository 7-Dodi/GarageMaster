<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.com.GarageMaster.entities.RelationWithPeca" %>
<%@ page import ="java.util.List"%>
<%
	List<RelationWithPeca> pecas = (List<RelationWithPeca>) request.getAttribute("pecas");
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
	margin-bottom: 40px;
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
.none{
	display: none;
}
</style>

<body>
	<div class="main">
		<h1>Adicionar Peças (Venda: ${param.idVenda})</h1>
		<%if(pecas.size() > 0){ %>
		<div id="tableContainer">
			<table id="tabela">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>Descricao</th>
						<th>Valor</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<% for(int k=0; k < pecas.size(); k++){%>
					<tr>
						<td><%=pecas.get(k).getIdPeca()%></td>
						<td><%=pecas.get(k).getNomePeca()%></td>
						<td><%=pecas.get(k).getDescricaoPeca()%></td>
						<td><%=pecas.get(k).getValorPeca()%></td>
						<td class="buttonsTd">
							<a href="javascript: confirmar(<%=pecas.get(k).getIdPeca()%>, ${param.idVenda})"><button>Adicionar</button></a>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
		<%} else { %>
			<h2>Não há mais peças para adicionar</h2>
		<%} %>
		<div class="buttons">
			<a href="./index.html"><button>Home Page</button></a>
			<a href="venda"><button>Vendas</button></a>
		</div>
	</div>
</body>
<script src="./scripts/confirmadorVendaRelation.js"></script>
</html>