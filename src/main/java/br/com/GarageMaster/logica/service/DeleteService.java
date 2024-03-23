package br.com.GarageMaster.logica.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ServiceDAO;
import br.com.GarageMaster.logica.Logica;

public class DeleteService implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idService = req.getParameter("idService");
		int id = Integer.parseInt(idService);
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		ServiceDAO dao = new ServiceDAO();
		dao.removeServico(id);
		
		//Redirecionando para a tela principal
		res.sendRedirect("service");
	}

}
