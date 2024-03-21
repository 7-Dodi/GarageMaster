package br.com.GarageMaster.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.dao.FuncionarioDAO;
import br.com.GarageMaster.entities.Funcionario;

@WebFilter(urlPatterns = {"*.jsp", "*.html"})
public class AuthenticationFilter implements Filter {
       
	private FuncionarioDAO funcionarioDAO;

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
			funcionarioDAO = new FuncionarioDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Inicialize o DAO aqui
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        Funcionario funcionario = (Funcionario) request.getSession().getAttribute("funcionarioLogado");

        // Verifique se o funcionário está autenticado
        if (funcionario == null && !request.getRequestURI().endsWith("/login.html")
                && !request.getRequestURI().contains("/javax.faces.resource/")) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // Limpeza, se necessário
    }
}