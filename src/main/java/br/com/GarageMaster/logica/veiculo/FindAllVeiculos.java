package br.com.GarageMaster.logica.veiculo;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VeiculoDAO;
import br.com.GarageMaster.entities.Veiculo;
import br.com.GarageMaster.logica.Logica;

public class FindAllVeiculos implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Criando instãncia de dao
		VeiculoDAO dao = null;
		
		try {
			dao = new VeiculoDAO(); //Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Ciando lista de clientes
		List<Veiculo> veiculos = dao.allVeiculos();
		req.setAttribute("veiculos", veiculos); //Setando como atributo
		
		//Redirecionando para a página de clients
		RequestDispatcher rd = req.getRequestDispatcher("veiculoPage.jsp");
		rd.forward(req, res);
	}

}
