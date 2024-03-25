package br.com.GarageMaster.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.logica.client.FindAllClients;
import br.com.GarageMaster.logica.veiculo.CreatedVeiculo;
import br.com.GarageMaster.logica.veiculo.DeleteVeiculo;
import br.com.GarageMaster.logica.veiculo.EditVeiculo;
import br.com.GarageMaster.logica.veiculo.FindAllVeiculos;
import br.com.GarageMaster.logica.veiculo.FindVeiculo;

@WebServlet( urlPatterns = {"/ControllerVeiculo", "/veiculo", "/clientsVeiculo", "/addVeiculo", "/selectVeiculo","/updateVeiculo", "/deleteVeiculo",})
public class ControllerVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerVeiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		if (action.equals("/veiculo")) {
			veiculoPage(request, response);
		}else if (action.equals("/clientsVeiculo")) {
			veiculoOfClients(request, response);
		}else if (action.equals("/selectVeiculo")) {
			findVeiculo(request, response);
		}else if (action.equals("/updateVeiculo")) {
			updateVeiculo(request, response);
		}else if (action.equals("/deleteVeiculo")) {
			deleteVeiculo(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/addVeiculo")) {
			newVeiculo(request, response);
		}
	}
	
	
	// Páginas e métodos de veiculo
		protected void veiculoPage(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindAllVeiculos findAllVeiculos = new FindAllVeiculos();

			try {
				findAllVeiculos.executa(request, response, "veiculoPage.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Listando os donos de veiculos
		protected void veiculoOfClients(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindAllClients findAllClients = new FindAllClients();

			try {
				findAllClients.executa(request, response, "addVeiculo.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Metódo para adicionar um novo veiculo
		protected void newVeiculo(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			CreatedVeiculo newVeiculo = new CreatedVeiculo();

			try {
				newVeiculo.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Pesquisar um veiculo:
		protected void findVeiculo(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindVeiculo findVeiculo = new FindVeiculo();

			try {
				findVeiculo.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Editando um veiculo
		protected void updateVeiculo(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			EditVeiculo editVeiculo = new EditVeiculo();

			try {
				editVeiculo.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Deletando um veiculo
		protected void deleteVeiculo(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			DeleteVeiculo deleteVeiculo = new DeleteVeiculo();

			try {
				deleteVeiculo.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
