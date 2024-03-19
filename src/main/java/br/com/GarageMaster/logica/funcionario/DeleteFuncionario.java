package br.com.GarageMaster.logica.funcionario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.logica.Logica;

public class DeleteFuncionario implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Pegando o id que é passado pela a requisição
		String idUser = req.getParameter("idUser");
		int id = Integer.parseInt(idUser);
		
		//Instanciando e chamando o método do DAO para deletar o elemento
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.removeFuncionario(id);
		
		//Redirecionando para a tela principal
		res.sendRedirect("funcionario");
	}

}
