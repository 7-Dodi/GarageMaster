package br.com.GarageMaster.logica.funcionario;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.entities.Funcionario;
import br.com.GarageMaster.logica.Logica;

public class CreatedFuncionario implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("Executando a logica e redirecionando...");

		// Buscando os parâmetros no request
		String name = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String endereco = req.getParameter("endereco");
		String cargo = req.getParameter("cargo");
		String matricula = req.getParameter("matricula");
		String senha = req.getParameter("senha");
		
		// Montando o objeto funcionario
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(name);
		funcionario.setCpf(cpf);
		funcionario.setEndereco(endereco);
		funcionario.setCargo(cargo);
		funcionario.setMatricula(matricula);
		funcionario.setSenha(senha);
		
		//Salvar o Funcionario
		FuncionarioDAO dao;
		try {
			dao = new FuncionarioDAO();
			dao.createdFuncionario(funcionario);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = req.getRequestDispatcher("/erroRuntime.html");
	        rd.forward(req, res);
		}
		
		//Redirecionando para a página de confirmação
		RequestDispatcher rd = req.getRequestDispatcher("/funcionarioAdd.jsp");
		rd.forward(req, res);
	}

}
