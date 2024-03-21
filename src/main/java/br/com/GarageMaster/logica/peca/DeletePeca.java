package br.com.GarageMaster.logica.peca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.PecaDAO;
import br.com.GarageMaster.logica.Logica;

public class DeletePeca implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idPeca = req.getParameter("idPeca");
		int id = Integer.parseInt(idPeca);
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		PecaDAO dao = new PecaDAO();
		dao.removePeca(id);
		
		//Redirecionando para a tela principal
		res.sendRedirect("peca");
	}

}
