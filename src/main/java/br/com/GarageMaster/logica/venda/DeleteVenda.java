package br.com.GarageMaster.logica.venda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VendaDAO;
import br.com.GarageMaster.logica.Logica;

public class DeleteVenda implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idVenda = req.getParameter("idVenda");
		int id = Integer.parseInt(idVenda);
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		VendaDAO dao = new VendaDAO();
		dao.removeVenda(id);
		
		//Redirecionando para a tela principal
		res.sendRedirect("venda");
	}

}
