package br.com.GarageMaster.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.logica.client.CreatedClient;
import br.com.GarageMaster.logica.client.DeleteClient;
import br.com.GarageMaster.logica.client.EditClient;
import br.com.GarageMaster.logica.client.FindAllClients;
import br.com.GarageMaster.logica.client.FindClient;
import br.com.GarageMaster.logica.funcionario.AuthenticationFuncionario;

@WebServlet(urlPatterns = { "/Controller", "/authenticationFilter", "/client", "/addClient", "/selectClient", "/updateClient", "/deleteClient" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		if (action.equals("/client")) {
			clientsPage(request, response);
		}else if (action.equals("/selectClient")) {
			findClient(request, response);
		}else if (action.equals("/updateClient")) {
			updateClient(request, response);
		}else if (action.equals("/deleteClient")) {
			deleteClient(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/addClient")) {
			newClient(request, response);
		}
		if (action.equals("/authenticationFilter")) {
			authenticateUser(request, response);
		}
	}

	// Método privado para autenticar funcionario ao sistema
	private void authenticateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuthenticationFuncionario authentication = new AuthenticationFuncionario();

		try {
			authentication.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Páginas e métodos de Cliente
	// Página inicial de Clients
	protected void clientsPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FindAllClients findAllClients = new FindAllClients();

		try {
			findAllClients.executa(request, response, "clientsPage.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Adicionar um novo cliente:
	protected void newClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreatedClient createdClient = new CreatedClient();

		try {
			createdClient.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Pesquisar um cliente:
	protected void findClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FindClient findClient = new FindClient();

		try {
			findClient.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Editando um cliente
	protected void updateClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EditClient editClient = new EditClient();

		try {
			editClient.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Deletando um cliente
	protected void deleteClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeleteClient deleteClient = new DeleteClient();

		try {
			deleteClient.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	

}
