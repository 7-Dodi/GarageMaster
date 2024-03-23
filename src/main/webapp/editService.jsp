<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="br.com.GarageMaster.entities.RelationWithPeca" %>
<%@ page import ="java.util.List"%>
<%
	List<RelationWithPeca> listaPeca = (List<RelationWithPeca>) request.getAttribute("listaPeca");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Garage Master</title>
<link rel="icon" href="./images/carro.png" type="image/png">
</head>

<style>
	*{
		margin: 0;
		box-sizing: border-box;
		font-family: system-ui;
	}
	
	.main {
	    position: fixed;
	    top: 50%;
	    left: 50%;
	    transform: translate(-50%, -50%);
	    padding:10px;   
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    justify-content: space-between;
	}
	.main h1{
		font-size: 50px;
	}
	.main form{
		width: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.main input{
		width: 400px;
	    height: 35px;
	    margin-bottom: 5px;
	    border-radius: 5px;
	    border: 1px solid #21E;
	    padding: 5px;
	    font-size: 14px;
	    cursor: pointer;
	}
	
	#finalizar {
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
.product{
	background-color: #fff;
	color: blue;
	width: 110px;
	height: 40px;
	border: 2px solid blue;
	border-radius: 10px;
	transition: all 0.5s;
	font-weight: 530;
	cursor: pointer;
}

.remover {
	background-color: red;
}
</style>

<body>
	<div class="main">
		<h1>Editando um serviço</h1>
		<form name="formAddService" action="updateService">
			<input type="text" name="id" readonly="readonly" value="<% out.print(request.getAttribute("id"));%>"/><br />
			<input type="text" name="idVeiculo" readonly="readonly" value="<% out.print(request.getAttribute("idVeiculo"));%>"/><br />
			<input type="text" name="idFuncionario" readonly="readonly" value="<% out.print(request.getAttribute("idFuncionario"));%>"/><br />
			<input type="text" name="data" readonly="readonly" value="<% out.print(request.getAttribute("data"));%>"/><br />
			<input type="text" name="descricao" value="<% out.print(request.getAttribute("descricao"));%>"/><br />
			<input type="text" name="valor" value="<% out.print(request.getAttribute("valor"));%>"/><br />  
			<input id="finalizar" type="button" value="Salvar"/>
		</form>
			<% if(listaPeca.size() > 0) {%>
			<div id="tableContainer">
			<table id="tabela">
				<thead>
					<tr>
						<th>Id</th>
						<th>Peca</th>
						<th>Quantidade</th>
						<th>Opções</th>
					</tr>
				</thead>
				<tbody>
					<% for(int k=0; k < listaPeca.size(); k++){%>
					<tr>
						<td><%=listaPeca.get(k).getIdServico()%></td>
						<td>
							<span> Id: </span><%=listaPeca.get(k).getIdPeca()%><br>
							<span> Peça: </span><%=listaPeca.get(k).getNomePeca()%>
						</td>
						<td><%=listaPeca.get(k).getQuantidade()%></td>
						<td class="buttonsTd">
							<a><button class="remover" onclick="confirmar(<%=listaPeca.get(k).getIdPeca()%>, <%=listaPeca.get(k).getIdServico()%>)">Remover</button></a>
							<a href="relationService?idService=<%=listaPeca.get(k).getIdServico()%>"><button class="product">Produtos</button></a>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
		<% }%>
	</div>
</body>
<script src="./scripts/validatorService.js"></script>
<script>
	//Validação da deleção de uma peça de um serviço
	function confirmar(idPeca, idServico){
		 let resposta = confirm("Confirmar a exclusão dessa peça nesse serviço?" );
		 if(resposta === true){
			 window.location.href= "deleteRelationService?idService=" + idServico + "&idPeca=" + idPeca;
		 }
	}
</script>
</html>