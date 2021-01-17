package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * Responsável por fazer a conexão com o banco de dados
 * Conexão Postgrsql para o banco curso-jsp
 * @author Jorge Anderson
 *
 */

public class SingleConnection2 {
	
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	
	static {//
		conectar();
	}
	
	public SingleConnection2() {
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com banco de dados.");
		}
		
	}

	/**
	 * Retoirna a conexão do banco de dados
	 * 
	 * @author Jorge Anderson
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	

}
