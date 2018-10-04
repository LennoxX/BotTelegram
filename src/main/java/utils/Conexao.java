package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection con = null;
	private static final String ENDERECO = "jdbc:postgresql://localhost:5432/aulas";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "postgres";
	public static Connection getConection() {

		if (con == null) {
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(ENDERECO, USUARIO, SENHA);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		return con;
	}

	public static void Desconectar() {
		try {
			con.close();
		} catch (SQLException onConClose) {
			onConClose.printStackTrace();
		}
	}
}
