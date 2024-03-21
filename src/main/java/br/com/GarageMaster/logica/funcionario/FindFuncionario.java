package br.com.GarageMaster.logica.funcionario;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.entities.Funcionario;
import br.com.GarageMaster.logica.Logica;
public class FindFuncionario implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id do funcionario que foi passado no escopo da solicitação ao controller
		String idfuncionario = req.getParameter("idUser");
		int id = Integer.parseInt(idfuncionario);
		
		//Instanciando o dao e acessando o método de busca
		FuncionarioDAO dao = null;
		try {
			dao = new FuncionarioDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Criando um objeto com os dados recurperados
		Funcionario funcionario = null;
		try {
			funcionario = dao.searchFuncionario(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Setando os atributos de cliente:
		req.setAttribute("id", funcionario.getId());
		req.setAttribute("nome", funcionario.getNome());
		req.setAttribute("cpf", funcionario.getCpf());
		req.setAttribute("endereco", funcionario.getEndereco());
		req.setAttribute("cargo", funcionario.getCargo());
		req.setAttribute("matricula", funcionario.getMatricula());
		req.setAttribute("senha", funcionario.getSenha());
		
		RequestDispatcher rd = req.getRequestDispatcher("editFuncionario.jsp");
		rd.forward(req, res);
	}

}
