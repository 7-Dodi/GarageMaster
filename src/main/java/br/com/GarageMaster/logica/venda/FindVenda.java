package br.com.GarageMaster.logica.venda;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.RelationWithPecaDAO;
import br.com.GarageMaster.dao.VendaDAO;
import br.com.GarageMaster.entities.RelationWithPeca;
import br.com.GarageMaster.entities.Venda;
import br.com.GarageMaster.logica.Logica;

public class FindVenda implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id do cliente que foi passado no escopo da solicitação ao controller
		String idVenda = req.getParameter("idVenda");
		int id = Integer.parseInt(idVenda);
		
		//Instanciando o dao e acessando o método de busca
		VendaDAO dao = null;
		RelationWithPecaDAO relationDao = null;
		try {
			dao = new VendaDAO();
			relationDao = new RelationWithPecaDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Criando um objeto com os dados recurperados
		Venda venda = null;
		try {
			venda = dao.searchVenda(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Montando uma lista de atributos
		List<RelationWithPeca> relationPeca = relationDao.allVendaAndPeca(id);
		
		
		//Setando os atributos de venda:
		req.setAttribute("id", venda.getId());
		req.setAttribute("data", venda.getData());
		req.setAttribute("valor", venda.getValor());
		req.setAttribute("finalizacao", venda.getFinalizacao());
		req.setAttribute("idCliente", venda.getIdClient());
		req.setAttribute("idFuncionario", venda.getIdFuncionario());
		
		//Settando a lista
		req.setAttribute("listaPeca", relationPeca);
		
		RequestDispatcher rd = req.getRequestDispatcher("editVenda.jsp");
		rd.forward(req, res);
	}

}
