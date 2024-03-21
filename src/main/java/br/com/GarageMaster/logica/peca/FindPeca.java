package br.com.GarageMaster.logica.peca;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.PecaDAO;
import br.com.GarageMaster.entities.Peca;
import br.com.GarageMaster.logica.Logica;

public class FindPeca implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id do cliente que foi passado no escopo da solicitação ao controller
		String idPeca = req.getParameter("idPeca");
		int id = Integer.parseInt(idPeca);
		
		//Instanciando o dao e acessando o método de busca
		PecaDAO dao = null;
		try {
			dao = new PecaDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Criando um objeto com os dados recurperados
		Peca peca = null;
		try {
			peca = dao.searchPeca(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Setando os atributos de peca:
		req.setAttribute("id", peca.getId());
		req.setAttribute("nome", peca.getNome());
		req.setAttribute("descricao", peca.getDescricao());
		req.setAttribute("valor", peca.getValor());
		
		RequestDispatcher rd = req.getRequestDispatcher("editPeca.jsp");
		rd.forward(req, res);
	}

}
