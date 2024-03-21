package br.com.GarageMaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GarageMaster.connection.ConnectionManufature;
import br.com.GarageMaster.entities.Peca;

public class PecaDAO {
	private Connection conexao;

	public PecaDAO() throws ClassNotFoundException {
		this.conexao = new ConnectionManufature().getConnection();
	}

	// Inserir dados de uma peca
	public void createdPeca(Peca peca) {
		String sql = "insert into peca (nome, descricao, valor) values (?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, peca.getNome());
			stmt.setString(2, peca.getDescricao());
			stmt.setFloat(3, peca.getValor());

			// Executa
			stmt.execute();
			stmt.close();

			// Retornando ação:
			System.out.println("Peca adicionada ao sistema");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Pesquisar uma peca:
	public Peca searchPeca(int id) throws SQLException {
		String sql = "select * from peca where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);

		try {
			// Enviando dados da pesquisa
			stmt.setInt(1, id);
			// Recebendo os dados
			ResultSet rs = stmt.executeQuery();
			Peca peca = new Peca();

			// Verificando se existe retorno
			if (!rs.next()) {
				System.out.println("Não foi encontrado nenhuma peca");
			} else {
				do { // Existindo, deve-se percorrer o while pelo menos uma vez
						// Criando o objeto Peca
					peca.setId(rs.getInt("id"));
					peca.setNome(rs.getString("nome"));
					peca.setDescricao(rs.getString("descricao"));
					peca.setValor(rs.getFloat("valor"));
				} while (rs.next());
			}

			// Retornando a peca criado
			return peca;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Listar dados de todos as pecas:
	public List<Peca> allPecas() {
		try {
			List<Peca> pecas = new ArrayList<Peca>();
			PreparedStatement stmts = conexao.prepareStatement("select * from peca");
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto peca
				Peca peca = new Peca();

				peca.setId(rs.getInt("id"));
				peca.setNome(rs.getString("nome"));
				peca.setDescricao(rs.getString("descricao"));
				peca.setValor(rs.getFloat("valor"));

				pecas.add(peca);
			}

			// Finalizando execurção
			rs.close();
			stmts.close();

			// Retornando a lista criada
			return pecas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Editar um cadastro de um peca
	public void editPeca(Peca peca) {
		String sql = "update peca set nome=?, descricao=?, valor=? where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, peca.getNome());
			stmt.setString(2, peca.getDescricao());
			stmt.setFloat(3, peca.getValor());
			stmt.setInt(4, peca.getId());

			// Executando
			stmt.execute();
			stmt.close();

			// Message
			System.out.println("Peca atualizada");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Procurando se a peca já existe
	public boolean PecaExists(int id) {
		String sql = "select count(*) from peca where id=?";
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

	// Removendo um cadastro de um peca
	public void removePeca(int id) {
		if (PecaExists(id)) { // Validando que a peca existe
			String sql = "delete from peca where id=?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);

				// Executando
				stmt.execute();
				stmt.close();

				// Message
				System.out.println("Peca removido");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Peca não existe ou não encontrado");
		}
	}
}
