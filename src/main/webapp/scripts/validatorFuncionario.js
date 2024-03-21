/**
 * 
 */
const buttonFun = document.querySelector("#funButton");
 
 //Validação do formulário de inserção de funcionario
 buttonFun.addEventListener("click", ()=>{
	 let name = formAddFuncionario.nome.value;
	 let cpf = formAddFuncionario.cpf.value;
	 let endereco = formAddFuncionario.endereco.value;
	 let cargo = formAddFuncionario.cargo.value;
	 let matricula = formAddFuncionario.matricula.value;
	 let senha = formAddFuncionario.senha.value;
	 
	 if(name === "" || cpf === "" || endereco === "" || cargo === "" || matricula === "" || senha === ""){
		 alert("Preencha os campos corretamente");
		 return false;
	 }else{
		 document.forms['formAddFuncionario'].submit();
	 }
 });