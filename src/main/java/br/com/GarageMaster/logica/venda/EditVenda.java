package br.com.GarageMaster.logica.venda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VendaDAO;
import br.com.GarageMaster.entities.Venda;
import br.com.GarageMaster.logica.Logica;

public class EditVenda implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// Buscando os parâmetros no request
		String idVenda = req.getParameter("id");
		String finalizacao = req.getParameter("finalizacao");
		String data = req.getParameter("data");
		String valor = req.getParameter("valor");
		String idCliente = req.getParameter("idCliente");
		String idFuncionario = req.getParameter("idFuncionario");
		
		// Montando o objeto Venda
		Venda venda = new Venda();
		
		venda.setId(Integer.parseInt(idVenda));
		venda.setData(data);
		venda.setFinalizacao(finalizacao);
		venda.setValor(Float.parseFloat(valor));
		venda.setIdClient(Integer.parseInt(idCliente));
		venda.setIdFuncionario(Integer.parseInt(idFuncionario));
		
		//Salvar a Peca
		VendaDAO dao;
		try {
			dao = new VendaDAO();
			dao.editVenda(venda);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.sendRedirect("venda");
	}
}
