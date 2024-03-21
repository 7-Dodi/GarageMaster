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
	.main input, #idCliente{
		width: 400px;
	    height: 35px;
	    margin-bottom: 5px;
	    border-radius: 5px;
	    border: 1px solid #21E;
	    padding: 5px;
	    font-size: 14px;
	    cursor: pointer;
	}
	
	#btnVeiculo {
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
</style>

<body>
	<div class="main">
		<h1>Adicionar novo veículo</h1>
		<form name="formAddVeiculo" action="addVeiculo" method="POST">
			<input type="text" name="modelo" placeholder="Modelo"/><br /> 
			<input type="text" name="marca" placeholder="Marca"/><br /> 
			<input type="text" name="placa" placeholder="Placa"/><br />
			<select id="idCliente" name="idCliente">
            <option value="">Selecione o Cliente</option>
            <% for (Client cliente : clientes) { %>
                <option value="<%= cliente.getId() %>">
                    <%= cliente.getNome() %> (ID: <%= cliente.getId() %>)
                </option>
            <% } %>
        </select><br/>
			<input id="btnVeiculo" type="button" value="Gravar"/>
		</form>
	</div>
</body>
<script src="./scripts/validatorVeiculo.js"></script>
</html>