package br.com.GarageMaster.logica.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.RelationWithPecaDAO;
import br.com.GarageMaster.entities.RelationWithPeca;
import br.com.GarageMaster.logica.Logica;

public class CreatedRelationService implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("Executando a logica e redirecionando...");

		// Buscando os parâmetros no request
		String idPeca = req.getParameter("idPeca");
		String idService = req.getParameter("idService");
		String quantidade = req.getParameter("quantidade");
		
		// Montando o objeto Servico
		RelationWithPeca relation = new RelationWithPeca();
		
		relation.setIdPeca(Integer.parseInt(idPeca));
		relation.setIdServico(Integer.parseInt(idService));
		relation.setQuantidade(Integer.parseInt(quantidade));
		
		//Salvar a Servico
		RelationWithPecaDAO dao;
		try {
			dao = new RelationWithPecaDAO();
			dao.createdServicePeca(relation);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = req.getRequestDispatcher("/erroRuntime.html");
	        rd.forward(req, res);
		}
		
		res.sendRedirect("relationService?idService="+idService);
	}

}
