/**
 * 
 */

//Função para remover um cliente
function confirmar (id){
	 let resposta = confirm("Confirmar a exclusão desse serviço?");
	 if(resposta === true){
		 window.location.href= "deleteService?idService=" + id;
	 }
 }