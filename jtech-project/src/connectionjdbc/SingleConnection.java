package connectionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/jtech-project?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
		
	}

	private static void conectar() {
		
		try {
				
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
			
		}
		
		
	}
	
	public static Connection getConnection() {
		
		return connection;
	}
	
	
	
}