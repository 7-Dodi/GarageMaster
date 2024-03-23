package br.com.GarageMaster.logica.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.dao.VeiculoDAO;
import br.com.GarageMaster.entities.Funcionario;
import br.com.GarageMaster.entities.Veiculo;
import br.com.GarageMaster.logica.LogicaRedirectJsp;

public class FindAllVeiculosAndFuncionarios implements LogicaRedirectJsp {

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res, String redirect) throws Exception {
		// Criando instãncia de dao
		VeiculoDAO daoVeiculos = null;
		FuncionarioDAO daoFuncionarios = null;

		try {
			daoVeiculos = new VeiculoDAO(); // Instanciando
			daoFuncionarios = new FuncionarioDAO(); // Instanciando
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ciando lista de clientes
		List<Veiculo> veiculos = daoVeiculos.allVeiculos();
		req.setAttribute("veiculos", veiculos); // Setando como atributo

		// Ciando lista de clientes
		List<Funcionario> funcionarios = daoFuncionarios.allFuncionarios();
		req.setAttribute("funcionarios", funcionarios); // Setando como atributo

		// Redirecionando para a página de clients
		RequestDispatcher rd = req.getRequestDispatcher(redirect);
		rd.forward(req, res);
	}

}
