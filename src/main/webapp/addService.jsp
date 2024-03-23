<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="br.com.GarageMaster.entities.Veiculo" %>
<%@ page import="br.com.GarageMaster.entities.Funcionario" %>
<%@ page import ="java.util.List"%>
<%
	List<Veiculo> veiculos = (List<Veiculo>) request.getAttribute("veiculos");
	List<Funcionario> funcionarios = (List<Funcionario>) request.getAttribute("funcionarios");
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
	.main input, #idVeiculo, #idFuncionario{
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
</style>

<body>
	<div class="main">
		<h1>Adicionar novo Serviço</h1>
		<form name="formAddService" action="addService" method="POST">
			<input type="text" name="descricao" placeholder="Descrição do Serviço"/><br /> 
			<input type="text" name="valor" placeholder="Valor do Serviço"/><br /> 

			<!-- Select de veiculos -->
			<select id="idVeiculo" name="idVeiculo">
            <option value="">Selecione o Veículo</option>
            <% for (Veiculo veiculo : veiculos) { %>
                <option value="<%= veiculo.getId() %>">
                    <%= veiculo.getModelo() %> (ID: <%= veiculo.getId() %>)
                </option>
            <% } %>
	        </select><br/>
        
			<!--Select de funcionarios -->
	        <select id="idFuncionario" name="idFuncionario">
	            <option value="">Selecione o Funcionário</option>
	            <% for (Funcionario funcionario : funcionarios) { %>
	                <option value="<%= funcionario.getId() %>">
	                    <%= funcionario.getNome() %> (ID: <%= funcionario.getId() %>)
	                </option>
	            <% } %>
	        </select><br/>
			<input id="finalizar" type="button" value="Finalizar"/>
		</form>
	</div>
</body>
<script src="./scripts/validatorService.js"></script>
</html>