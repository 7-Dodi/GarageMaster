package br.com.GarageMaster.logica.peca;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.PecaDAO;
import br.com.GarageMaster.entities.Peca;
import br.com.GarageMaster.logica.Logica;

public class EditPeca implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// Buscando os parâmetros no request
		String idPeca = req.getParameter("id");
		String name = req.getParameter("nome");
		String descricao = req.getParameter("descricao");
		String valor = req.getParameter("valor");

		// Montando o objeto peca
		Peca peca = new Peca();
		peca.setId(Integer.parseInt(idPeca));
		peca.setNome(name);
		peca.setDescricao(descricao);
		peca.setValor(Float.parseFloat(valor));

		// Salvar a Peca
		PecaDAO dao;
		try {
			dao = new PecaDAO();
			dao.editPeca(peca);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = req.getRequestDispatcher("/erroRuntime.html");
	        rd.forward(req, res);
		}

		res.sendRedirect("peca");
	}
}
