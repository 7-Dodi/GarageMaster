package br.com.GarageMaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GarageMaster.connection.ConnectionManufature;
import br.com.GarageMaster.entities.Client;

public class ClientDAO {
	private Connection conexao;

	public ClientDAO() throws ClassNotFoundException {
		this.conexao = new ConnectionManufature().getConnection();
	}

	// Inserir dados de um cliente
	public void createdClient(Client cliente) {
		String sql = "insert into cliente (nome, cpf, endereco, telefone) values (?,?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEndereco());
			stmt.setString(4, cliente.getTelefone());

			// Executa
			stmt.execute();
			stmt.close();

			// Retornando ação:
			System.out.println("Cliente adicionado ao sistema");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Pesquisar um cliente:
	public Client searchClient(int id) throws SQLException {
		String sql = "select * from cliente where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);

		try {
			// Enviando dados da pesquisa
			stmt.setInt(1, id);
			// Recebendo os dados
			ResultSet rs = stmt.executeQuery();
			Client cliente = new Client();

			// Verificando se existe retorno
			if (!rs.next()) {
				System.out.println("Não foi encontrado nenhum cliente");
			} else {
				do { // Existindo, deve-se percorrer o while pelo menos uma vez
						// Criando o objeto Cliente
					cliente.setId(rs.getInt("id"));
					cliente.setNome(rs.getString("nome"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setEndereco(rs.getString("endereco"));
					cliente.setTelefone(rs.getString("telefone"));
				} while (rs.next());
			}

			// Retornando o cliente criado
			return cliente;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Listar dados de todos os clientes:
	public List<Client> allClients() {
		try {
			List<Client> clientes = new ArrayList<Client>();
			PreparedStatement stmts = conexao.prepareStatement("select * from cliente");
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto cliente
				Client cliente = new Client();

				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));

				clientes.add(cliente);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return clientes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Editar um cadastro de um cliente
	public void editClient(Client cliente) {
		String sql = "update cliente set nome=?, cpf=?, endereco=?, telefone=? where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEndereco());
			stmt.setString(4, cliente.getTelefone());
			stmt.setInt(5, cliente.getId());

			// Executando
			stmt.execute();
			stmt.close();

			// Message
			System.out.println("Cliente atualizado");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Procurando se o cliente existe
	public boolean ClientExists(int id) {
		String sql = "select count(*) from cliente where id=?";
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

	// Removendo um cadastro de um cliente
	public void removeClient(int id) {
		if (ClientExists(id)) { // Validando que o usuário existe
			String sql = "delete from cliente where id=?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);

				// Executando
				stmt.execute();
				stmt.close();

				// Message
				System.out.println("Cliente removido");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Cliente não existe ou não encontrado");
		}
	}
}
