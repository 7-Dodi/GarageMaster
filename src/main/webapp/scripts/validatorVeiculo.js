/**
 * 
 */
const btnVeiculo = document.querySelector("#btnVeiculo");
 
 //Validação para o formulário de inserção de client
btnVeiculo.addEventListener("click", ()=>{
	 let modelo = formAddVeiculo.modelo.value;
	 let placa = formAddVeiculo.placa.value;
	 let marca = formAddVeiculo.marca.value;
	 let id = formAddVeiculo.idCliente.value;
	 
	 if(modelo === "" || placa === "" || marca === "" || id === ""){
		 alert("Preencha os campos corretamente");
		 return false;
	 }else{
		 document.forms['formAddVeiculo'].submit();
	 } 
 });
