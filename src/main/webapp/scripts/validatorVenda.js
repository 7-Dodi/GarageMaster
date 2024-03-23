/**
 * 
 */
const button = document.querySelector("#finalizar");

//Validação para o formulário de inserção de uma venda
button.addEventListener("click", ()=>{
	 let cliente = formAddVenda.idCliente.value;
	 let funcionario = formAddVenda.idFuncionario.value;
	 
	 if(cliente === "" || funcionario === ""){
		 alert("Preencha os campos corretamente");
		 return false;
	 }else{
		 document.forms['formAddVenda'].submit();
	 }
 });
 
//Função para remover uma venda
function confirmar (id){
	 let resposta = confirm("Confirmar a exclusão dessa venda?");
	 if(resposta === true){
		 window.location.href= "deleteVenda?idVenda=" + id;
	 }
 }