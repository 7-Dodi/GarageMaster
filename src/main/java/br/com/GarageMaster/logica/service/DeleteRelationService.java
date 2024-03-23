package br.com.GarageMaster.logica.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.RelationWithPecaDAO;
import br.com.GarageMaster.logica.Logica;

public class DeleteRelationService implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idService = req.getParameter("idService");
		String idPeca = req.getParameter("idPeca");
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		RelationWithPecaDAO dao = new RelationWithPecaDAO();
		dao.removeRelationServiceAndPeca(Integer.parseInt(idPeca), Integer.parseInt(idService));
		
		//Redirecionando para a tela principal
		res.sendRedirect("selectService?idService="+idService);
	}

}
