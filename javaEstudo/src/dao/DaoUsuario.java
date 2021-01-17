package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import entidade.Usuario;

public class DaoUsuario {

	private Connection connection;

	public  DaoUsuario() {

		connection = SingleConnection.getConnection();
	}

	public void salvar(Usuario usuario) {
		try {

			String sql = "INSERT INTO usuario (login, senha) "
					+ "VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			

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

	public List<Usuario> listar(String descricaoconsulta) throws SQLException{
		
		String sql = "SELECT * FROM usuario where nome like '%"+descricaoconsulta+"%'";
		return consultarUuarios(sql);
	}
	
	public List<Usuario> listar() throws Exception {
		String sql = "SELECT * FROM usuario";

		return consultarUuarios(sql);

	}

	private List<Usuario> consultarUuarios(String sql) throws SQLException {
		List<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			
			
			lista.add(usuario);
		}
		
		return lista;
	}

	public void delete(String id) {

		try {
			String sql = "DELETE FROM usuario where id = '" + id + "' and login <> 'admin'";
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

	public Usuario consultar(String id) throws SQLException {

		String sql = "SELECT * FROM usuario";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			
			return usuario; /* curriculoBase64, contentTypeCurriculo */

		}

		return null;
	}

	public boolean validarLogin(String login) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where login = '" + login + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;// Return true

		}

		return false;
	}

	/*
	 * public boolean validarSenha(String senha) throws SQLException {
	 * 
	 * String sql = "SELECT count(1) as qtd FROM usuario where senha = '" + senha +
	 * "'";
	 * 
	 * PreparedStatement preparedStatement = connection.prepareStatement(sql);
	 * ResultSet resultSet = preparedStatement.executeQuery();
	 * 
	 * if (resultSet.next()) {
	 * 
	 * return resultSet.getInt("qtd") <= 0;// Return true
	 * 
	 * }
	 * 
	 * return false; }
	 */

	public boolean validarLoginUpdate(String login, String id) throws SQLException {

		String sql = "SELECT count(1) as qtd FROM usuario where login = '" + login + "' and id <> " + id;// selecionar
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

	public void atualizar(Usuario usuario) {

		try {

			String sql = "UPDATE usuario SET login = ?, senha = ?, imagem = ? where id = " + usuario.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getImagem());

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

	public void gravarImagem(String imagem) throws SQLException {
		
		String tipoDados = imagem.split(",")[0].split(";")[0].split("/")[1];
		
		String sql = "insert into usuario (imagem, tipofile) values (?,?);";
			
		PreparedStatement insert = connection.prepareStatement(sql);
		
		insert.setString(1, imagem);
		insert.setString(2, tipoDados);
		insert.execute();
		connection.commit();
	}

	public Usuario buscaImagem(String iduser)  {
		
		try {
			
		
		String sql = "SELECT * FROM usuario where id = "+iduser;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setImagem(resultSet.getString("imagem"));
			usuario.setTipofile(resultSet.getString("tipofile"));
			
			
			return usuario;
			
		}
			}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}
}
