package br.com.GarageMaster.logica.veiculo;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ClientDAO;
import br.com.GarageMaster.entities.Client;
import br.com.GarageMaster.logica.Logica;

public class ClientsOfVeiculos implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// Criando instãncia de dao
		ClientDAO dao = null;

		try {
			dao = new ClientDAO(); // Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ciando lista de clientes
		List<Client> clientes = dao.allClients();
		req.setAttribute("clients", clientes); // Setando como atributo

		// Redirecionando para a página de clients
		RequestDispatcher rd = req.getRequestDispatcher("addVeiculo.jsp");
		rd.forward(req, res);

	}

}
