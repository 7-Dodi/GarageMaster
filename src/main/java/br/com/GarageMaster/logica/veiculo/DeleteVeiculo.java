package br.com.GarageMaster.logica.veiculo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VeiculoDAO;
import br.com.GarageMaster.logica.Logica;

public class DeleteVeiculo implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idVeiculo = req.getParameter("idVeiculo");
		int id = Integer.parseInt(idVeiculo);
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		VeiculoDAO dao = new VeiculoDAO();
		dao.removeVeiculo(id);
		
		//Redirecionando para a tela principal
		res.sendRedirect("veiculo");
	}

}
