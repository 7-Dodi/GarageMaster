package br.com.GarageMaster.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.logica.peca.CreatedPeca;
import br.com.GarageMaster.logica.peca.DeletePeca;
import br.com.GarageMaster.logica.peca.EditPeca;
import br.com.GarageMaster.logica.peca.FindAllPecas;
import br.com.GarageMaster.logica.peca.FindPeca;

@WebServlet( urlPatterns = {"/ControllerPeca", "/peca", "/addPeca", "/selectPeca", "/updatePeca", "/deletePeca"})
public class ControllerPeca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerPeca() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		if (action.equals("/peca")) {
			pecaPage(request, response);
		}else if (action.equals("/selectPeca")) {
			findPeca(request, response);
		}else if (action.equals("/updatePeca")) {
			updatePeca(request, response);
		}else if (action.equals("/deletePeca")) {
			deletePeca(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		if (action.equals("/addPeca")) {
			newPeca(request, response);
		}
	}

	// Métodos de Peças
	// Listar peças
	protected void pecaPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FindAllPecas findAllPecas = new FindAllPecas();

		try {
			findAllPecas.executa(request, response, "pecaPage.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Criar peça
	protected void newPeca(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreatedPeca newPeca = new CreatedPeca();

		try {
			newPeca.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Listar dados de uma peca
	protected void findPeca(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FindPeca findPeca = new FindPeca();

		try {
			findPeca.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Editando uma peca
	protected void updatePeca(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EditPeca editPeca = new EditPeca();

		try {
			editPeca.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Deletando uma peca
	protected void deletePeca(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeletePeca peca = new DeletePeca();

		try {
			peca.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
