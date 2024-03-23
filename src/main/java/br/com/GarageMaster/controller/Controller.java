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
import br.com.GarageMaster.logica.funcionario.CreatedFuncionario;
import br.com.GarageMaster.logica.funcionario.DeleteFuncionario;
import br.com.GarageMaster.logica.funcionario.EditFuncionario;
import br.com.GarageMaster.logica.funcionario.FindAllFuncionarios;
import br.com.GarageMaster.logica.funcionario.FindFuncionario;
import br.com.GarageMaster.logica.peca.CreatedPeca;
import br.com.GarageMaster.logica.peca.DeletePeca;
import br.com.GarageMaster.logica.peca.EditPeca;
import br.com.GarageMaster.logica.peca.FindAllPecas;
import br.com.GarageMaster.logica.peca.FindPeca;
import br.com.GarageMaster.logica.service.CreatedRelationService;
import br.com.GarageMaster.logica.service.CreatedService;
import br.com.GarageMaster.logica.service.DeleteRelationService;
import br.com.GarageMaster.logica.service.DeleteService;
import br.com.GarageMaster.logica.service.EditService;
import br.com.GarageMaster.logica.service.FindAllServices;
import br.com.GarageMaster.logica.service.FindAllServicesNotRelations;
import br.com.GarageMaster.logica.service.FindAllVeiculosAndFuncionarios;
import br.com.GarageMaster.logica.service.FindService;
import br.com.GarageMaster.logica.veiculo.CreatedVeiculo;
import br.com.GarageMaster.logica.veiculo.DeleteVeiculo;
import br.com.GarageMaster.logica.veiculo.EditVeiculo;
import br.com.GarageMaster.logica.veiculo.FindAllVeiculos;
import br.com.GarageMaster.logica.veiculo.FindVeiculo;

@WebServlet(urlPatterns = { "/Controller", "/authenticationFilter", "/client", "/funcionario", "/veiculo", "/peca",
		"/service", "/relationService", "/clientsVeiculo", "/serviceForm", "/addClient", "/addFuncionario", "/addVeiculo", "/addPeca",
		"/addService", "/addRelationService", "/selectClient", "/updateClient", "/selectFuncionario", "/updateFuncionario", "/selectVeiculo",
		"/updateVeiculo", "/selectPeca", "/updatePeca", "/selectService", "/updateService", "/deleteClient",
		"/deleteFuncionario", "/deleteVeiculo", "/deletePeca", "/deleteService", "/deleteRelationService"})
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
		} else if (action.equals("/funcionario")) {
			funcionarioPage(request, response);
		} else if (action.equals("/veiculo")) {
			veiculoPage(request, response);
		} else if (action.equals("/peca")) {
			pecaPage(request, response);
		} else if (action.equals("/service")) {
			servicePage(request, response);
		}else if (action.equals("/relationService")) {
			serviceRelation(request, response);
		} else if (action.equals("/clientsVeiculo")) {
			veiculoOfClients(request, response);
		} else if (action.equals("/serviceForm")) {
			serviceForm(request, response);
		} else if (action.equals("/selectClient")) {
			findClient(request, response);
		} else if (action.equals("/selectFuncionario")) {
			findFuncionario(request, response);
		} else if (action.equals("/selectVeiculo")) {
			findVeiculo(request, response);
		} else if (action.equals("/selectPeca")) {
			findPeca(request, response);
		} else if (action.equals("/selectService")) {
			findService(request, response);
		} else if (action.equals("/updateClient")) {
			updateClient(request, response);
		} else if (action.equals("/updateFuncionario")) {
			updateFuncionario(request, response);
		} else if (action.equals("/updateVeiculo")) {
			updateVeiculo(request, response);
		} else if (action.equals("/updatePeca")) {
			updatePeca(request, response);
		} else if (action.equals("/updateService")) {
			updateService(request, response);
		} else if (action.equals("/deleteClient")) {
			deleteClient(request, response);
		} else if (action.equals("/deleteFuncionario")) {
			deleteFuncionario(request, response);
		} else if (action.equals("/deleteVeiculo")) {
			deleteVeiculo(request, response);
		} else if (action.equals("/deletePeca")) {
			deletePeca(request, response);
		} else if (action.equals("/deleteService")) {
			deleteService(request, response);
		}else if (action.equals("/addRelationService")) {
			newRelationService(request, response);
		}else if (action.equals("/deleteRelationService")) {
			deleteRelationService(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/addClient")) {
			newClient(request, response);
		} else if (action.equals("/addFuncionario")) {
			newFuncionario(request, response);
		} else if (action.equals("/addVeiculo")) {
			newVeiculo(request, response);
		} else if (action.equals("/addPeca")) {
			newPeca(request, response);
		} else if (action.equals("/addService")) {
			newService(request, response);
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
