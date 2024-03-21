package br.com.GarageMaster.logica.veiculo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.VeiculoDAO;
import br.com.GarageMaster.entities.Veiculo;
import br.com.GarageMaster.logica.Logica;

public class EditVeiculo implements Logica {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// Buscando os parâmetros no request
		String idVeiculo = req.getParameter("id");
		String modelo = req.getParameter("modelo");
		String placa = req.getParameter("placa");
		String marca = req.getParameter("marca");
		String idCliente = req.getParameter("idCliente");

		// Montando o objeto veiculo
		Veiculo veiculo = new Veiculo();
		veiculo.setId(Integer.parseInt(idVeiculo));
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculo.setMarca(marca);
		veiculo.setIdClient(Integer.parseInt(idCliente));

		// Salvar o veiculo
		VeiculoDAO dao;
		try {
			dao = new VeiculoDAO();
			dao.editVeiculo(veiculo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		res.sendRedirect("veiculo");
	}
}
