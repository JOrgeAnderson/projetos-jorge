package pos_ja_jdbc.pos_ja_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserposDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		UserposDAO dao = new UserposDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("Marcio");
		userposjava.setEmail("marcio.lima@gmail.com");

		dao.salvar(userposjava);
	}

	@Test
	public void iniFiltrar() {
		UserposDAO dao = new UserposDAO();

		try {
			List<Userposjava> list = dao.listar();

			for (Userposjava userposjava : list) {
				System.out.println(userposjava.getNome());
				System.out.println("---------------------------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initBuscar() {
		UserposDAO dao = new UserposDAO();

		try {
			Userposjava userposjava = dao.buscar(7L);

			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initAtualizar() {

		UserposDAO dao = new UserposDAO();

		try {
			Userposjava objetoBanco = dao.buscar(2l);
			
			objetoBanco.setNome("Miguel");
			
			dao.atualizar(objetoBanco);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void initDeletar() {
		
		try {
			
			UserposDAO dao = new UserposDAO();
			dao.deletar(7L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Telefone telefone = new Telefone();
		
		telefone.setNumero("(91) 3342-9283");
		telefone.setTipo("Casa");
		telefone.setUsuario(2L);
		
		UserposDAO dao = new UserposDAO();
		dao.salvarTelefone(telefone);
	}
	
	@Test
	public void testeCarregaFonesUser() {
		UserposDAO dao = new UserposDAO();
		
		List<BeanUserFone> beanFones = dao.listaUserFone(12L);
		
		for (BeanUserFone beanUserFone : beanFones) {
			System.out.println(beanUserFone);
			System.out.println("-------------------------------------");
		}
		
	}
	@Test
	public void testeDeleteUserFone() {
		
		UserposDAO dao = new UserposDAO();
		dao.deleteFonesPorUser(14L);
		
	}
	
}

