package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * Responsável por fazer a conexão com o banco de dados 
 * Conexão Mysql para o banco curso-jsp
 * @author Jorge Anderson
 *
 */

public class SingleConnectionMySQL {
	
	
	private static String url = "jdbc:mysql://localhost:3306/teste-mysql?autoReconnect=true";
	private static String password = "admin";
	private static String user = "jorge123";
	private static Connection connection = null;
	
	
	static {//
		conectar();
	}
	
	public SingleConnectionMySQL() {
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
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
