package br.com.GarageMaster.logica.peca;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.PecaDAO;
import br.com.GarageMaster.entities.Peca;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllPecas implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		// TODO Auto-generated method stub
		// Criando instãncia de dao
		PecaDAO dao = null;

		try {
			dao = new PecaDAO(); // Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ciando lista de peças
		List<Peca> pecas = dao.allPecas();
		req.setAttribute("pecas", pecas); // Setando como atributo

		// Redirecionando para a página informada
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}
}
