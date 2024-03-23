package br.com.GarageMaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GarageMaster.connection.ConnectionManufature;
import br.com.GarageMaster.entities.Servico;

public class ServiceDAO {
	private Connection conexao;

	public ServiceDAO() throws ClassNotFoundException {
		this.conexao = new ConnectionManufature().getConnection();
	}

	// Inserir dados de um serviço
	public void createdService(Servico servico) {
		String sql = "insert into servico (descricao, valor, data, idVeiculo, idFuncionario) values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, servico.getDescricao());
			stmt.setFloat(2, servico.getValor());
			stmt.setString(3, servico.getData());
			stmt.setInt(4, servico.getIdVeiculo());
			stmt.setInt(5, servico.getIdFuncionario());

			// Executa
			stmt.execute();
			stmt.close();

			// Retornando ação:
			System.out.println("Serviço adicionado ao sistema");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Pesquisar um servico:
	public Servico searchService(int id) throws SQLException {
		String sql = "select * from servico where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);

		try {
			// Enviando dados da pesquisa
			stmt.setInt(1, id);
			// Recebendo os dados
			ResultSet rs = stmt.executeQuery();
			Servico servico = new Servico();

			// Verificando se existe retorno
			if (!rs.next()) {
				System.out.println("Não foi encontrado nenhum servico");
			} else {
				do { // Existindo, deve-se percorrer o while pelo menos uma vez
						// Criando o objeto servico
					servico.setId(rs.getInt("id"));
					servico.setDescricao(rs.getString("descricao"));
					servico.setValor(rs.getFloat("valor"));
					servico.setData(rs.getString("data"));
					servico.setIdVeiculo(rs.getInt("idVeiculo"));
					servico.setIdFuncionario(rs.getInt("idFuncionario"));
				} while (rs.next());
			}

			// Retornando o servico criado
			return servico;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Listar dados de todos os serciços:
	public List<Servico> allServicos() {
		try {
			List<Servico> servicos = new ArrayList<Servico>();
			PreparedStatement stmts = conexao.prepareStatement("SELECT s.id AS servicoId, s.descricao, s.valor, s.data, v.id AS veiculoId, v.modelo AS veiculoModelo, f.id AS funcionarioId, f.nome AS funcionarioNome FROM Servico s JOIN Veiculo v ON s.idVeiculo = v.id JOIN Funcionario f ON s.idFuncionario = f.id;");
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto servico
				Servico servico = new Servico();

				servico.setId(rs.getInt("servicoId"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setValor(rs.getFloat("valor"));
				servico.setData(rs.getString("data"));
				servico.setIdVeiculo(rs.getInt("veiculoId"));
				servico.setModeloVeiculo(rs.getString("veiculoModelo"));
				servico.setIdFuncionario(rs.getInt("funcionarioId"));
				servico.setFuncionarioNome(rs.getString("funcionarioNome"));

				servicos.add(servico);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return servicos;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Editar um cadastro de um servico
	public void editServico(Servico servico) {
		String sql = "update servico set descricao=?, valor=?, data=?, idVeiculo=?, idFuncionario=? where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, servico.getDescricao());
			stmt.setFloat(2, servico.getValor());
			stmt.setString(3, servico.getData());
			stmt.setInt(4, servico.getIdVeiculo());
			stmt.setInt(5, servico.getIdFuncionario());
			stmt.setInt(6, servico.getId());

			// Executando
			stmt.execute();
			stmt.close();

			// Message
			System.out.println("Servico atualizado");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Procurando se o Servico existe
	public boolean ServicoExists(int id) {
		String sql = "select count(*) from servico where id=?";
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

	// Removendo um cadastro de um Servico
	public void removeServico(int id) {
		if (ServicoExists(id)) { // Validando que o Servico existe
			String sql = "delete from servico where id=?";
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
			System.out.println("Serviço não existe ou não encontrado");
		}
	}
}
