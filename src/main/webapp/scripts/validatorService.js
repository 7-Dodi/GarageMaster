/**
 * 
 */
const buttonFun = document.querySelector("#finalizar");
 
 //Validação do formulário de inserção de funcionario
 buttonFun.addEventListener("click", ()=>{
	 let descricao = formAddService.descricao.value;
	 let valor = formAddService.valor.value;
	 let veiculo = formAddService.idVeiculo.value;
	 let funcionario = formAddService.idFuncionario.value;
	 
	 if(descricao === "" || valor === "" || veiculo === "" || funcionario === ""){
		 alert("Preencha os campos corretamente");
		 return false;
	 }else{
		 document.forms['formAddService'].submit();
	 }
 });