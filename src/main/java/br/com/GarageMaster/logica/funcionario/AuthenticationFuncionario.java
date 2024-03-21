package br.com.GarageMaster.logica.funcionario;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.entities.Funcionario;
import br.com.GarageMaster.logica.Logica;

public class AuthenticationFuncionario implements Logica{

	@Override
	public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String matricula = req.getParameter("matricula");
		String senha = req.getParameter("senha");
		
		FuncionarioDAO dao = null;
		try {
			dao = new FuncionarioDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Funcionario funcionario = new Funcionario();
		
		try {
			funcionario = dao.authenticate(matricula, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (funcionario != null) {
		    req.getSession().setAttribute("funcionarioLogado", funcionario);
		    res.sendRedirect(req.getContextPath() + "/index.html");
		} else {
		    // Autenticação falhou, redirecione para a página de login com uma mensagem de erro
		    res.sendRedirect(req.getContextPath() + "/login.html?error=1");
		}

		
	}

}
