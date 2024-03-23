package br.com.GarageMaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GarageMaster.connection.ConnectionManufature;
import br.com.GarageMaster.entities.Venda;

public class VendaDAO {
	private Connection conexao;

	public VendaDAO() throws ClassNotFoundException {
		this.conexao = new ConnectionManufature().getConnection();
	}

	// Inserir dados de uma venda
	public void createdVenda(Venda venda) {
		String sql = "insert into venda (valor, data, finalizacao, idCliente, idFuncionario) values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setFloat(1, venda.getValor());
			stmt.setString(2, venda.getData());
			stmt.setString(3, venda.getFinalizacao());
			stmt.setInt(4, venda.getIdClient());
			stmt.setInt(5, venda.getIdFuncionario());
			
			// Executa
			stmt.execute();
			stmt.close();

			// Retornando ação:
			System.out.println("Venda adicionado ao sistema");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Pesquisar um venda:
	public Venda searchVenda(int id) throws SQLException {
		String sql = "select * from venda where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);

		try {
			// Enviando dados da pesquisa
			stmt.setInt(1, id);
			
			// Recebendo os dados
			ResultSet rs = stmt.executeQuery();
			Venda venda = new Venda();

			// Verificando se existe retorno
			if (!rs.next()) {
				System.out.println("Não foi encontrado nenhum venda");
			} else {
				do { // Existindo, deve-se percorrer o while pelo menos uma vez
						// Criando o objeto Venda
					venda.setId(rs.getInt("id"));
					venda.setValor(rs.getFloat("valor"));
					venda.setData(rs.getString("data"));
					venda.setFinalizacao(rs.getString("finalizacao"));
					venda.setIdClient(rs.getInt("idCliente"));
					venda.setIdFuncionario(rs.getInt("idFuncionario"));
					
				} while (rs.next());
			}

			// Retornando a venda criado
			return venda;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Listar dados de todos os vendas:
	public List<Venda> allVendass() {
		try {
			List<Venda> vendas = new ArrayList<Venda>();
			PreparedStatement stmts = conexao.prepareStatement("SELECT v.id AS vendaId, v.finalizacao, v.valor, v.data, c.id AS clienteId, c.nome AS nomeClient, f.id AS funcionarioId, f.nome AS funcionarioNome FROM Venda v JOIN Cliente c ON v.idCliente = c.id JOIN Funcionario f ON v.idFuncionario = f.id;");
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto venda
				Venda venda = new Venda();

				venda.setId(rs.getInt("vendaId"));
				venda.setValor(rs.getFloat("valor"));
				venda.setData(rs.getString("data"));
				venda.setFinalizacao(rs.getString("finalizacao"));
				venda.setIdClient(rs.getInt("clienteId"));
				venda.setNameClient(rs.getString("nomeClient"));;
				venda.setIdFuncionario(rs.getInt("funcionarioId"));
				venda.setNameFuncionario(rs.getString("funcionarioNome"));

				vendas.add(venda);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return vendas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Editar um cadastro de uma venda
	public void editVenda(Venda venda) {
		String sql = "update venda set valor=?, data=?, finalizacao=?, idCliente=?, idFuncionario=? where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setFloat(1, venda.getValor());
			stmt.setString(2, venda.getData());
			stmt.setString(3, venda.getFinalizacao());
			stmt.setInt(4, venda.getIdClient());
			stmt.setInt(5, venda.getIdFuncionario());
			stmt.setInt(6, venda.getId());

			// Executando
			stmt.execute();
			stmt.close();

			// Message
			System.out.println("Venda atualizado");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Procurando se a venda existe
	public boolean VendaExists(int id) {
		String sql = "select count(*) from venda where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);

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

	// Removendo um cadastro de um venda
	public void removeVenda(int id) {
		if (VendaExists(id)) { // Validando que o objeto existe
			String sql = "delete from venda where id=?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);

				// Executando
				stmt.execute();
				stmt.close();

				// Message
				System.out.println("Venda removida");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Venda não existe ou não encontrado");
		}
	}
}
