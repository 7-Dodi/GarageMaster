/**
 * 
 */
const button = document.querySelector("#button");

//Validação para o formulário de inserção de client
button.addEventListener("click", ()=>{
	 let name = formAddCliente.nome.value;
	 let cpf = formAddCliente.cpf.value;
	 let endereco = formAddCliente.endereco.value;
	 let telefone = formAddCliente.tephone.value;
	 
	 if(name === "" || cpf === "" || endereco === "" || telefone === ""){
		 alert("Preencha os campos corretamente");
		 return false;
	 }else{
		 document.forms['formAddCliente'].submit();
	 } 
 });