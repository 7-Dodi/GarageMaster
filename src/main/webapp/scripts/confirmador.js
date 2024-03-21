/**
 * 
 */

//Função para remover um cliente
function confirmar (id){
	 let resposta = confirm("Confirmar a exclusão desse cliente?");
	 if(resposta === true){
		 window.location.href= "deleteClient?idUser=" + id;
	 }
 }
 
 //Função para remover um funcionario
 function confirmarFuncionario (id){
	 let resposta = confirm("Confirmar a exclusão desse funcionario?");
	 if(resposta === true){
		 window.location.href= "deleteFuncionario?idUser=" + id;
	 }
 }