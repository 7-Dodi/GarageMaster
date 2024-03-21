/**
 * 
 */

//Função para remover um cliente
function confirmar (id){
	 let resposta = confirm("Confirmar a exclusão desse peca?");
	 if(resposta === true){
		 window.location.href= "deletePeca?idPeca=" + id;
	 }
 }