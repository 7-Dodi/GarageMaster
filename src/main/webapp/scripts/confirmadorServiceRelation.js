/**
 * 
 */

//Função para adicionar uma peça ao serviço
function confirmar (idPeca, idServico){
	 let resposta = window.prompt("Por favor, insira a quantidade usada desse produto:");
	console.log("O valor inserido foi: " + resposta + "o id da Peca: " + idPeca + "id do servico" + idServico);
	if(resposta !== null){
		window.location.href = "addRelationService?idPeca=" + idPeca + "&idService=" + idServico + "&quantidade=" + resposta;
	
	}
}