package br.com.GarageMaster.logica.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ClientDAO;
import br.com.GarageMaster.logica.Logica;

public class DeleteClient implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idUser = req.getParameter("idUser");
		int id = Integer.parseInt(idUser);
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		ClientDAO dao = new ClientDAO();
		dao.removeClient(id);
		
		//Redirecionando para a tela principal
		res.sendRedirect("client");
	}

}
