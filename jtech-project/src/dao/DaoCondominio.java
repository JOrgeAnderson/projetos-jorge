package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionjdbc.SingleConnection;
import model.Condominio;

public class DaoCondominio {
	
	private Connection connection;
	
	
	public DaoCondominio() {
		connection = SingleConnection.getConnection();
	}
	
	
	public void salvarCondominio(Condominio condominio) {
		
		try {
			String sql = "INSERT INTO condominio (nome, cep, rua, bairro, cidade, estado, telefone) "
					+ "VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
		
			
			statement.setString(1, condominio.getNome());
			statement.setString(2, condominio.getCep());
			statement.setString(3, condominio.getRua());
			statement.setString(4, condominio.getBairro());
			statement.setString(5, condominio.getCidade());
			statement.setString(6, condominio.getEstado());
			statement.setString(7, condominio.getTelefone());
//			statement.setLong(8, condominio.getIdUser());
//			statement.setLong(9, condominio.getTotalImposto());
			statement.execute();
			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public List<Condominio> listarDescricao(String descricao) throws SQLException{
			String sql = "SELECT * FROM condominio where nome like '%"+descricao+"%'";
			return lista(sql);
	}
	
	public List<Condominio> listarCondominio() throws SQLException{
		String sql = "SELECT * FROM condominio";
		return lista(sql);
	}
	
	private List<Condominio> lista(String sql) throws SQLException{
		List<Condominio> lista = new ArrayList<Condominio>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			Condominio condominio = new Condominio();
			condominio.setId(resultSet.getLong("id"));
			condominio.setNome(resultSet.getString("nome"));
			condominio.setCep(resultSet.getString("cep"));
			condominio.setRua(resultSet.getString("rua"));
			condominio.setBairro(resultSet.getString("bairro"));
			condominio.setCidade(resultSet.getString("cidade"));
			condominio.setEstado(resultSet.getString("estado"));
			condominio.setTelefone(resultSet.getString("telefone"));
//			condominio.setIdUser(resultSet.getLong("idUser"));
//			condominio.setTotalImposto(resultSet.getLong("totalImposto"));
			
			lista.add(condominio);
		}
		
		return lista;
		
	}
	
	public Condominio consultarCondominio(Long id) throws SQLException{
		
		
			String sql = "SELECT * FROM condominio where id = "+ id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				Condominio condominio = new Condominio();
				condominio.setNome(resultSet.getString("nome"));
				condominio.setCep(resultSet.getString("cep"));
				condominio.setRua(resultSet.getString("bairro"));
				condominio.setBairro(resultSet.getString("bairro"));
				condominio.setCidade(resultSet.getString("cidade"));
				condominio.setEstado(resultSet.getString("estado"));
				condominio.setTelefone(resultSet.getString("telefone"));
//				condominio.setIdUser(resultSet.getLong("idUser"));
//				condominio.setTotalImposto(resultSet.getLong("totalImposto"));
				
				return condominio;
			}
				return null;
	}
	
	
	public void atualizarCondominio(Condominio condominio) {
		
		try {
			String sql = "UPDATE condominio SET (nome = ?, cep= ?, rua = ?, "
					+ "bairro = ?, cidade = ?, estado = ?, telefone = ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, condominio.getNome());
			statement.setString(2, condominio.getCep());
			statement.setString(3, condominio.getRua());
			statement.setString(4, condominio.getBairro());
			statement.setString(5, condominio.getCidade());
			statement.setString(6, condominio.getEstado());
			statement.setString(7, condominio.getTelefone());
//			statement.setLong(8, condominio.getIdUser());
//			statement.setLong(9, condominio.getTotalImposto());
			
			statement.executeUpdate();
			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void deletarCondominio(Condominio condominio) {
		
		try {
			String sql = "DELETE FROM condominio where id = "+condominio.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			
			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
}
