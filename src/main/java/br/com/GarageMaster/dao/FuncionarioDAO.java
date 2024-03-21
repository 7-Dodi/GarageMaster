package br.com.GarageMaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.GarageMaster.connection.ConnectionManufature;
import br.com.GarageMaster.entities.Funcionario;

public class FuncionarioDAO {
	private Connection conexao;
	
	public FuncionarioDAO() throws ClassNotFoundException {
		this.conexao = new ConnectionManufature().getConnection();
	}
	
	//Inserir um novo funcionário
	public void createdFuncionario (Funcionario fun) {
		String sql = "insert into funcionario (nome, cpf, endereco, cargo, matricula, senha) values (?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			//Preenchendo os valores dos campos:
			stmt.setString(1, fun.getNome());
			stmt.setString(2, fun.getCpf());
			stmt.setString(3, fun.getEndereco());
			stmt.setString(4, fun.getCargo());
			stmt.setString(5, fun.getMatricula());
			stmt.setString(6, fun.getSenha());
		
			//Executa
			stmt.execute();
			stmt.close();
			
			//Retornando ação:
			System.out.println("Funcionario adicionado ao sistema");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Pesquisar Funcinário
	public Funcionario searchFuncionario (int id) throws SQLException {
		String sql = "select * from funcionario where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		try {
			//Enviando dados da pesquisa
			stmt.setInt(1, id);
			//Recebendo os dados
			ResultSet rs = stmt.executeQuery();
			Funcionario fun = new Funcionario();
			
			//Verificando se existe retorno
			if(!rs.next()) {
				System.out.println("Não foi encontrado nenhum funcionario");
			}else {
				do { //Existindo, deve-se percorrer o while pelo menos uma vez
					// Criando o objeto Funcionario
					fun.setId(rs.getInt("id"));
					fun.setNome(rs.getString("nome"));
					fun.setCpf(rs.getString("cpf"));
					fun.setEndereco(rs.getString("endereco"));
					fun.setCargo(rs.getString("cargo"));
					fun.setMatricula(rs.getString("matricula"));
					fun.setSenha(rs.getString("senha"));
				} while (rs.next());
			}
			
			//Retornando o funcionario criado
			return fun;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Listando todos os funcionários
	public List<Funcionario> allFuncionarios() {
		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			PreparedStatement stmts = conexao.prepareStatement("select * from funcionario");
			ResultSet rs = stmts.executeQuery();

			while (rs.next()) {
				// Criando o objeto funcionario
				Funcionario fun = new Funcionario();
				
				// Criando o objeto Patient
				fun.setId(rs.getInt("id"));
				fun.setNome(rs.getString("nome"));
				fun.setCpf(rs.getString("cpf"));
				fun.setEndereco(rs.getString("endereco"));
				fun.setCargo(rs.getString("cargo"));
				fun.setMatricula(rs.getString("matricula"));
				fun.setSenha(rs.getString("senha"));
				
				funcionarios.add(fun);
			}
			
			//Finalizando execurção
			rs.close();
			stmts.close();
			
			//Retornando a lista criada
			return funcionarios;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	//Editar um funcionário
	public void editFuncionario(Funcionario funcionario) {
		String sql = "update funcionario set nome=?, cpf=?, endereco=?, cargo=?, matricula=?, senha=? where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Preenchendo os valores dos campos:
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getEndereco());
			stmt.setString(4, funcionario.getCargo());
			stmt.setString(5, funcionario.getMatricula());
			stmt.setString(6, funcionario.getSenha());
			stmt.setInt(7, funcionario.getId());

			// Executando
			stmt.execute();
			stmt.close();

			// Message
			System.out.println("Funcionario atualizado");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Procurando se um funcionario existe:
	public boolean FuncionarioExists(int id) {
		String sql = "select count(*) from funcionario where id=?";
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

	//Removendo um funcionario do sistema
	public void removeFuncionario(int id) {
		if (FuncionarioExists(id)) { // Validando que o usuário existe
			String sql = "delete from funcionario where id=?";
			try {
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id);

				// Executando
				stmt.execute();
				stmt.close();

				// Message
				System.out.println("Funcionario removido");

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Funcionario não existe ou não encontrado");
		}
	}

    // Autenticação do funcionário
    public Funcionario authenticate(String matricula, String senha) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE matricula = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setEndereco(rs.getString("endereco"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setMatricula(rs.getString("matricula"));
                    funcionario.setSenha(rs.getString("senha"));
                    return funcionario;
                }
            }
        }
        return null; // Nenhum funcionário encontrado com as credenciais fornecidas
    }
}
