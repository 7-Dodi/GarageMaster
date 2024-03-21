package br.com.GarageMaster.logica.veiculo;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VeiculoDAO;
import br.com.GarageMaster.entities.Veiculo;
import br.com.GarageMaster.logica.Logica;

public class FindVeiculo implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id do veiculo que foi passado no escopo da solicitação ao controller
		String idVeiculo = req.getParameter("idVeiculo");
		int id = Integer.parseInt(idVeiculo);
		
		//Instanciando o dao e acessando o método de busca
		VeiculoDAO dao = null;
		try {
			dao = new VeiculoDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Criando um objeto com os dados recurperados
		Veiculo veiculo = null;
		try {
			veiculo = dao.searchVeiculo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Setando os atributos de cliente:
		req.setAttribute("id", veiculo.getId());
		req.setAttribute("modelo", veiculo.getModelo());
		req.setAttribute("placa", veiculo.getPlaca());
		req.setAttribute("marca", veiculo.getMarca());
		req.setAttribute("idCliente", veiculo.getIdClient());
		
		RequestDispatcher rd = req.getRequestDispatcher("editVeiculo.jsp");
		rd.forward(req, res);
	}

}
