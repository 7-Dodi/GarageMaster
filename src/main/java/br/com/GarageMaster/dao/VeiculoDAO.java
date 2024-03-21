package br.com.GarageMaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GarageMaster.connection.ConnectionManufature;
import br.com.GarageMaster.entities.Veiculo;

public class VeiculoDAO {
	private Connection conexao;

	public VeiculoDAO() throws ClassNotFoundException {
		this.conexao = new ConnectionManufature().getConnection();
	}

	// Inserir dados de um veiculo
	public void createdVeiculo(Veiculo veiculo) {
		String sql = "insert into veiculo (modelo, placa, marca, idCliente) values (?,?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, veiculo.getModelo());
			stmt.setString(2, veiculo.getPlaca());
			stmt.setString(3, veiculo.getMarca());
			stmt.setInt(4, veiculo.getIdClient());

			// Executa
			stmt.execute();
			stmt.close();

			// Retornando ação:
			System.out.println("Veiculo adicionado ao sistema");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Pesquisar um Veiculo:
	public Veiculo searchVeiculo(int id) throws SQLException {
		String sql = "select * from veiculo where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);

		try {
			// Enviando dados da pesquisa
			stmt.setInt(1, id);
			// Recebendo os dados
			ResultSet rs = stmt.executeQuery();
			Veiculo veiculo = new Veiculo();

			// Verificando se existe retorno
			if (!rs.next()) {
				System.out.println("Não foi encontrado nenhum veiculo");
			} else {
				do { // Existindo, deve-se percorrer o while pelo menos uma vez
						// Criando o objeto veiculo
					veiculo.setId(rs.getInt("id"));
					veiculo.setModelo(rs.getString("modelo"));
					veiculo.setPlaca(rs.getString("placa"));
					veiculo.setMarca(rs.getString("marca"));
					veiculo.setIdClient(rs.getInt("idCliente"));
				} while (rs.next());
			}

			// Retornando o veiculo criado
			return veiculo;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Listar dados de todos os Veiculo:
	public List<Veiculo> allVeiculos() {
		try {
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			//A Query SQL retorna uma junção das tabelas veiculo e cliente
			PreparedStatement stmts = conexao.prepareStatement("SELECT v.id AS veiculoId, v.modelo, v.marca, v.placa, v.idCliente, c.nome AS clienteNome FROM Veiculo v JOIN Cliente c ON v.idCliente = c.id;");
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto veiculo
				Veiculo veiculo = new Veiculo();

				veiculo.setId(rs.getInt("veiculoId"));
				veiculo.setModelo(rs.getString("modelo"));
				veiculo.setMarca(rs.getString("marca"));
				veiculo.setPlaca(rs.getString("placa"));
				veiculo.setIdClient(rs.getInt("idCliente"));
				veiculo.setNomeClient(rs.getString("clienteNome"));

				veiculos.add(veiculo);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return veiculos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Editar um cadastro de um veiculo
	public void editVeiculo(Veiculo veiculo) {
		String sql = "update veiculo set modelo=?, placa=?, marca=?, idCliente=? where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, veiculo.getModelo());
			stmt.setString(2, veiculo.getPlaca());
			stmt.setString(3, veiculo.getMarca());
			stmt.setInt(4, veiculo.getIdClient());
			stmt.setInt(5, veiculo.getId());

			// Executando
			stmt.execute();
			stmt.close();

			// Message
			System.out.println("Veiculo atualizado");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Procurando se o veiculo existe
	public boolean VeiculoExists(int id) {
		String sql = "select count(*) from veiculo where id=?";
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

	// Removendo um cadastro de um veiculo
	public void removeVeiculo(int id) {
		if (VeiculoExists(id)) { // Validando que o usuário existe
			String sql = "delete from veiculo where id=?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);

				// Executando
				stmt.execute();
				stmt.close();

				// Message
				System.out.println("Veiculo removido");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Veiculo não existe ou não encontrado");
		}
	}
}
