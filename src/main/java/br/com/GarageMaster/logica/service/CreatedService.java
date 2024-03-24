package br.com.GarageMaster.logica.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ServiceDAO;
import br.com.GarageMaster.entities.Servico;
import br.com.GarageMaster.logica.Logica;

public class CreatedService implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("Executando a logica e redirecionando...");

		// Buscando os parâmetros no request
		String descricao = req.getParameter("descricao");
		String valor = req.getParameter("valor");
		String idVeiculo = req.getParameter("idVeiculo");
		String idFuncionario = req.getParameter("idFuncionario");
		
		// Montando o objeto Servico
		Servico service = new Servico();
		
		service.setData();
		service.setDescricao(descricao);
		service.setValor(Float.parseFloat(valor));
		service.setIdVeiculo(Integer.parseInt(idVeiculo));
		service.setIdFuncionario(Integer.parseInt(idFuncionario));
		
		//Salvar a Peca
		ServiceDAO dao;
		try {
			dao = new ServiceDAO();
			dao.createdService(service);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = req.getRequestDispatcher("/erroRuntime.html");
	        rd.forward(req, res);
		}
		
		// Redirecionando para a página de confirmação
		RequestDispatcher rd = req.getRequestDispatcher("/serviceAdd.jsp");
		rd.forward(req, res);
	}

}
