package br.com.GarageMaster.logica.venda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.RelationWithPecaDAO;
import br.com.GarageMaster.logica.Logica;

public class DeleteRelationVenda implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idVenda = req.getParameter("idVenda");
		String idPeca = req.getParameter("idPeca");
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		RelationWithPecaDAO dao = new RelationWithPecaDAO();
		dao.removeRelationVendaAndPeca(Integer.parseInt(idPeca), Integer.parseInt(idVenda));
		
		//Redirecionando para a tela principal
		res.sendRedirect("selectVenda?idVenda="+idVenda);
	}

}
