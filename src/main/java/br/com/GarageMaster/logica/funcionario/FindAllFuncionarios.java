package br.com.GarageMaster.logica.funcionario;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.entities.Funcionario;
import br.com.GarageMaster.logica.Logica;

public class FindAllFuncionarios implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
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
		RequestDispatcher rd = req.getRequestDispatcher("funcionarioPage.jsp");
		rd.forward(req, res);
	}

}
