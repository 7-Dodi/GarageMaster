package br.com.GarageMaster.logica.client;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ClientDAO;
import br.com.GarageMaster.entities.Client;
import br.com.GarageMaster.logica.Logica;

public class FindClient implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id do cliente que foi passado no escopo da solicitação ao controller
		String idClient = req.getParameter("idUser");
		int id = Integer.parseInt(idClient);
		
		//Instanciando o dao e acessando o método de busca
		ClientDAO dao = null;
		try {
			dao = new ClientDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Criando um objeto com os dados recurperados
		Client cliente = null;
		try {
			cliente = dao.searchClient(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Setando os atributos de cliente:
		req.setAttribute("id", cliente.getId());
		req.setAttribute("nome", cliente.getNome());
		req.setAttribute("cpf", cliente.getCpf());
		req.setAttribute("endereco", cliente.getEndereco());
		req.setAttribute("telephone", cliente.getTelefone());
		
		RequestDispatcher rd = req.getRequestDispatcher("editClient.jsp");
		rd.forward(req, res);
	}

}
