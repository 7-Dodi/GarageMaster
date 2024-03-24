package br.com.GarageMaster.logica.veiculo;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.ClientDAO;
import br.com.GarageMaster.dao.VeiculoDAO;
import br.com.GarageMaster.entities.Veiculo;
import br.com.GarageMaster.logica.Logica;

public class CreatedVeiculo implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("Executando a logica e redirecionando...");
		ClientDAO ClientDao = new ClientDAO();
		VeiculoDAO dao;

		// Buscando os parâmetros no request
		String modelo = req.getParameter("modelo");
		String placa = req.getParameter("placa");
		String marca = req.getParameter("marca");
		String idCliente = req.getParameter("idCliente");

		// Verificando se o cliente existe
		if (!ClientDao.ClientExists(Integer.parseInt(idCliente))) {
			//Redirecionar para a página de criação
			 res.sendRedirect("addVeiculo.html");
		} else {
			// Montando o objeto veiculo
			Veiculo veiculo = new Veiculo();
			veiculo.setModelo(modelo);
			veiculo.setPlaca(placa);
			veiculo.setMarca(marca);
			veiculo.setIdClient(Integer.parseInt(idCliente));

			// Salvar o veiculo
			try {
				dao = new VeiculoDAO();
				dao.createdVeiculo(veiculo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher rd = req.getRequestDispatcher("/erroRuntime.html");
		        rd.forward(req, res);
			}

			// Redirecionando para a página de confirmação
			RequestDispatcher rd = req.getRequestDispatcher("/veiculoAdd.jsp");
			rd.forward(req, res);
		}
	}
}
