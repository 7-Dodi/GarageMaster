package br.com.GarageMaster.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.GarageMaster.logica.venda.CreatedRelationVenda;
import br.com.GarageMaster.logica.venda.CreatedVenda;
import br.com.GarageMaster.logica.venda.DeleteRelationVenda;
import br.com.GarageMaster.logica.venda.DeleteVenda;
import br.com.GarageMaster.logica.venda.EditVenda;
import br.com.GarageMaster.logica.venda.FindAllClientesAndFuncionarios;
import br.com.GarageMaster.logica.venda.FindAllVendas;
import br.com.GarageMaster.logica.venda.FindAllVendasNotRelations;
import br.com.GarageMaster.logica.venda.FindVenda;

@WebServlet(urlPatterns = {"/ControllerVenda", "/venda", "/vendaForm", "/relationVenda", "/addVenda", "/addRelationVenda", "/selectVenda","/updateVenda", "/deleteVenda", "/deleteRelationVenda"})
public class ControllerVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ControllerVenda() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		if (action.equals("/venda")) {
			vendaPage(request, response);
		}else if (action.equals("/relationVenda")) {
			vendaRelation(request, response);
		}else if (action.equals("/vendaForm")) {
			vendaForm(request, response);
		}else if (action.equals("/selectVenda")) {
			findVenda(request, response);
		}else if (action.equals("/updateVenda")) {
			updateVenda(request, response);
		}else if (action.equals("/deleteVenda")) {
			deleteVenda(request, response);
		}else if (action.equals("/addRelationVenda")) {
			newRelationVenda(request, response);
		}else if (action.equals("/deleteRelationVenda")) {
			deleteRelationVenda(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/addVenda")) {
			newVenda(request, response);
		}
	}

	//Páginas e métodos de vendas
	//Págiinca de vendas:
	protected void vendaPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FindAllVendas vendas = new FindAllVendas();
		
		try {
			vendas.executa(request, response, "vendaPage.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Venda form
	protected void vendaForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FindAllClientesAndFuncionarios clientsAndFuncionarios = new FindAllClientesAndFuncionarios();
		
		try {
			clientsAndFuncionarios.executa(request, response, "addVenda.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Adicionar uma nova venda
	protected void newVenda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreatedVenda venda = new CreatedVenda();
		
		try {
			venda.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Pesquisar um Venda:
	protected void findVenda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FindVenda venda = new FindVenda();
		
		try {
			venda.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Editando uma venda
	protected void updateVenda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EditVenda venda = new EditVenda();
		
		try {
			venda.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Deletando uma venda
	protected void deleteVenda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeleteVenda venda = new DeleteVenda();
		
		try {
			venda.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Listando peças para uma venda
	protected void vendaRelation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FindAllVendasNotRelations vendasNotRelation = new FindAllVendasNotRelations();
		
		try {
			vendasNotRelation.executa(request, response, "addRelationVenda.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Criação a relação entre Vnedas e Peças
	protected void newRelationVenda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreatedRelationVenda vendaRelation = new CreatedRelationVenda();
		
		try {
			vendaRelation.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Criação a relação entre vendas e Peças
	protected void deleteRelationVenda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeleteRelationVenda vendaDeletion = new DeleteRelationVenda();
		
		try {
			vendaDeletion.executa(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
