package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidade.Eventos;

public class DaoEventos {

	private static Connection connection;
	
	public DaoEventos() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvarEvento(Eventos eventos) {
		try {

			String sql = "INSERT INTO eventos (descricao, dataEvento) "
					+ "VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, eventos.getDescricao());
			statement.setString(2, eventos.getDataEvento());
			

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
	
	public List<Eventos> getEventos() throws Exception {
		List<Eventos> lista = new ArrayList<Eventos>();
		String sql = "SELECT * FROM eventos";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Eventos eventos = new Eventos();
			eventos.setId(resultSet.getLong("id"));
			eventos.setDescricao(resultSet.getString("descricao"));
			eventos.setDataEvento(resultSet.getString("dataevento"));
			
			lista.add(eventos);
		}
		
		return lista;
	}

	public void delete(String id) {

		try {
			String sql = "DELETE FROM eventos where id = '" + id;
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

	public Eventos consultar(String id) throws SQLException {

		String sql = "SELECT * FROM eventos";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Eventos eventos = new Eventos();
			eventos.setId(resultSet.getLong("id"));
			
			return eventos; /* curriculoBase64, contentTypeCurriculo */

		}

		return null;
	}


	public void atualizar(Eventos eventos) {

		try {

			String sql = "UPDATE eventos SET descricao = ?, dataevento = ?  where id = " + eventos.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, eventos.getDescricao());
			preparedStatement.setString(2, eventos.getDataEvento());

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
