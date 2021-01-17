package br.com.jwebfull.desk.conexaoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexaoBD {
	
	private static String url = "jdbc:postgresql://localhost:5432/jwebfull";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection conexao = null;
	
	static {
		conectar();
	}
	
	
	public conexaoBD() {
		conectar();
	}

	
	private static void conectar() {
		
		try {
		if(conexao == null) {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(url, user, password);
			conexao.setAutoCommit(false);
			System.out.println("SUCESSO!");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		}
	
	public static Connection getConnection() {
		return conexao;
	}
		
	}
