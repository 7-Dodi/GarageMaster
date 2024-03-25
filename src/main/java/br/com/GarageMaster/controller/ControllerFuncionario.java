package br.com.GarageMaster.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.logica.funcionario.CreatedFuncionario;
import br.com.GarageMaster.logica.funcionario.DeleteFuncionario;
import br.com.GarageMaster.logica.funcionario.EditFuncionario;
import br.com.GarageMaster.logica.funcionario.FindAllFuncionarios;
import br.com.GarageMaster.logica.funcionario.FindFuncionario;

@WebServlet( urlPatterns = {"/ControllerFuncionario", "/funcionario", "/addFuncionario", "/selectFuncionario", "/updateFuncionario", "/deleteFuncionario"})
public class ControllerFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerFuncionario() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		
		if (action.equals("/funcionario")) {
			funcionarioPage(request, response);
		}else if (action.equals("/selectFuncionario")) {
			findFuncionario(request, response);
		}else if (action.equals("/updateFuncionario")) {
			updateFuncionario(request, response);
		}else if (action.equals("/deleteFuncionario")) {
			deleteFuncionario(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/addFuncionario")) {
			newFuncionario(request, response);
		}
	}

	// Páginas e métodos de Funcionario
	// Listando os funcionarios cadastrados
		protected void funcionarioPage(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindAllFuncionarios findAllFuncionatios = new FindAllFuncionarios();
			try {
				findAllFuncionatios.executa(request, response, "funcionarioPage.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	// Criando um novo funcionario
		protected void newFuncionario(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			CreatedFuncionario createdFuncionario = new CreatedFuncionario();

			try {
				createdFuncionario.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Pesquisando um funcionario
		protected void findFuncionario(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindFuncionario findFuncionario = new FindFuncionario();

			try {
				findFuncionario.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Editando um cliente
		protected void updateFuncionario(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			EditFuncionario editFuncionario = new EditFuncionario();

			try {
				editFuncionario.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Deletando um cliente
		protected void deleteFuncionario(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			DeleteFuncionario deleteFuncionario = new DeleteFuncionario();

			try {
				deleteFuncionario.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
