package br.com.GarageMaster.logica.client;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ClientDAO;
import br.com.GarageMaster.entities.Client;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllClients implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		//Criando instãncia de dao
		ClientDAO dao = null;
		
		try {
			dao = new ClientDAO(); //Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Ciando lista de clientes
		List<Client> clientes = dao.allClients();
		req.setAttribute("clients", clientes); //Setando como atributo
		
		//Redirecionando para a página de clients
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}

}
