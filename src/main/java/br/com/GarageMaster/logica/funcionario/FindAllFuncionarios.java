package br.com.GarageMaster.logica.funcionario;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.entities.Funcionario;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllFuncionarios implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		//Criando instãncia de dao
		FuncionarioDAO dao = null;
		
		try {
			dao = new FuncionarioDAO(); //Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Ciando lista de clientes
		List<Funcionario> funcionarios = dao.allFuncionarios();
		req.setAttribute("funcionarios", funcionarios); //Setando como atributo
		
		//Redirecionando para a página de clients
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}

}
