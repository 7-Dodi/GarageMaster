package br.com.GarageMaster.logica.venda;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VendaDAO;
import br.com.GarageMaster.entities.Venda;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllVendas implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		// TODO Auto-generated method stub
		// Criando instãncia de dao
		VendaDAO dao = null;

		try {
			dao = new VendaDAO(); // Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ciando lista de servicos
		List<Venda> vendas = dao.allVendass();
		req.setAttribute("vendas", vendas); // Setando como atributo

		// Redirecionando para a página informada
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}
}
