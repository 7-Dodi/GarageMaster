package br.com.GarageMaster.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManufature {

	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver"); //Usando o drive do PostgreSQL
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/garageMaster", "postgres", "dx=26/12"); //Conexão com o banco
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
