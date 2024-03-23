package br.com.GarageMaster.logica.venda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VendaDAO;
import br.com.GarageMaster.entities.Venda;
import br.com.GarageMaster.logica.Logica;

public class CreatedVenda implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("Executando a logica e redirecionando...");

		// Buscando os parâmetros no request
		String idClient = req.getParameter("idCliente");
		String idFuncionario = req.getParameter("idFuncionario");
		
		// Montando o objeto Venda
		Venda venda = new Venda();
		
		venda.setData();
		venda.setFinalizacao();
		venda.setIdClient(Integer.parseInt(idClient));
		venda.setIdFuncionario(Integer.parseInt(idFuncionario));
		
		//Salvar a Peca
		VendaDAO dao;
		try {
			dao = new VendaDAO();
			dao.createdVenda(venda);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Redirecionando para a página de confirmação
		res.sendRedirect("venda");
	}

}
