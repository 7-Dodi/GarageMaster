/**
 * 
 */

//Função para adicionar uma peça a uma venda
function confirmar (idPeca, idVenda){
	 let resposta = window.prompt("Por favor, insira a quantidade solicitada desse produto:");
	if(resposta !== null){
		window.location.href = "addRelationVenda?idPeca=" + idPeca + "&idVenda=" + idVenda + "&quantidade=" + resposta;
	
	}
}