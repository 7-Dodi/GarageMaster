package br.com.GarageMaster.logica.venda;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ClientDAO;
import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.entities.Client;
import br.com.GarageMaster.entities.Funcionario;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllClientesAndFuncionarios implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		// Criando instãncia de dao
		ClientDAO daoClientes = null;
		FuncionarioDAO daoFuncionarios = null;

		try {
			daoClientes = new ClientDAO(); // Instanciando
			daoFuncionarios = new FuncionarioDAO(); // Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ciando lista de clientes
		List<Client> clients = daoClientes.allClients();
		req.setAttribute("clientes", clients); // Setando como atributo

		// Ciando lista de clientes
		List<Funcionario> funcionarios = daoFuncionarios.allFuncionarios();
		req.setAttribute("funcionarios", funcionarios); // Setando como atributo

		// Redirecionando para a página de clients
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}

}
