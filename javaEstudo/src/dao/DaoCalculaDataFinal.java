package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnection;

public class DaoCalculaDataFinal {

	private Connection connection;
	
	
	
	public DaoCalculaDataFinal() {
		connection = SingleConnection.getConnection();
	}
	
	public void gravaDataFinal(String data) throws Exception{
		
		String sql = "insert into datafinal (datafinal) values (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, data);
		preparedStatement.execute();
		connection.commit();
	}
}
