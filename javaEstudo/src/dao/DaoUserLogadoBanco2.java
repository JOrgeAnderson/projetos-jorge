package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection2;
import entidade.UserLogado;

public class DaoUserLogadoBanco2 {

	private Connection connection;

	public  DaoUserLogadoBanco2() {

		connection = SingleConnection2.getConnection();
	}

	public void salvar(UserLogado userlogado) {
		try {

			String sql = "INSERT INTO userlogado (login, senha) "
					+ "VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userlogado.getLogin());
			statement.setString(2, userlogado.getSenha());
			

			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Retorna uma lista pela descrição feita pelo usuário
	 * 
	 * @param descricaoconsulta
	 * @author Jorge Anderson
	 * @throws SQLException
	 */
	public List<UserLogado> descricaoLista(String descricaoconsulta) throws SQLException{
		
		String sql = "SELECT * FROM userlogado where nome like '%"+descricaoconsulta+"%'";
		return consultarUuarios(sql);
	}
	
	public List<UserLogado> listar() throws Exception {
		String sql = "SELECT * FROM userlogado";

		return consultarUuarios(sql);

	}

	private List<UserLogado> consultarUuarios(String sql) throws SQLException {
		List<UserLogado> lista = new ArrayList<UserLogado>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			UserLogado userlogado = new UserLogado();
			userlogado.setId(resultSet.getLong("id"));
			userlogado.setLogin(resultSet.getString("login"));
			userlogado.setSenha(resultSet.getString("senha"));
			
			lista.add(userlogado);
		}
		
		return lista;
	}

	public void delete(String id) {

		try {
			String sql = "DELETE FROM userlogado where id = '" + id + "' and login <> 'admin'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public UserLogado consultar(String id) throws SQLException {

		String sql = "SELECT * FROM userlogado";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			UserLogado userlogado = new UserLogado();
			userlogado.setId(resultSet.getLong("id"));
			userlogado.setLogin(resultSet.getString("login"));
			userlogado.setSenha(resultSet.getString("senha"));
			
			return userlogado; /* curriculoBase64, contentTypeCurriculo */

		}

		return null;
	}

	public boolean validarLogin(String login) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM userlogado where login = '" + login + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;// Return true

		}

		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM userlogado where login = '" + login + "' and id <> " + id;// selecionar
																											// dados que
																											// tenham o
																											// mesmo
																											// login
																											// tendo
																											// id's
																											// diferentes.

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;// Return true

		}

		return false;
	}

	public void atualizar(UserLogado userlogado) {

		try {

			String sql = "UPDATE userlogado SET login = ?, senha = ?  where id = " + userlogado.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, userlogado.getLogin());
			preparedStatement.setString(2, userlogado.getSenha());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
