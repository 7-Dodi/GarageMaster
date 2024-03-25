package br.com.GarageMaster.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.logica.service.CreatedRelationService;
import br.com.GarageMaster.logica.service.CreatedService;
import br.com.GarageMaster.logica.service.DeleteRelationService;
import br.com.GarageMaster.logica.service.DeleteService;
import br.com.GarageMaster.logica.service.EditService;
import br.com.GarageMaster.logica.service.FindAllServices;
import br.com.GarageMaster.logica.service.FindAllServicesNotRelations;
import br.com.GarageMaster.logica.service.FindAllVeiculosAndFuncionarios;
import br.com.GarageMaster.logica.service.FindService;

@WebServlet(urlPatterns = {"/ControllerServico", "/service", "/relationService", "/serviceForm", "/addService", "/addRelationService", "/selectService", "/updateService", "/deleteService", "/deleteRelationService"})
public class ControllerServico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerServico() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		if (action.equals("/service")) {
			servicePage(request, response);
		}else if (action.equals("/relationService")) {
			serviceRelation(request, response);
		}else if (action.equals("/serviceForm")) {
			serviceForm(request, response);
		}else if (action.equals("/selectService")) {
			findService(request, response);
		}else if (action.equals("/updateService")) {
			updateService(request, response);
		}else if (action.equals("/deleteService")) {
			deleteService(request, response);
		}else if (action.equals("/addRelationService")) {
			newRelationService(request, response);
		}else if (action.equals("/deleteRelationService")) {
			deleteRelationService(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/addService")) {
			newService(request, response);
		}
	}
	
	// Métodos de Services
		// Página inicial de Serviços
		protected void servicePage(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindAllServices services = new FindAllServices();

			try {
				services.executa(request, response, "servicePage.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//ServiceForm
		protected void serviceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			FindAllVeiculosAndFuncionarios veiculosAndFuncionarios = new FindAllVeiculosAndFuncionarios();
			
			try {
				veiculosAndFuncionarios.executa(request, response, "addService.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Adicionar um novo serviço:
		protected void newService(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			CreatedService service = new CreatedService();

			try {
				service.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Pesquisar um serviço:
		protected void findService(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindService service = new FindService();

			try {
				service.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Editando um serviço
		protected void updateService(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			EditService service = new EditService();

			try {
				service.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Deletando um serviço
		protected void deleteService(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			DeleteService service = new DeleteService();

			try {
				service.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Relação service Pecas
		protected void serviceRelation(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			FindAllServicesNotRelations findPecas = new FindAllServicesNotRelations();
			
			try {
				findPecas.executa(request, response, "addRelationService.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Criação a relação entre serviços e Peças
		protected void newRelationService(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			CreatedRelationService relationService = new CreatedRelationService();
			
			try {
				relationService.executa(request, response);			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Criação a relação entre serviços e Peças
		protected void deleteRelationService(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			DeleteRelationService delete = new DeleteRelationService();
			
			try {
				delete.executa(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
