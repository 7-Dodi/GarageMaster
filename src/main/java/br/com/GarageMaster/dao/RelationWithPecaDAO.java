package br.com.GarageMaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GarageMaster.connection.ConnectionManufature;
import br.com.GarageMaster.entities.RelationWithPeca;

public class RelationWithPecaDAO {
	private Connection conexao;

	public RelationWithPecaDAO() throws ClassNotFoundException {
		this.conexao = new ConnectionManufature().getConnection();
	}

	//Relação Peça e Serviço
	// Inserir dados de uma relação Serviço e Peça
	public void createdServicePeca(RelationWithPeca relation) {
		String sql = "insert into ServicoPeca (idServico, idPeca, quantidade) values (?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setInt(1, relation.getIdServico());
			stmt.setInt(2, relation.getIdPeca());
			stmt.setInt(3, relation.getQuantidade());

			// Executa
			stmt.execute();
			stmt.close();

			// Retornando ação:
			System.out.println("Relação adicionada ao sistema");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Listar dados de Serviço e Peça:
	public List<RelationWithPeca> allServiceAndPeca(int id) {
		try {
			List<RelationWithPeca> ralationDatas = new ArrayList<RelationWithPeca>();
			PreparedStatement stmts = conexao.prepareStatement("SELECT SP.idPeca as idPeca, P.nome as nomePeca, SP.quantidade as quantidade FROM ServicoPeca SP INNER JOIN Peca P ON SP.idPeca = P.id WHERE SP.idServico = ?");
			
			// Preenchendo os valores dos campos:
			stmts.setInt(1, id);
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto relação
				RelationWithPeca relation = new RelationWithPeca();

				relation.setIdServico(id);
				relation.setIdPeca(rs.getInt("idPeca"));
				relation.setNomePeca(rs.getString("nomePeca"));
				relation.setQuantidade(rs.getInt("quantidade"));

				ralationDatas.add(relation);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return ralationDatas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Listando peças que não tenha relação com serviço
	public List<RelationWithPeca> allNotExistsRelation(int id){
		try {
			List<RelationWithPeca> ralationDatas = new ArrayList<RelationWithPeca>();
			PreparedStatement stmts = conexao.prepareStatement("SELECT id, nome, valor, descricao FROM Peca WHERE id NOT IN ( SELECT idPeca FROM ServicoPeca WHERE idServico = ?);");
			
			// Preenchendo os valores dos campos:
			stmts.setInt(1, id);
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto relação
				RelationWithPeca relation = new RelationWithPeca();

				relation.setIdServico(id);
				relation.setIdPeca(rs.getInt("id"));
				relation.setNomePeca(rs.getString("nome"));
				relation.setDescricaoPeca(rs.getString("descricao"));
				relation.setValorPeca(rs.getString("valor"));
				
				ralationDatas.add(relation);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return ralationDatas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Procurando se a relaçao existe
	public boolean RelationServiceAndPecaExists(int idServico, int idPeca) {
		String sql = "select count(*) from ServicoPeca where idServico = ? and idPeca = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idServico);
			stmt.setInt(2, idPeca);
			
			// Executando
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt(1); // Contagem de registros da consulta

			// Encerrando
			rs.close();
			stmt.close();
			return count > 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Removendo um cadastro da relaçao
	public void removeRelationServiceAndPeca(int idPeca, int idService) {
		if (RelationServiceAndPecaExists(idService, idPeca)) { // Validando que existe
			String sql = "delete from ServicoPeca where idServico = ? and idPeca = ?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, idService);
				stmt.setInt(2, idPeca);
				
				// Executando
				stmt.execute();
				stmt.close();

				// Message
				System.out.println("Relação removida");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Relação não existe ou não encontrado");
		}
	}
	
	
	
	//Relação Peça e Venda
	// Inserir dados de uma relação Venda e Peça
	public void createdVendaPeca(RelationWithPeca relation) {
		String sql = "insert into VendaPeca (idVenda, idPeca, quantidade) values (?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setInt(1, relation.getIdVenda());
			stmt.setInt(2, relation.getIdPeca());
			stmt.setInt(3, relation.getQuantidade());

			// Executa
			stmt.execute();
			stmt.close();

			// Retornando ação:
			System.out.println("Relação adicionada ao sistema");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Listar dados de Venda e Peça:
	public List<RelationWithPeca> allVendaAndPeca(int id) {
		try {
			List<RelationWithPeca> ralationDatas = new ArrayList<RelationWithPeca>();
			PreparedStatement stmts = conexao.prepareStatement("SELECT VP.idPeca as idPeca, P.nome as nomePeca, VP.quantidade as quantidade FROM VendaPeca VP INNER JOIN Peca P ON VP.idPeca = P.id WHERE VP.idVenda = ?");
			
			// Preenchendo os valores dos campos:
			stmts.setInt(1, id);
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto relação
				RelationWithPeca relation = new RelationWithPeca();

				relation.setIdVenda(id);
				relation.setIdPeca(rs.getInt("idPeca"));
				relation.setNomePeca(rs.getString("nomePeca"));
				relation.setQuantidade(rs.getInt("quantidade"));

				ralationDatas.add(relation);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return ralationDatas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Listando peças que não tenha relação com venda
	public List<RelationWithPeca> allNotExistsRelationWithVenda(int id){
		try {
			List<RelationWithPeca> ralationDatas = new ArrayList<RelationWithPeca>();
			PreparedStatement stmts = conexao.prepareStatement("SELECT id, nome, valor, descricao FROM Peca WHERE id NOT IN ( SELECT idPeca FROM VendaPeca WHERE idVenda = ?);");
			
			// Preenchendo os valores dos campos:
			stmts.setInt(1, id);
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto relação
				RelationWithPeca relation = new RelationWithPeca();

				relation.setIdVenda(id);
				relation.setIdPeca(rs.getInt("id"));
				relation.setNomePeca(rs.getString("nome"));
				relation.setDescricaoPeca(rs.getString("descricao"));
				relation.setValorPeca(rs.getString("valor"));
				
				ralationDatas.add(relation);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return ralationDatas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Procurando se a relaçao existe entre venda e peça
	public boolean RelationVendaAndPecaExists(int idVenda, int idPeca) {
		String sql = "select count(*) from VendaPeca where idVenda = ? and idPeca = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idVenda);
			stmt.setInt(2, idPeca);
			
			// Executando
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int count = rs.getInt(1); // Contagem de registros da consulta

			// Encerrando
			rs.close();
			stmt.close();
			return count > 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Removendo um cadastro da relaçao entre venda e peça
	public void removeRelationVendaAndPeca(int idPeca, int idVenda) {
		if (RelationVendaAndPecaExists(idVenda, idPeca)) { // Validando que existe
			String sql = "delete from VendaPeca where idVenda = ? and idPeca = ?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, idVenda);
				stmt.setInt(2, idPeca);
				
				// Executando
				stmt.execute();
				stmt.close();

				// Message
				System.out.println("Relação removida");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Relação não existe ou não encontrado");
		}
	}
}
