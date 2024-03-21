/**
 * 
 */
 //Função para remover um veiculo
 function confirmarVeiculo (id){
	 let resposta = confirm("Confirmar a exclusão desse veiculo?");
	 if(resposta === true){
		 window.location.href= "deleteVeiculo?idVeiculo=" + id;
	 }
 }