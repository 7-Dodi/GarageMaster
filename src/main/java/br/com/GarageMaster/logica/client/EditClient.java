package br.com.GarageMaster.logica.client;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ClientDAO;
import br.com.GarageMaster.entities.Client;
import br.com.GarageMaster.logica.Logica;

public class EditClient implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// Buscando os parâmetros no request
		String idClient = req.getParameter("id");
		String name = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String endereco = req.getParameter("endereco");
		String tephone = req.getParameter("tephone");

		// Montando o objeto client
		Client cliente = new Client();
		cliente.setId(Integer.parseInt(idClient));
		cliente.setNome(name);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		cliente.setTelefone(tephone);

		// Salvar o cliente
		ClientDAO dao;
		try {
			dao = new ClientDAO();
			dao.editClient(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = req.getRequestDispatcher("/erroRuntime.html");
	        rd.forward(req, res);
		}
		
		res.sendRedirect("client");
	}
}
