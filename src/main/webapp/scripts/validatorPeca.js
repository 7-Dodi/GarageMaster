/**
 * 
 */
const button = document.querySelector("#button");

//Validação para o formulário de inserção de client
button.addEventListener("click", ()=>{
	 let name = formAddPeca.nome.value;
	 let descricao = formAddPeca.descricao.value;
	 let valor = formAddPeca.valor.value;
	 
	 if(name === "" || descricao === "" || valor === ""){
		 alert("Preencha os campos corretamente");
		 return false;
	 }else{
		 document.forms['formAddPeca'].submit();
	 } 
 });