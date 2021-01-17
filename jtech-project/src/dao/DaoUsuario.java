package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectionjdbc.SingleConnection;
import model.Usuario;

public class DaoUsuario {//CRUD

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvarUsuario(Usuario usuario) {

		try {
			String sql = "INSERT INTO usuario(nome, login, senha, email, categoria) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getNome());
			insert.setString(2, usuario.getLogin());
			insert.setString(3, usuario.getSenha());
			insert.setString(4, usuario.getEmail());
			insert.setString(5, usuario.getCategoria());

			insert.execute();
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

	public List<Usuario> Buscar(String descricao) throws SQLException {
		String sql = "SELECT * FROM usuario where login <> 'admin' and like '%" + descricao + "%'";

		return listar(sql);

	}

	public List<Usuario> listarUsuarios() throws SQLException {
		String sql = "SELECT * FROM usuario where login <> 'admin'";
		return listar(sql);
	}

	private List<Usuario> listar(String sql) throws SQLException {

		List<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setCategoria(resultSet.getString("categoria"));

			lista.add(usuario);
		}

		return lista;
	}

	public Usuario consultarUsuario(String id) throws SQLException {

		String sql = "SELECT * FROM usuario where id = " + id + " and login <> 'admin'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			Usuario usuarioview = new Usuario();
			usuarioview.setId(resultSet.getLong("id"));
			usuarioview.setNome(resultSet.getString("nome"));
			usuarioview.setLogin(resultSet.getString("login"));
			usuarioview.setEmail(resultSet.getString("email"));
			usuarioview.setSenha(resultSet.getString("senha"));
			usuarioview.setCategoria(resultSet.getString("categoria"));

			return usuarioview;
		}
		return null;

	}

	public void atualizarUsuario(Usuario usuario) {

		try {

			String sql = "UPDATE usuario SET nome = ?, login = ?, email = ?, senha = ?, categoria = ? where id = " + usuario.getId();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getLogin());
			statement.setString(3, usuario.getEmail());
			statement.setString(4, usuario.getSenha());
			statement.setString(5, usuario.getCategoria());

			statement.executeUpdate();
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

	public void deletarUsuario(String id) {

		try {

			String sql = "DELETE FROM usuario where id = " + id + " and login <> 'admin'";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

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

	public boolean validarLogin(String login) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where login = '" + login + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;

		}

		return false;
	}

	public boolean validarUpdateLogin(String login, String id) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where login = '" + login + "' and id <> " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;

		}

		return false;

	}

}
