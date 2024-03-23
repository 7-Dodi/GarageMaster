package br.com.GarageMaster.logica.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ServiceDAO;
import br.com.GarageMaster.entities.Servico;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllServices implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		// TODO Auto-generated method stub
		// Criando instãncia de dao
		ServiceDAO dao = null;

		try {
			dao = new ServiceDAO(); // Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ciando lista de servicos
		List<Servico> servicos = dao.allServicos();
		req.setAttribute("servicos", servicos); // Setando como atributo

		// Redirecionando para a página informada
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}
}
