<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	
	#funButton {
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
		<h1>Editando um fundionario</h1>
		<form name="formAddFuncionario" action="updateFuncionario">
			<input type="text" name="id" readonly="readonly" value="<% out.print(request.getAttribute("id"));%>"/><br />
			<input type="text" name="nome" value="<% out.print(request.getAttribute("nome"));%>"/><br /> 
			<input type="text" name="cpf" value="<% out.print(request.getAttribute("cpf"));%>"/><br />
			<input type="text" name="endereco" value="<% out.print(request.getAttribute("endereco"));%>"/><br /> 
			<input type="text" name="cargo" value="<% out.print(request.getAttribute("cargo"));%>"/><br /> 
			<input type="text" name="matricula" value="<% out.print(request.getAttribute("matricula"));%>"/><br /> 
			<input type="text" name="senha" value="<% out.print(request.getAttribute("senha"));%>"/><br /> 
			<input id="funButton" type="button" value="Salvar"/>
		</form>
	</div>
</body>
<script src="./scripts/validatorFuncionario.js"></script>
</html>