package br.com.jwebfull.desk.conexaoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jwebfull.desk.conexaoJDBC.conexaoBD;
import br.com.jwebfull.desk.model.Cliente;

public class ClienteDAO {

	private Connection connection;

	public ClienteDAO() {
		connection = conexaoBD.getConnection();
	}

	public void salvarCliente(Cliente cliente) {

		try {
			String sql = "INSERT INTO cliente (nome, email, cpf, usuario, senha, status) VALUES (?,?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, cliente.getNome().toUpperCase());
			insert.setString(2, cliente.getEmail().toLowerCase());
			insert.setString(3, cliente.getCpf());
			insert.setString(4, cliente.getUsuario().toLowerCase());
			insert.setString(5, cliente.getSenha());
			insert.setString(6, cliente.getStatus());

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

	public List<Cliente> listarCliente() throws Exception {

		List<Cliente> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM cliente";

		PreparedStatement statement = connection.prepareStatement(sqlSelect);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Cliente cliente = new Cliente();

			cliente.setId(resultSet.getLong("id"));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setCpf(resultSet.getString("cpf"));
			cliente.setUsuario(resultSet.getString("usuario"));

			lista.add(cliente);

		}

		return lista;
	}

	public Cliente buscarId(Long id) throws Exception {

		Cliente cliente = new Cliente();

		String sqlSelectId = "SELECT * FROM cliente where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sqlSelectId);
		ResultSet resultSet = statement.executeQuery();
		
		
		cliente.setNome(resultSet.getString("nome"));
		cliente.setEmail(resultSet.getString("email"));
		cliente.setCpf(resultSet.getString("cpf"));
		cliente.setUsuario(resultSet.getString("usuario"));

		return cliente;
	}

	public List<Cliente> BuscarNome(String nome) throws Exception {
		List<Cliente> lista = new ArrayList<>();

		String sqlSelectNome = "SELECT * FROM cliente where nome like '" + nome.toUpperCase() + "%'";

		PreparedStatement statement = connection.prepareStatement(sqlSelectNome);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Cliente cliente = new Cliente();
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setCpf(resultSet.getString("cpf"));
			cliente.setUsuario(resultSet.getString("usuario"));

			lista.add(cliente);
		}
		return lista;
	}

	public Cliente BuscarEmail(String email) throws Exception {

		Cliente cliente = new Cliente();

		String sqlSelectEmail = "SELECT * FROM cliente where nome like '" + email.toLowerCase() + "%'";

		PreparedStatement statement = connection.prepareStatement(sqlSelectEmail);
		ResultSet resultSet = statement.executeQuery();

		cliente.setNome(resultSet.getString("nome"));
		cliente.setEmail(resultSet.getString("email"));
		cliente.setCpf(resultSet.getString("cpf"));
		cliente.setUsuario(resultSet.getString("usuario"));

		return cliente;
	}

	public Cliente BuscarUsuario(String usuario) throws Exception {

		Cliente cliente = new Cliente();

		String sqlSelectUsuario = "SELECT * FROM cliente where nome like '" + usuario + "%'";

		PreparedStatement statement = connection.prepareStatement(sqlSelectUsuario);
		ResultSet resultSet = statement.executeQuery();

		cliente.setNome(resultSet.getString("nome"));
		cliente.setEmail(resultSet.getString("email"));
		cliente.setCpf(resultSet.getString("cpf"));
		cliente.setUsuario(resultSet.getString("usuario"));

		return cliente;
	}

	public Cliente BuscarCpf(String cpf) throws Exception {

		Cliente cliente = new Cliente();

		String sqlSelectCpf = "SELECT * FROM cliente where cpf like '" + cpf + "%'";

		PreparedStatement statement = connection.prepareStatement(sqlSelectCpf);
		ResultSet resultSet = statement.executeQuery();

		cliente.setNome(resultSet.getString("nome"));
		cliente.setEmail(resultSet.getString("email"));
		cliente.setCpf(resultSet.getString("cpf"));
		cliente.setUsuario(resultSet.getString("usuario"));

		return cliente;
	}

	public void atualizar(Cliente cliente) {

		String sqlUpdate = "UPDATE cliente set nome = ?, email = ?, usuario = ? where id = " + cliente.getId();

		try {
			PreparedStatement statement = connection.prepareStatement(sqlUpdate);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getEmail());
			statement.setString(3, cliente.getUsuario());

			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public void habilitarUser(Long id) {

		String sqlUpdate = "UPDATE cliente set status = 'ATIVO' where id = " +id;

		try {
			PreparedStatement statement = connection.prepareStatement(sqlUpdate);

			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public void Deletar(Long id) {

		try {
			String sqlDelete = "DELETE * FROM cliente where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sqlDelete);
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

}
