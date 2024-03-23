package br.com.GarageMaster.logica.venda;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.RelationWithPecaDAO;
import br.com.GarageMaster.entities.RelationWithPeca;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllVendasNotRelations implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		//Recebendo o atributo da requisição
		String idVenda = req.getParameter("idVenda");
		int id = Integer.parseInt(idVenda);
		
		// Criando instãncia de dao
		RelationWithPecaDAO dao = null;

		try {
			dao = new RelationWithPecaDAO(); // Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ciando lista de servicos
		List<RelationWithPeca> pecas = dao.allNotExistsRelationWithVenda(id);
		req.setAttribute("pecas", pecas); // Setando como atributo

		// Redirecionando para a página informada
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}
}
