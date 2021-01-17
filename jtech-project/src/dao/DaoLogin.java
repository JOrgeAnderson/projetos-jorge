package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionjdbc.SingleConnection;

public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		
		connection = SingleConnection.getConnection();
	}
	
	public boolean VerificaLogin(String login, String senha) throws Exception{
		
		String sql = "SELECT * FROM usuario where login = '"+login+"' and senha = '"+senha+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean verificaCategoria(String login) throws SQLException {
		
		String sql = "SELECT * from usuario where login = '"+login+"' and categoria = 'Administrador'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			return true;
		
		}
			
			return false;
		}
		
		
	}
	
