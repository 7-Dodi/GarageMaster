package br.com.GarageMaster.logica.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.RelationWithPecaDAO;
import br.com.GarageMaster.dao.ServiceDAO;
import br.com.GarageMaster.entities.RelationWithPeca;
import br.com.GarageMaster.entities.Servico;
import br.com.GarageMaster.logica.Logica;

public class FindService implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id do cliente que foi passado no escopo da solicitação ao controller
		String idService = req.getParameter("idService");
		int id = Integer.parseInt(idService);
		
		//Instanciando o dao e acessando o método de busca
		ServiceDAO dao = null;
		RelationWithPecaDAO relationDao = null;
		try {
			dao = new ServiceDAO();
			relationDao = new RelationWithPecaDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Criando um objeto com os dados recurperados
		Servico servico = null;
		try {
			servico = dao.searchService(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Montando uma lista de atributos
		List<RelationWithPeca> relationPeca = relationDao.allServiceAndPeca(id);
		
		
		//Setando os atributos de service:
		req.setAttribute("id", servico.getId());
		req.setAttribute("data", servico.getData());
		req.setAttribute("valor", servico.getValor());
		req.setAttribute("descricao", servico.getDescricao());
		req.setAttribute("idVeiculo", servico.getIdVeiculo());
		req.setAttribute("idFuncionario", servico.getIdFuncionario());
		
		//Settando a lista
		req.setAttribute("listaPeca", relationPeca);
		
		RequestDispatcher rd = req.getRequestDispatcher("editService.jsp");
		rd.forward(req, res);
	}

}
