package br.com.GarageMaster.logica.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ServiceDAO;
import br.com.GarageMaster.entities.Servico;
import br.com.GarageMaster.logica.Logica;

public class EditService implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// Buscando os parâmetros no request
		String idService = req.getParameter("id");
		String descricao = req.getParameter("descricao");
		String data = req.getParameter("data");
		String valor = req.getParameter("valor");
		String idVeiculo = req.getParameter("idVeiculo");
		String idFuncionario = req.getParameter("idFuncionario");
		
		// Montando o objeto Servico
		Servico service = new Servico();
		
		service.setId(Integer.parseInt(idService));
		service.setData(data);
		service.setDescricao(descricao);
		service.setValor(Float.parseFloat(valor));
		service.setIdVeiculo(Integer.parseInt(idVeiculo));
		service.setIdFuncionario(Integer.parseInt(idFuncionario));
		
		//Salvar a Peca
		ServiceDAO dao;
		try {
			dao = new ServiceDAO();
			dao.editServico(service);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.sendRedirect("service");
	}
}
